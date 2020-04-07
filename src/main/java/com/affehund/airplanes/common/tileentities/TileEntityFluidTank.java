package com.affehund.airplanes.common.tileentities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.affehund.airplanes.common.blocks.tools.IRestorableTileEntity;
import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.utils.InventoryActions;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
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
public class TileEntityFluidTank extends TileEntity implements ITickable, IRestorableTileEntity
{
	public static final int MAX_CONTENTS = 16000; // 16 buckets
	public static final int TOTAL_SLOTS = 2;
	public static final int INPUT_SLOTS = 1;
	public static final int OUTPUT_SLOTS = 1;
	public FluidStack fluid;
	protected int fluidcapacity;

	public final FluidTank tank = new FluidTank(MAX_CONTENTS)
	{
		@Override
		protected void onContentsChanged()
		{
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);

			markDirty();
		}
	};

	public FluidTank getTank()
	{
		return tank;
	}

	private ItemStackHandler inputHandler = new ItemStackHandler(TOTAL_SLOTS)
	{
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack)
		{
			return true;
		}

		@Override
		protected void onContentsChanged(int slot)
		{
			TileEntityFluidTank.this.markDirty();
		}
	};

	@Override
	public void update()
	{
		if (getWorld().isRemote)
			return;
		updateTankSlots();
		if (ConfigAirplanes.Global.General.enableTankRainFilling)
		{
			fillWithRain(getWorld(), pos);
		}
	}

	public void fillWithRain(World worldIn, BlockPos pos)
	{
		BlockPos plusY = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		if (worldIn.rand.nextInt(50) == 1)
		{
			if (getWorld().isRainingAt(plusY))
			{
				FluidStack stack = new FluidStack(FluidRegistry.WATER, 10);
				tank.fill(stack, true);
			}
		}
	}

	public int getComparatorInputOverride()
	{
		float f = (float) this.tank.getFluidAmount() / this.tank.getCapacity();
		return (int) (f * 15);
	}

	public void updateTankSlots()
	{
		InventoryActions.fillItemFromTank(inputHandler, tank, 0, 1);
		InventoryActions.fillTankFromItem(inputHandler, tank, 0, 1);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
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
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
		{
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
		}
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputHandler);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		writeRestorableToNBT(compound);
		compound.setTag("Inventory", this.inputHandler.serializeNBT());
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inputHandler.deserializeNBT(compound.getCompoundTag("Inventory"));
		readRestorableFromNBT(compound);
	}

	@Override
	public void readRestorableFromNBT(NBTTagCompound compound)
	{
		tank.readFromNBT(compound.getCompoundTag("tank"));
	}

	@Override
	public void writeRestorableToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tankNBT = new NBTTagCompound();
		tank.writeToNBT(tankNBT);
		compound.setTag("tank", tankNBT);
	}

	public int getField(int id)
	{
		return 0;
	}

	public void setField(int id, int value)
	{
	}

	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		NBTTagCompound nbtTag = super.getUpdateTag();
		NBTTagCompound tankNBT = new NBTTagCompound();
		tank.writeToNBT(tankNBT);
		nbtTag.setTag("tank", tankNBT);
		return nbtTag;
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
	{
		tank.readFromNBT(packet.getNbtCompound().getCompoundTag("tank"));
	}

	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}
}