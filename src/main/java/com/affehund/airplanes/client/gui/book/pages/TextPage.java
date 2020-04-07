/**
 * 
 */
package com.affehund.airplanes.client.gui.book.pages;

import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;

import net.minecraft.client.gui.FontRenderer;
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

public class TextPage extends DefaultPage
{
	private final String text;
	private final String title2;
	private final String text2;

	public TextPage(String title, String text, String title2, String text2)
	{
		super(title);
		this.text = text;
		this.title2 = title2;
		this.text2 = text2;
	}

	@Override
	protected void draw(GuiBookOfAirplanes gui, FontRenderer fontRenderer, RenderItem itemRenderer, int xOffset,
			int yOffset)
	{
		fontRenderer.drawSplitString(this.text, xOffset + 18, yOffset + 30, 96, 0);
		fontRenderer.drawSplitString(this.title2, xOffset + BookLoader.leftSideOffsetX, yOffset + 16, 96, 15054119);
		fontRenderer.drawSplitString(this.text2, xOffset + BookLoader.leftSideOffsetX, yOffset + 30, 96, 0);
	}
}
