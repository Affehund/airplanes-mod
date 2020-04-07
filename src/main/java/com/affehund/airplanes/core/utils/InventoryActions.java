package com.affehund.airplanes.core.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */
public class InventoryActions
{
	/*
	 * This part of the code is copied from the ItemStackUtils of Industrial Foregoing.
	 * https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/1.12/src/main/java/com/buuz135/industrial/utils/ItemStackUtils.java
	 * Permission is given by the license of Industrial Foregoing.
	 * For more information: https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/1.12/LICENSE
	 */
	
	public static void fillItemFromTank(ItemStackHandler fluidItems, IFluidTank tank, int slotIn, int slotOut)
	{
		if (tank.getFluid() == null)
			return;
		ItemStack stack = fluidItems.getStackInSlot(slotIn).copy();
		if (!stack.isEmpty())
		{
			if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null))
			{
				FluidActionResult result = FluidUtil.tryFillContainer(stack, (IFluidHandler) tank, tank.getCapacity(),
						null, false);
				if (result.isSuccess() && (fluidItems.getStackInSlot(slotOut).isEmpty()
						|| (ItemHandlerHelper.canItemStacksStack(result.getResult(), fluidItems.getStackInSlot(slotOut))
								&& result.getResult().getCount() + fluidItems.getStackInSlot(1).getCount() <= result
										.getResult().getMaxStackSize())))
				{
					result = FluidUtil.tryFillContainer(stack, (IFluidHandler) tank, tank.getCapacity(), null, true);
					if (fluidItems.getStackInSlot(slotOut).isEmpty())
					{
						fluidItems.setStackInSlot(slotOut, result.getResult());
					} else
					{
						fluidItems.getStackInSlot(slotOut).grow(1);
					}
					fluidItems.getStackInSlot(slotIn).shrink(1);
				}
			}
		}
	}

	public static void fillTankFromItem(ItemStackHandler fluidItems, IFluidTank tank, int slotIn, int slotOut)
	{
		ItemStack stack = fluidItems.getStackInSlot(slotIn).copy();
		if (!stack.isEmpty())
		{
			if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null))
			{
				IFluidHandlerItem cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
				FluidStack fluidStack = cap.drain(1000, false);
				if (fluidStack != null && tank.fill(fluidStack, false) == slotIn)
					return;
				FluidActionResult result = FluidUtil.tryEmptyContainer(stack, (IFluidHandler) tank, 1000, null, false);
				if (result.isSuccess() && (fluidItems.getStackInSlot(slotOut).isEmpty()
						|| (ItemHandlerHelper.canItemStacksStack(result.getResult(), fluidItems.getStackInSlot(slotOut))
								&& result.getResult().getCount() + fluidItems.getStackInSlot(1).getCount() <= result
										.getResult().getMaxStackSize())))
				{
					result = FluidUtil.tryEmptyContainer(stack, (IFluidHandler) tank, tank.getCapacity(), null, true);
					if (fluidItems.getStackInSlot(slotOut).isEmpty())
					{
						fluidItems.setStackInSlot(slotOut, result.getResult());
					} else
					{
						fluidItems.getStackInSlot(slotOut).grow(1);
					}
					fluidItems.getStackInSlot(slotIn).shrink(1);
				}
			}
		}
	}
}
