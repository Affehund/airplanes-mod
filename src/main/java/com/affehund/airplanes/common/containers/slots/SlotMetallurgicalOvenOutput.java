package com.affehund.airplanes.common.containers.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
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
public class SlotMetallurgicalOvenOutput extends Slot
{
	@SuppressWarnings("unused")
	private final EntityPlayer player;
	@SuppressWarnings("unused")
	private int removeCount;

	public SlotMetallurgicalOvenOutput(EntityPlayer player, IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
		this.player = player;
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return false;
	}

	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
	{
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}

	@Override
	public ItemStack decrStackSize(int amount)
	{
		if (this.getHasStack())
			this.removeCount += Math.min(amount, this.getStack().getCount());
		return super.decrStackSize(amount);
	}
}