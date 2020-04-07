package com.affehund.airplanes.common.containers;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

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
public class ContainerSuitcase extends ContainerChest
{
	public boolean updateNotification;

	public ContainerSuitcase(IInventory playerInventory, IInventory chestInventory, EntityPlayer player)
	{
		super(playerInventory, chestInventory, player);
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
	{
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size())
		{
			tmpSlot = inventorySlots.get(slotId);

		} else
		{
			tmpSlot = null;
		}

		if (tmpSlot != null)
		{
			if (tmpSlot.isHere(player.inventory, player.inventory.currentItem))
			{
				return tmpSlot.getStack();
			}
		}

		if (clickTypeIn == ClickType.SWAP)
		{
			ItemStack stack = player.inventory.getStackInSlot(dragType);
			if (stack == player.inventory.getCurrentItem())
			{
				return ItemStack.EMPTY;
			}
		}

		this.updateNotification = true;
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}