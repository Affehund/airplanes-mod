package com.affehund.airplanes.common.containers;

import com.affehund.airplanes.common.containers.slots.SlotMetallurgicalOvenOutput;
import com.affehund.airplanes.common.tileentities.TileEntityMetallurgicalOven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
public class ContainerMetallurgicalOven extends Container
{
	private final TileEntityMetallurgicalOven tileentity;
	private int cookTime, totalCookTime, energy;

	public ContainerMetallurgicalOven(InventoryPlayer player, TileEntityMetallurgicalOven tileentity)
	{
		this.tileentity = tileentity;

		addMetallurgicalOvenSlots(player);
		// player inventory slots
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

	public void addMetallurgicalOvenSlots(InventoryPlayer player)
	{
		// input slots
		int x = 17;
		int y = 26;
		addSlotToContainer(new Slot(tileentity, 0, x, y));
		y += 18;
		addSlotToContainer(new Slot(tileentity, 1, x, y));
		x = 35;
		y = 26;
		addSlotToContainer(new Slot(tileentity, 2, x, y));
		y += 18;
		addSlotToContainer(new Slot(tileentity, 3, x, y));

		// output slot
		x = 107;
		y = 36;
		this.addSlotToContainer(new SlotMetallurgicalOvenOutput(player.player, tileentity, 4, x, y));
	}

	@Override
	@SideOnly(Side.CLIENT)
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
			if (this.cookTime != this.tileentity.getField(0))
				listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if (this.totalCookTime != this.tileentity.getField(1))
				listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if (this.energy != this.tileentity.getField(2))
				listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
		}
		this.cookTime = this.tileentity.getField(0);
		this.totalCookTime = this.tileentity.getField(1);
		this.energy = this.tileentity.getField(2);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.tileentity.isUsableByPlayer(playerIn);
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

			if (index == 0 || index == 1 || index == 2 || index == 3)
			{
				if (!this.mergeItemStack(itemstack1, index, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			if (index == 4)
			{
				if (!this.mergeItemStack(itemstack1, index, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 4, false))
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
