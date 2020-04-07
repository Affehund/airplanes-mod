package com.affehund.airplanes.client.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
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
public class ButtonInfoWidget extends GuiButton
{
	public static ResourceLocation texture = new ResourceLocation(
			Reference.MODID + ":textures/gui/widgets.png");
	private String text;

	public ButtonInfoWidget(int parButtonId, int parPosX, int parPosY, String text)
	{
		super(parButtonId, parPosX, parPosY, 16, 16, text);
		this.text = text;
	}

	@Override
	public void drawButton(Minecraft mc, int parX, int parY, float partialTicks)
	{
		if (visible)
		{
			boolean isButtonHovered = parX >= this.x && parY >= this.y && parX < this.x + this.width
					&& parY < this.y + this.height;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(texture);
			if (isButtonHovered)
			{

			}
			drawTexturedModalRect(this.x, this.y, 0, 0, 16, 16);
		}
		super.drawButton(mc, parX, parY, partialTicks);
	}
}
