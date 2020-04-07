package com.affehund.airplanes.core.utils;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
public class ItemStackUtils
{
	public static boolean isItemFuzzyEqual(ItemStack stack1, ItemStack stack2)
	{
		if (isSameOreDictStack(stack1, stack2))
		{
			return true;
		}
		if (stack1.getItem() != stack2.getItem())
			return false;
		return stack1.getItemDamage() == stack2.getItemDamage()
				|| stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE
				|| stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE;
	}

	public static boolean isSameOreDictStack(ItemStack stack1, ItemStack stack2)
	{
		int ids[] = OreDictionary.getOreIDs(stack1);
		for (int id : ids)
		{
			String name = OreDictionary.getOreName(id);
			List<ItemStack> oreDictStacks = OreDictionary.getOres(name);
			for (ItemStack oreDictStack : oreDictStacks)
			{
				if (OreDictionary.itemMatches(stack2, oreDictStack, false))
				{
					return true;
				}
			}
		}
		return false;
	}
}
