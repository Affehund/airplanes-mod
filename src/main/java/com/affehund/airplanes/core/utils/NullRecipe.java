package com.affehund.airplanes.core.utils;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

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
public class NullRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
	private final ItemStack output;

	public NullRecipe(ItemStack output)
	{
		this.output = output;
	}

	public static IRecipe from(IRecipe other)
	{
		return new NullRecipe(other.getRecipeOutput()).setRegistryName(other.getRegistryName());
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		return false;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return output;
	}
}