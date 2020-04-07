package com.affehund.airplanes.client.gui.book.pages;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderItem;
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
public class ChapterPage
{
	private final String name;
	private final String id;
	private final ItemStack icon;

	public ChapterPage(String name, String id, ItemStack icon)
	{
		this.name = name;
		this.id = id;
		this.icon = icon;
	}

	public String getName()
	{
		return name;
	}

	public String getId()
	{
		return id;
	}

	// buttons
	public int drawChapterButton(FontRenderer fontRenderer, RenderItem itemRenderer, int x, int y)
	{
		int xOffset = 25;
		int yOffset = 16;
		int width = fontRenderer.getStringWidth(this.name);
		itemRenderer.renderItemIntoGUI(this.icon, x + xOffset - 8, y);
		fontRenderer.drawString(this.name, x + xOffset - (width / 2), y + yOffset, 0);
		return (xOffset * 2) - 8 + 5;
	}

	@SideOnly(Side.CLIENT)
	public static class ChapterButton extends GuiButton
	{
		private final String name;

		public ChapterButton(int parButtonId, int parPosX, int parPosY, int width, int height, String name)
		{
			super(parButtonId, parPosX, parPosY, width, height, "");
			this.name = name;
		}

		@Override
		public void drawButton(Minecraft mc, int parX, int parY, float partialTicks)
		{

		}

		public String getName()
		{
			return name;
		}
	}
}
