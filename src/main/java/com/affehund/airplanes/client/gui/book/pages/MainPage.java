package com.affehund.airplanes.client.gui.book.pages;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.util.text.TextFormatting;

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
public class MainPage extends DefaultPage
{
	private final String bookName;
	private String edition;
	private final String color;
	private final String text;
	private final String categoryTitle;
	private final ChapterPage[] categories;

	protected MainPage(String bookName, String edition, String color, String text, String categoryTitle,
			ChapterPage... categories)
	{
		super(null);
		this.bookName = bookName;
		this.edition = edition + " Edition";
		this.text = text;
		this.color = color;
		this.categoryTitle = categoryTitle;
		this.categories = categories;
	}

	public String getEdition()
	{
		return this.edition;
	}
	
    public void setEdition(String edition) {
        this.edition = edition;
    }

	@Override
	protected void draw(GuiBookOfAirplanes gui, FontRenderer fontRenderer, RenderItem itemRenderer, int xOffset,
			int yOffset)
	{
		// banner for the side of the main page
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(GuiBookOfAirplanes.bookPageTexture);
		GuiBookOfAirplanes guiBook = new GuiBookOfAirplanes();
		guiBook.drawTexturedModalRect(xOffset - 8, yOffset + 12, 0, 200, 138, 31);

		// draw strings and chapters
		drawStrings(gui, fontRenderer, itemRenderer, xOffset, yOffset);
		drawChapters(gui, fontRenderer, itemRenderer, xOffset, yOffset);
	}

	private void drawStrings(GuiBookOfAirplanes gui, FontRenderer fontRenderer, RenderItem itemRenderer, int xOffset,
			int yOffset)
	{
		fontRenderer.drawStringWithShadow(this.bookName, xOffset + 18, yOffset + 15, Integer.parseInt(color, 16));
		fontRenderer.drawStringWithShadow(TextFormatting.GOLD + this.edition, xOffset + 18, yOffset + 25, 0);
		fontRenderer.drawSplitString(this.text, xOffset + 18, yOffset + 40, 96, 0);
		fontRenderer.drawSplitString(this.categoryTitle, xOffset + BookLoader.leftSideOffsetX, yOffset + 16, 96, 0);
	}
	

	private void drawChapters(GuiBookOfAirplanes gui, FontRenderer fontRenderer, RenderItem itemRenderer, int xOffset,
			int yOffset)
	{
		GlStateManager.pushAttrib();
		GlStateManager.disableDepth();
		RenderHelper.enableGUIStandardItemLighting();
		int x = xOffset + BookLoader.leftSideOffsetX - 5;
		int y = yOffset + 35;
		for (ChapterPage categories : this.categories)
		{
			x += 5 + categories.drawChapterButton(fontRenderer, itemRenderer, x, y);
			if (x > xOffset + BookLoader.leftSideOffsetX + 90)
			{
				x = xOffset + BookLoader.leftSideOffsetX - 5;
				y += 20 + fontRenderer.FONT_HEIGHT;
			}
		}
		RenderHelper.disableStandardItemLighting();
		GlStateManager.enableDepth();
		GlStateManager.popAttrib();
	}

	@Override
	public void init(List<GuiButton> buttonList, int currentId, int xOffset, int yOffset)
	{
		int x = xOffset + BookLoader.leftSideOffsetX;
		int y = yOffset + 35;
		int width = 55;
		int height = 16 + 12;
		for (ChapterPage categories : this.categories)
		{
			buttonList.add(new ChapterPage.ChapterButton(currentId, x, y, width, height, categories.getId()));
			currentId++;
			x += width;
			if (x > xOffset + BookLoader.leftSideOffsetX + 90)
			{
				x = xOffset + BookLoader.leftSideOffsetX;
				y += 20 + 12;
			}
		}
	}

	public String getDestination(ChapterPage.ChapterButton button)
	{
		for (ChapterPage categories : this.categories)
		{
			if (categories.getId().equalsIgnoreCase(button.getName()))
			{
				return categories.getId();
			}
		}
		throw new IllegalArgumentException("Unknown button \'" + button.getName().toLowerCase() + "\'");
	}
}
