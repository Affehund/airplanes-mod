package com.affehund.airplanes.core.compat.jei;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.gui.GuiMetallurgicalOven;
import com.affehund.airplanes.common.containers.ContainerMetallurgicalOven;
import com.affehund.airplanes.common.recipes.metallurgical_oven.MetallurgicalOvenRegistry;
import com.affehund.airplanes.core.compat.jei.metallurgical_oven.MetallurgicalOvenRecipeCategory;
import com.affehund.airplanes.core.compat.jei.metallurgical_oven.MetallurgicalOvenRecipeWrapper;
import com.affehund.airplanes.core.init.BlockInit;
import com.affehund.airplanes.core.init.ItemInit;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.resources.I18n;
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
@JEIPlugin
public class JeiPlugin implements IModPlugin
{
	public static IJeiHelpers jeiHelper;
	public static IModRegistry modRegistry;
	public static final String METALLURGICAL_OVEN = Reference.MODID + ".metallurgical_oven";

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		IJeiHelpers helpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = helpers.getGuiHelper();

		registry.addRecipeCategories(new MetallurgicalOvenRecipeCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry)
	{
		/*
		 * Metallurgical Oven
		 */
		registry.addRecipeCatalyst(new ItemStack(BlockInit.METALLURGICAL_OVEN), JeiPlugin.METALLURGICAL_OVEN);
		registry.addRecipes(MetallurgicalOvenRegistry.getInstance().getAllRecipes(), JeiPlugin.METALLURGICAL_OVEN);
		registry.handleRecipes(MetallurgicalOvenRegistry.StandardMetallurgicalOvenRecipe.class, MetallurgicalOvenRecipeWrapper::new, JeiPlugin.METALLURGICAL_OVEN);
		registry.addRecipeClickArea(GuiMetallurgicalOven.class, 68, 36, 16, 23, JeiPlugin.METALLURGICAL_OVEN);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerMetallurgicalOven.class,
				JeiPlugin.METALLURGICAL_OVEN, 0, 4, 5, 36);

		/*
		 * Blacklist items in jei
		 */
		registry.getJeiHelpers().getIngredientBlacklist()
				.addIngredientToBlacklist(new ItemStack(BlockInit.ALUMINUM_BLOCK, 1, OreDictionary.WILDCARD_VALUE));

		/*
		 * Adding descriptions for items in jei
		 */
		this.addJeiDescription(registry, new ItemStack(ItemInit.AIRPLANES_HAMMER), ItemStack.class, "airplanes.jei.hammer");
		this.addJeiDescription(registry, new ItemStack(BlockInit.COMBUSTION_ENGINE), ItemStack.class, "airplanes.jei.combustion");
	}

	@SuppressWarnings("deprecation")
	public void addJeiDescription(IModRegistry registry, ItemStack stack, Class<ItemStack> ingredientClass, String description)
	{
		registry.addIngredientInfo(stack, ingredientClass,  I18n.format(description));
		AirplanesMod.log.info("Added description for: " + stack.getDisplayName() + " (" + description + ")");
	}
}
