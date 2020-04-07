/**
 * 
 */
package com.affehund.airplanes.client.gui.book.pages;

import java.util.Collection;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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

public class RecipeHelper
{
	/*
	 * helper for oredict stuff
	 */
	public static ItemStack getItemStackFromOreItem(Object oreItem)
	{
		ItemStack itemStack = null;
		if (oreItem instanceof String)
		{
			itemStack = OreDictionary.getOres((String) oreItem).get(0);
		} else if (oreItem instanceof ItemStack)
		{
			itemStack = (ItemStack) oreItem;
		} else if (oreItem instanceof Item)
		{
			itemStack = new ItemStack((Item) oreItem);
		} else if (oreItem instanceof Block)
		{
			itemStack = new ItemStack((Block) oreItem);
		} else if (oreItem instanceof Collection)
		{
			itemStack = (ItemStack) ((Collection<?>) oreItem).toArray()[0];
		}
		return itemStack;
	}

	/*
	 * helper for smelting recipes
	 */
	public static void renderSmeltingRecipe(RenderItem itemRenderer, Item item, int x, int y)
	{
		ItemStack ingredient = null;
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		for (ItemStack recipeIngredient : recipes.keySet())
		{
			if (recipes.get(recipeIngredient).getItem().equals(item))
			{
				ingredient = recipeIngredient;
			}
		}
		if (ingredient == null)
		{
			return;
		}

		/*
		 * 1) input 2) fuel 3) output
		 */
		itemRenderer.renderItemIntoGUI(new ItemStack(ingredient.getItem()), x - 17, y);
		itemRenderer.renderItemIntoGUI(new ItemStack(Items.COAL), x - 17, y + 36);
		itemRenderer.renderItemIntoGUI(new ItemStack(item), x + 60 - 17, y + 18);
	}

	/*
	 * helper for crafting recipes
	 */
	public static void renderCraftingRecipe(RenderItem itemRenderer, Item item, int x, int y)
	{
		IRecipe recipe = null;
		for (IRecipe iRecipe : CraftingManager.REGISTRY)
		{
			if (iRecipe != null && iRecipe.getRecipeOutput() != null && iRecipe.getRecipeOutput().getItem() != null
					&& iRecipe.getRecipeOutput().getItem().equals(item))
			{
				recipe = iRecipe;
			}
		}

		int xOffset = 0;
		int yOffset = 0;

		/*
		 * Shapeless recipes
		 */
		if (recipe instanceof ShapelessRecipes)
		{
			ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;

			for (Ingredient recipeItem : shapelessRecipe.recipeItems)
			{
				if (recipeItem == null)
				{
					continue;
				}
				itemRenderer.renderItemIntoGUI(recipeItem.getMatchingStacks()[0], x + xOffset, y + yOffset);
				xOffset += 18;
				if (xOffset == 54)
				{
					xOffset = 0;
					yOffset += 18;
				}
			}
			itemRenderer.renderItemIntoGUI(shapelessRecipe.getRecipeOutput(), x + 89, y + 18);
		}
		
		/*
		 * Shaped recipes
		 */
		else if (recipe instanceof ShapedRecipes)
		{
			ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;

			for (Ingredient recipeItem : shapedRecipe.recipeItems)
			{
				if (recipeItem == null)
				{
					continue;
				}
				itemRenderer.renderItemIntoGUI(recipeItem.getMatchingStacks()[0], x + xOffset, y + yOffset);
				xOffset += 18;
				if (xOffset == 54)
				{
					xOffset = 0;
					yOffset += 18;
				}
			}
			itemRenderer.renderItemIntoGUI(shapedRecipe.getRecipeOutput(), x + 89, y + 18);
		}

		/*
		 * Shaped ore recipes
		 */
		else if (recipe instanceof ShapedOreRecipe)
		{
			ShapedOreRecipe shapedRecipe = (ShapedOreRecipe) recipe;

			for (Object oreItem : shapedRecipe.getIngredients())
			{
				ItemStack recipeItem = RecipeHelper.getItemStackFromOreItem(oreItem);
				if (recipeItem == null)
				{
					continue;
				}
				itemRenderer.renderItemIntoGUI(new ItemStack(recipeItem.getItem()), x + xOffset, y + yOffset);
				xOffset += 18;
				if (xOffset == 54)
				{
					xOffset = 0;
					yOffset += 18;
				}
				itemRenderer.renderItemIntoGUI(shapedRecipe.getRecipeOutput(), x + 89, y + 18);
			}
		}

		/*
		 * Shapeless ore recipes
		 */
		else if (recipe instanceof ShapelessOreRecipe)
		{
			ShapelessOreRecipe shapelessRecipe = (ShapelessOreRecipe) recipe;

			for (Object oreItem : shapelessRecipe.getIngredients())
			{
				ItemStack recipeItem = RecipeHelper.getItemStackFromOreItem(oreItem);

				if (recipeItem == null)
				{
					continue;
				}

				itemRenderer.renderItemIntoGUI(new ItemStack(recipeItem.getItem()), x + xOffset, y + yOffset);
				xOffset += 18;
				if (xOffset == 53)
				{
					xOffset = 0;
					yOffset += 18;
				}
				itemRenderer.renderItemIntoGUI(shapelessRecipe.getRecipeOutput(), x + 89, y + 18);
			}
		}
	}
}
