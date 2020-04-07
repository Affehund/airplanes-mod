package com.affehund.airplanes.common.recipes.metallurgical_oven;

import java.util.ArrayList;
import java.util.List;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.utils.ItemStackUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
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
public class MetallurgicalOvenRegistry implements IMetallurgicalOvenRegistry
{
	private static MetallurgicalOvenRegistry INSTANCE = new MetallurgicalOvenRegistry();

	private final List<IMetallurgicalOvenRecipe> metallurgicalOvenRecipes = new ArrayList<IMetallurgicalOvenRecipe>();

	private MetallurgicalOvenRegistry()
	{

	}

	public static MetallurgicalOvenRegistry getInstance()
	{
		return INSTANCE;
	}

	@Override
	public void addRecipe(IMetallurgicalOvenRecipe recipe)
	{
		metallurgicalOvenRecipes.add(recipe);
		AirplanesMod.log.info("Added recipe: " + recipe);
	}

	/*
	 * getter for jei plugin
	 */
	public List<IMetallurgicalOvenRecipe> getAllRecipes()
	{
		return metallurgicalOvenRecipes;
	}

	@Override
	public void addRecipe(ItemStack craftingResult, Object... requiredItems)
	{
		if (craftingResult == null || craftingResult.getItem() == null)
			throw new NullPointerException(
					"Can't register a metallurgical oven recipe with a null output stack or item");
		if (craftingResult.isEmpty())
			throw new NullPointerException(
					"Can't register a metallurgical oven recipe with a invalid output stack or item");
		NonNullList<ItemStack> requiredStacks = NonNullList.withSize(requiredItems.length, ItemStack.EMPTY);
		for (int i = 0; i < requiredStacks.size(); i++)
		{
			if (requiredItems[i] instanceof ItemStack)
			{
				requiredStacks.set(i, (ItemStack) requiredItems[i]);
			} else if (requiredItems[i] instanceof Item)
			{
				requiredStacks.set(i, new ItemStack((Item) requiredItems[i], 1, OreDictionary.WILDCARD_VALUE));
			} else if (requiredItems[i] instanceof Block)
			{
				requiredStacks.set(i, new ItemStack(Item.getItemFromBlock((Block) requiredItems[i]), 1,
						OreDictionary.WILDCARD_VALUE));
			} else
			{
				throw new IllegalArgumentException(
						"Metallurgical oven ingredients can only be ItemStack, Item or Block!");
			}
		}
		addRecipe(new StandardMetallurgicalOvenRecipe(craftingResult, requiredStacks));
	}

	public IMetallurgicalOvenRecipe getMatchingRecipe(NonNullList<ItemStack> input, ItemStack outputSlot)
	{

		for (IMetallurgicalOvenRecipe recipe : metallurgicalOvenRecipes)
		{
			if (recipe.matches(input))
			{
				if (outputSlot != null && !outputSlot.isEmpty())
				{// check if we can add the crafting result to the output slot
					ItemStack craftingResult = recipe.getCraftingResult(input);
					if (!ItemStack.areItemStackTagsEqual(outputSlot, craftingResult)
							|| !outputSlot.isItemEqual(craftingResult))
					{
						continue;
					} else if (craftingResult.getCount() + outputSlot.getCount() > outputSlot.getMaxStackSize())
					{
						continue;
					}
				}
				return recipe;
			}
		}
		return null;
	}

	public class StandardMetallurgicalOvenRecipe implements IMetallurgicalOvenRecipe
	{

		private final ItemStack craftingResult;
		private final NonNullList<ItemStack> requiredItems;

		private StandardMetallurgicalOvenRecipe(ItemStack craftingResult, NonNullList<ItemStack> requiredItems)
		{

			if (craftingResult.isEmpty())
				throw new IllegalArgumentException("Metallurgical oven crafting result can't be null!");
			if (requiredItems.size() > 4)
				throw new IllegalArgumentException(
						"There can't be more than 4 crafting ingredients for the metallurgical oven!");
			for (ItemStack requiredItem : requiredItems)
			{
				if (requiredItem.isEmpty())
					throw new NullPointerException("A metallurgical oven crafting ingredient can't be null!");
			}
			for (ItemStack stack : requiredItems)
			{
				for (ItemStack stack2 : requiredItems)
				{
					if (stack != stack2 && ItemStackUtils.isItemFuzzyEqual(stack, stack2))
						throw new IllegalArgumentException(
								"No equivalent metallurgical oven crafting ingredient can be given twice(includes oredict + wildcard).");
				}
			}

			this.craftingResult = craftingResult;
			this.requiredItems = requiredItems;
		}

		@Override
		public boolean matches(NonNullList<ItemStack> input)
		{

			for (ItemStack requiredItem : requiredItems)
			{
				int itemsNeeded = requiredItem.getCount();
				for (ItemStack inputStack : input)
				{
					if (!inputStack.isEmpty() && ItemStackUtils.isItemFuzzyEqual(inputStack, requiredItem))
					{
						itemsNeeded -= inputStack.getCount();
						if (itemsNeeded <= 0)
							break;
					}
				}
				if (itemsNeeded > 0)
					return false;
			}
			return true;
		}

		@Override
		public void useItems(NonNullList<ItemStack> input)
		{

			for (ItemStack requiredItem : requiredItems)
			{
				int itemsNeeded = requiredItem.getCount();
				for (int i = 0; i < input.size(); i++)
				{
					ItemStack inputStack = input.get(i);
					if (!inputStack.isEmpty() && ItemStackUtils.isItemFuzzyEqual(inputStack, requiredItem))
					{
						int itemsSubstracted = Math.min(inputStack.getCount(), itemsNeeded);
						inputStack.setCount(inputStack.getCount() - itemsSubstracted);
						if (inputStack.getCount() <= 0)
							input.set(i, ItemStack.EMPTY);
						itemsNeeded -= itemsSubstracted;
						if (itemsNeeded <= 0)
							break;
					}
				}
				if (itemsNeeded > 0)
					throw new IllegalArgumentException("Error: There are still some items needed.");
			}
		}

		@Override
		public ItemStack getCraftingResult(NonNullList<ItemStack> input)
		{
			return craftingResult;
		}

		/*
		 * getter for jei plugin
		 */
		public NonNullList<ItemStack> getRequiredItems()
		{
			return requiredItems;
		}
	}

	@Override
	public void removeRecipe(ItemStack craftingResult)
	{
		for (IMetallurgicalOvenRecipe recipe : metallurgicalOvenRecipes)
		{
			if (recipe.equals(craftingResult))
			{
				metallurgicalOvenRecipes.remove(recipe);
				AirplanesMod.log.info("Trying to remove " + craftingResult);
			}
		}
	}
}
