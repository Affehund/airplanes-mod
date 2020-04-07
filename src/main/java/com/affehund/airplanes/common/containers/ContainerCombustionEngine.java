package com.affehund.airplanes.common.containers;

import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

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
public class ContainerCombustionEngine extends Container
{
	private final TileEntityCombustionEngine tileentity;
	private int energy, burnTime;

	public ContainerCombustionEngine(InventoryPlayer player, TileEntityCombustionEngine tileentity)
	{
		this.tileentity = tileentity;
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 80, 31));

		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}

	@Override
	public void updateProgressBar(int id, int data)
	{
		this.tileentity.setField(id, data);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); ++i)
		{
			IContainerListener listener = (IContainerListener) this.listeners.get(i);
			if (this.energy != this.tileentity.getField(0))
				listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if (this.burnTime != this.tileentity.getField(1))
				listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
		}

		this.energy = this.tileentity.getField(0);
		this.burnTime = this.tileentity.getField(1);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 0)
			{
				if (!this.mergeItemStack(itemstack1, 0, this.inventorySlots.size(), true)
						&& TileEntityCombustionEngine.isItemFuel(itemstack))
				{
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 1, false)
					&& TileEntityCombustionEngine.isItemFuel(itemstack))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			} else
			{
				slot.onSlotChanged();
			}
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}
}