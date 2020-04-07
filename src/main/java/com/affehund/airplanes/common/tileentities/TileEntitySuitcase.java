package com.affehund.airplanes.common.tileentities;

import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.util.Constants;

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
public class TileEntitySuitcase extends InventoryBasic
{
	private final ItemStack stack;
	private static int size = 18;

	public TileEntitySuitcase(ItemStack stack)
	{
		super("Suitcase", false, size);
		this.stack = stack;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentTranslation("container.suitcase");
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		readFromNBT();
		super.openInventory(player);
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		writeToNBT();
		super.closeInventory(player);
	}

	@Override
	public void markDirty()
	{
		writeToNBT();
		super.markDirty();
	}

	public void writeToNBT()
	{
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null)
		{
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}

		NBTTagList list = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++)
		{
			ItemStack stack = getStackInSlot(i);

			if (stack != null)
			{
				NBTTagCompound slotTag = new NBTTagCompound();
				slotTag.setInteger("slotIndex", i);
				stack.writeToNBT(slotTag);
				list.appendTag(slotTag);
			}
		}
		tag.setTag("inventory", list);

	}

	public void readFromNBT()
	{
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null)
		{
			writeToNBT();
			return;
		}

		NBTTagList list = tag.getTagList("inventory", Constants.NBT.TAG_COMPOUND);
		if (list == null)
		{
			writeToNBT();
			return;
		}

		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound slotTag = list.getCompoundTagAt(i);
			int index = slotTag.getInteger("slotIndex");
			ItemStack stack = new ItemStack(slotTag);
			setInventorySlotContents(index, stack);
		}
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if(stack.getItem() != ItemInit.SUITCASE) return true;
		return false;
	}
}
