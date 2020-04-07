package com.affehund.airplanes.core.compat.jei;

import javax.annotation.Nonnull;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.recipes.metallurgical_oven.MetallurgicalOvenRegistry.StandardMetallurgicalOvenRecipe;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

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
public class MetallurgicalOvenRecipeHandler implements IRecipeCategory<MetallurgicalOvenWrapper>
{

	private final IDrawable background;
	protected final IDrawableAnimated animatedArrow;

	public MetallurgicalOvenRecipeHandler(IGuiHelper helper)
	{
		ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/metallurgical_oven.png");
		background = helper.createDrawable(location, 4, 4, 146, 70);
		IDrawableStatic staticArrow = helper.createDrawable(location, 176, 0, 23, 16);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public String getUid()
	{
		return JeiPlugin.METALLURGICAL_OVEN;
	}

	@Override
	public String getTitle()
	{
		return "Metallurgical Oven";
	}

	@Override
	public String getModName()
	{
		return Reference.NAME;
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft)
	{
		animatedArrow.draw(minecraft, 64, 32);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MetallurgicalOvenWrapper recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStackGroup = recipeLayout.getItemStacks();
		//inputs
		guiItemStackGroup.init(0, true, 12, 21);
		guiItemStackGroup.init(1, true, 12, 39);
		guiItemStackGroup.init(2, true, 30, 21);
		guiItemStackGroup.init(3, true, 30, 39);
		for (int i = 0; i < ingredients.getInputs(ItemStack.class).get(0).size(); i++)
		{
			guiItemStackGroup.set(i, ingredients.getInputs(ItemStack.class).get(0).get(i));
		}
		//outputs
		guiItemStackGroup.init(4, false, 102, 31);
		guiItemStackGroup.set(4, ingredients.getOutputs(ItemStack.class).get(0));
	}

}

class MetallurgicalOvenWrapperFactory implements IRecipeWrapperFactory<StandardMetallurgicalOvenRecipe>
{

	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull StandardMetallurgicalOvenRecipe recipe)
	{
		return new MetallurgicalOvenWrapper(recipe);
	}
}

class MetallurgicalOvenWrapper implements IRecipeWrapper
{

	protected final NonNullList<ItemStack> inputs;
	protected final ItemStack output;

	public MetallurgicalOvenWrapper(StandardMetallurgicalOvenRecipe recipe)
	{
		this.inputs = recipe.getRequiredItems();
		this.output = recipe.getCraftingResult(inputs);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void getIngredients(IIngredients ingredients)
	{

		ingredients.setInput(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}
}
