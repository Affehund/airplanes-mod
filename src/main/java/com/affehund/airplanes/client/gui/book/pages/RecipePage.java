package com.affehund.airplanes.client.gui.book.pages;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
public class RecipePage extends DefaultPage
{
	private static final ResourceLocation CRAFTING_GRID = new ResourceLocation(
			Reference.MODID + ":textures/gui/book/crafting.png");

	private static final ResourceLocation SMELTING_GRID = new ResourceLocation(
			Reference.MODID + ":textures/gui/book/furnace.png");

	private final String description;
	private final Item item;
	private final RecipeType recipeType;

	public RecipePage(Item item, String description, RecipeType recipeType)
	{
		super(item.getItemStackDisplayName(new ItemStack(item)));
		this.description = description;
		this.item = item;
		this.recipeType = recipeType;
	}

	@Override
	protected void draw(GuiBookOfAirplanes gui, FontRenderer fontRenderer, RenderItem itemRenderer, int xOffset,
			int yOffset)
	{
		GlStateManager.clearColor(0, 0, 0, 0);
		GlStateManager.color(1, 1, 1, 1);

		switch (recipeType)
		{
		case CRAFTING:
			Minecraft.getMinecraft().getTextureManager().bindTexture(CRAFTING_GRID);
			gui.drawTexturedModalRect(xOffset + 12, yOffset + 30, 0, 0, 119, 90);
			break;
		case SMELTING:
			Minecraft.getMinecraft().getTextureManager().bindTexture(SMELTING_GRID);
			gui.drawTexturedModalRect(xOffset + 20, yOffset + 30, 0, 0, 119, 90);
			break;
		default:
			break;
		}

		int x = xOffset + 38;
		int y = yOffset + 41;
		int offset = yOffset + 102;
		fontRenderer.drawSplitString(description, xOffset + 24, offset, 96, 0);
		GlStateManager.pushAttrib();
		GlStateManager.disableDepth();
		RenderHelper.enableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();

		switch (recipeType)
		{
		case CRAFTING:
			RecipeHelper.renderCraftingRecipe(itemRenderer, this.item, x - 24, y);
			break;
		case SMELTING:
			RecipeHelper.renderSmeltingRecipe(itemRenderer, this.item, x, y);
			break;
		default:
			break;
		}
		RenderHelper.disableStandardItemLighting();
		GlStateManager.enableDepth();
		GlStateManager.popAttrib();
	}

	public enum RecipeType {
		CRAFTING, SMELTING
	}
}