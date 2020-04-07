package com.affehund.airplanes.common.tileentities;

import javax.annotation.Nonnull;

import com.affehund.airplanes.common.blocks.tools.IRestorableTileEntity;
import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.energy.AirplanesForgeEnergyStorage;
import com.affehund.airplanes.core.energy.IBattery;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class TileEntityEnergyStorageBlock extends TileEntity implements ITickable, IRestorableTileEntity
{
	public int capacity = ConfigAirplanes.Global.Energy.MAX_CAPACITY_ENERGY_STORAGE;
	public int maxInput = ConfigAirplanes.Global.Energy.MAX_INPUT_ENERGY_STORAGE;
	public int maxOutput = ConfigAirplanes.Global.Energy.MAX_OUTPUT_ENERGY_STORAGE;
	public AirplanesForgeEnergyStorage energyStorage = new AirplanesForgeEnergyStorage(capacity, maxInput);
	public int energy = energyStorage.getEnergyStored();
	@SuppressWarnings("unused")
	private String customName;

	public static final int TOTAL_SLOTS = 2;
	public static final int ENERGY_INPUT_SLOTS = 1;
	public static final int ENERGY_OUTPUT_SLOTS = 1;

	private ItemStackHandler inputHandler = new ItemStackHandler(TOTAL_SLOTS);

	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			if (this.energy < this.capacity)
			{

				if (!inputHandler.getStackInSlot(0).isEmpty())
				{
					addEnergyFromItem();

				} else if (!inputHandler.getStackInSlot(1).isEmpty())
				{
					giveEnergyToItem();
				}

//				System.out.println(inputHandler.getStackInSlot(0).getItem().getRegistryName() + " "
//						+ inputHandler.getStackInSlot(0).getItemDamage());

				outputEnergy(maxOutput);
				markDirty();
			}
			markDirty();
		}
	}

	private void addEnergyFromItem()
	{
		if (!inputHandler.getStackInSlot(0).isEmpty() && !(this.getEnergyStored() >= this.capacity)
				&& inputHandler.getStackInSlot(0).getItem() instanceof IBattery
				&& inputHandler.getStackInSlot(0).getItemDamage() > 0)
		{

			ItemStack batteryStack = inputHandler.getStackInSlot(0);

			int nowDamage = 0;
			this.energyStorage.consumeEnergy(1);
			if (inputHandler.getStackInSlot(0).getItemDamage() <= inputHandler.getStackInSlot(0).getMaxDamage())
			{
				nowDamage = inputHandler.getStackInSlot(0).getMaxDamage()
						- inputHandler.getStackInSlot(0).getItemDamage();
				this.energyStorage.generateEnergy(1);
			} else
			{
				nowDamage = inputHandler.getStackInSlot(0).getMaxDamage();
			}
			inputHandler.getStackInSlot(0).getItem().setDamage(batteryStack, nowDamage);
		}
	}

	private void giveEnergyToItem()
	{
		if (!inputHandler.getStackInSlot(1).isEmpty() && !(this.getEnergyStored() >= 0)
				&& inputHandler.getStackInSlot(0).getItem() instanceof IBattery
				&& inputHandler.getStackInSlot(0).getItemDamage() < inputHandler.getStackInSlot(0).getMaxDamage())
		{
			ItemStack batteryStack = inputHandler.getStackInSlot(1);

			int nowDamage = 0;
			if (inputHandler.getStackInSlot(0).getItemDamage() >= 0)
			{
				nowDamage = inputHandler.getStackInSlot(0).getItemDamage() - 1;
				this.energyStorage.consumeEnergy(1);
			} else
			{
				nowDamage = 0;
			}

			inputHandler.getStackInSlot(1).getItem().setDamage(batteryStack, nowDamage);

		}
	}

	public void outputEnergy(int maxOutput)
	{
		for (EnumFacing facing : EnumFacing.VALUES)
		{
			TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
			if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
			{
				IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());

				if (handler != null && handler.canReceive())
				{
					int accepted = handler.receiveEnergy(energyStorage.getEnergyStored(), false);
					energyStorage.consumeEnergy(accepted);
					if (energyStorage.getEnergyStored() <= 0)
					{
						break;
					}
				}
			}
			markDirty();
		}
	}

	public boolean isItemValid(int slot, @Nonnull ItemStack stack)
	{
		if (stack.getItem() == ItemInit.BATTERY)
			return true;
		else
			return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityEnergy.ENERGY)
		{
			return CapabilityEnergy.ENERGY.cast(energyStorage);
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputHandler);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityEnergy.ENERGY)
		{
			return true;
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("GUIEnergy", this.energy);
		compound.setString("Name", getDisplayName().toString());
		compound.setTag("Inventory", this.inputHandler.serializeNBT());
		compound.setInteger("Energy", energyStorage.getEnergyStored());
		this.writeRestorableToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.energy = compound.getInteger("GUIEnergy");
		this.customName = compound.getString("Name");
		this.inputHandler.deserializeNBT(compound.getCompoundTag("Inventory"));
		energyStorage.setEnergy(compound.getInteger("Energy"));
		this.readRestorableFromNBT(compound);
	}

	@Override
	public void writeRestorableToNBT(NBTTagCompound compound)
	{
		compound.setInteger("energy", energyStorage.getEnergyStored());
	}

	@Override
	public void readRestorableFromNBT(NBTTagCompound compound)
	{
		energyStorage.setEnergy(compound.getInteger("energy"));
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentTranslation("container.energy_storage_block");
	}

	public int getEnergyStored()
	{
		return this.energy;
	}

	public int getMaxEnergyStored()
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	public int getField(int id)
	{
		switch (id)
		{
		case 0:
			return this.energyStorage.getEnergyStored();
		default:
			return 0;
		}
	}

	public void setField(int id, int value)
	{
		switch (id)
		{
		case 0:
			this.energy = value;
			break;
		default:
			break;
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
		this.handleUpdateTag(pkt.getNbtCompound());
	}

	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}
}
