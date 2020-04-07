package com.affehund.airplanes.core.init;

import java.util.Iterator;
import java.util.Map;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.recipes.metallurgical_oven.IMetallurgicalOvenRegistry;
import com.affehund.airplanes.common.recipes.metallurgical_oven.MetallurgicalOvenRegistry;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

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
public class RecipeInit
{
	public static IMetallurgicalOvenRegistry getMetallurgicalOvenRegistry()
	{
		return MetallurgicalOvenRegistry.getInstance();
	}
	
	/*
	 * init recipes
	 */
	public static void init()
	{
		/*
		 * add metallurgical oven recipes
		 */
		IMetallurgicalOvenRegistry metOven = getMetallurgicalOvenRegistry();
		
		metOven.addRecipe(new ItemStack(ItemInit.STEEL_INGOT, 5), new ItemStack(Items.IRON_INGOT, 4),
				new ItemStack(Items.COAL, 1));
		metOven.addRecipe(new ItemStack(ItemInit.SILICON, 1), new ItemStack(ItemInit.RAW_SILICON, 3),
				new ItemStack(Items.COAL, 2));
		
		/*
		 * add smelting recipes
		 */
		GameRegistry.addSmelting(BlockInit.BAUXITE_ORE, new ItemStack(ItemInit.ALUMINUM_INGOT, 1), 0.3F);
		GameRegistry.addSmelting(BlockInit.COPPER_ORE, new ItemStack(ItemInit.COPPER_INGOT, 1), 0.3F);
		GameRegistry.addSmelting(BlockInit.TIN_ORE, new ItemStack(ItemInit.TIN_INGOT, 1), 0.3F);
		
		GameRegistry.addSmelting(Items.IRON_INGOT, new ItemStack(ItemInit.STEEL_INGOT, 1), 0.2F);
		GameRegistry.addSmelting(ItemInit.RAW_SILICON, new ItemStack(ItemInit.SILICON, 1), 2F);	
		
		
		/*
		 * calling removeSmeltingRecipes
		 */
		removeSmeltingRecipes();
	}
	
	
	/*
	 * Remove smelting recipes
	 */
	public static void removeSmeltingRecipes()
	{
		removeSmelting(new ItemStack(Items.IRON_INGOT), Reference.MODID);
	}

	public static void removeSmelting(Item result, int stacksize, int meta, String modid)
	{
		ItemStack resultStack = new ItemStack(result, stacksize, meta);
		removeSmelting(resultStack, modid);
	}

	public static void removeSmelting(ItemStack result, String modid)
	{
		ItemStack recipeResult = null;
		Map<ItemStack,ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		Iterator<ItemStack> iterator = recipes.keySet().iterator();
		while(iterator.hasNext())
		{
			ItemStack furnaceRecipe = iterator.next();
			recipeResult = recipes.get(furnaceRecipe);
			if(ItemStack.areItemStacksEqual(result, recipeResult)) 
			{
				AirplanesMod.log.info("Removed furnace recipe " + furnaceRecipe.getDisplayName().toLowerCase() + " -> " + recipeResult.getDisplayName());
				iterator.remove();
			}
		}
	}

	public static void removeRecipe(IForgeRegistryModifiable<?> modRegistry, ResourceLocation recipe, String modid)
	{
		@SuppressWarnings("unused")
		IRecipe p = (IRecipe)modRegistry.getValue(recipe);
		modRegistry.remove(recipe);
	}
}
