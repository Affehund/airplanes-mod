package com.affehund.airplanes.client.gui.book.pages;

import java.util.List;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderItem;

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
public abstract class DefaultPage
{

	private final String title;

	protected DefaultPage(String title)
	{
		this.title = title;
	}

	public void drawPage(GuiBookOfAirplanes gui, FontRenderer fontRenderer, RenderItem itemRenderer, int xOffset,
			int yOffset)
	{
		if (this.title != null)
		{
			fontRenderer.drawStringWithShadow(this.title, xOffset + 18, 16 + yOffset, 15054119);
		}

		try
		{
			this.draw(gui, fontRenderer, itemRenderer, xOffset, yOffset);
		} catch (Exception e)
		{
			AirplanesMod.log.error("Error while drawing page!");
			e.printStackTrace();
		}
	}

	public void init(List<GuiButton> buttonList, int currentId, int xOffset, int yOffset)
	{
	}

	protected abstract void draw(GuiBookOfAirplanes gui, FontRenderer fontRenderer, RenderItem itemRenderer,
			int xOffset, int yOffset);
}
