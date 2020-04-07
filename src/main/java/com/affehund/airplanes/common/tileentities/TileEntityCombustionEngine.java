package com.affehund.airplanes.common.tileentities;

import com.affehund.airplanes.common.blocks.machines.BlockCombustionEngine;
import com.affehund.airplanes.common.blocks.tools.IRestorableTileEntity;
import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.energy.AirplanesForgeEnergyStorage;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
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
public class TileEntityCombustionEngine extends TileEntity implements ITickable, IRestorableTileEntity
{
	public int maxInput = 0;
	public int maxOutput = ConfigAirplanes.Global.Energy.MAX_OUTPUT_COMBUSTION;
	public int capacity = ConfigAirplanes.Global.Energy.MAX_CAPACITY_COMBUSTION;

	public int burnTime = 0;
	public int maxBurnTime = 50;

	public AirplanesForgeEnergyStorage energyStorage = new AirplanesForgeEnergyStorage(capacity, maxInput);
	public int energy = energyStorage.getEnergyStored();

	private String customName;

	public ItemStackHandler handler = new ItemStackHandler(1);

	@Override
	public void update()
	{
		boolean flag = this.isBurning();
		boolean flag1 = false;
		if (!this.world.isRemote)
		{
			outputEnergy(maxOutput);

			if (!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0))
					&& !(this.getEnergyStored() >= this.capacity))
			{
				burnTime++;
				if (burnTime >= maxBurnTime)
				{
					flag1 = true;
					int fuelValue = getFuelValue(handler.getStackInSlot(0));
					this.energyStorage.generateEnergy(fuelValue);
					handler.getStackInSlot(0).shrink(1);
					this.burnTime = 0;
				}
			}
			if (flag != this.isBurning())
			{
				flag1 = true;
				BlockCombustionEngine.setState(this.isBurning(), this.world, this.pos);
				this.markDirty();
			} else if (!this.isBurning() && this.burnTime > 0)
			{
				this.burnTime = MathHelper.clamp(this.burnTime - 2, 0, this.maxBurnTime);
			}
			if (flag1)
			{
				this.markDirty();
			}
		}
	}

	public int getMaxTrans()
	{
		return this.energyStorage.getMaxTransfer();
	}

	public void outputEnergy(int maxOutput)
	{
		if (energyStorage.getEnergyStored() > 0)
		{
			for (EnumFacing facing : EnumFacing.VALUES)
			{
				TileEntity tileEntity = world.getTileEntity(pos.offset(facing));

				if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
				{
					IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
					if (handler != null && handler.canReceive())
					{
						int accepted = handler.receiveEnergy(maxOutput, false);
						energyStorage.consumeEnergy(accepted);

						if (energyStorage.getEnergyStored() <= 0)
						{
							break;
						}
					}
				}
			}
			markDirty();
		}
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return oldState.getBlock() != newState.getBlock();
	}

	public boolean isBurning()
	{
		ItemStack stack = handler.getStackInSlot(0);
		return this.burnTime > 0 && isItemFuel(stack);
	}

	public static boolean isItemFuel(ItemStack stack)
	{
		return getFuelValue(stack) > 0;
	}

	public static int getFuelValue(ItemStack stack)
	{
		if (stack.isEmpty())
		{
			return 0;
		} else
		{
			/*
			 * to use burn time set by furnace fuels (Item#getItemBurnTime) as energy value
			 */
			int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
			if (burnTime >= 0)
				return burnTime;

			Item item = stack.getItem();
			if (item == ItemInit.FUEL)
			{
				return 200;
			} else if (item == ItemInit.WOODEN_GEAR)
			{
				return 127;
			} else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
			{
				return 100;
			} else if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
			{
				return 50;
			} else if (item == Item.getItemFromBlock(Blocks.WOOL))
			{
				return 40;
			} else if (item == Item.getItemFromBlock(Blocks.CARPET))
			{
				return 10;
			} else if (item == Item.getItemFromBlock(Blocks.LADDER))
			{
				return 50;
			} else if (item == Items.STICK)
			{
				return 25;
			} else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
			{
				return 50;
			} else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
			{
				return 1000;
			} else if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()))
			{
				return 100;
			} else if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()))
			{
				return 100;
			} else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()))
			{
				return 100;
			} else if (item != Items.BOW && item != Items.FISHING_ROD)
			{
				if (item == Items.SIGN)
				{
					return 100;
				} else if (item == Items.COAL)
				{
					return 125;
				} else if (item == Items.LAVA_BUCKET)
				{
					return 2000;
				} else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
				{
					if (item == Items.BLAZE_ROD)
					{
						return 700;
					} else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
					{
						return 300;
					} else
					{
						return item instanceof ItemBoat ? 200 : 0;
					}
				} else
				{
					return 50;
				}
			} else
			{
				return 150;
			}
		}
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
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler);
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

		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("BurnTime", this.burnTime);
		compound.setString("Name", getDisplayName().toString());
		compound.setInteger("Energy", energyStorage.getEnergyStored());
		this.writeRestorableToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.setCustomName(compound.getString("Name"));
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
		return new TextComponentTranslation("container.combustion_engine");
	}

	public int getEnergyStored()
	{
		return this.energyStorage.getEnergyStored();
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
		case 1:
			return this.burnTime;
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
		case 1:
			this.burnTime = value;
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

	public String getCustomName()
	{
		return customName;
	}

	public void setCustomName(String customName)
	{
		this.customName = customName;
	}
}
