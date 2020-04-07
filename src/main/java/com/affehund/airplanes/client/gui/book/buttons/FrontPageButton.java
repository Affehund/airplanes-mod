/**
 * 
 */
package com.affehund.airplanes.client.gui.book.buttons;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */

@SideOnly(Side.CLIENT)
public class FrontPageButton extends GuiButton
{
	public FrontPageButton(int parButtonId, int parPosX, int parPosY)
	{
		super(parButtonId, parPosX, parPosY, 25, 13, "");
	}

	@Override
	public void drawButton(Minecraft mc, int parX, int parY, float partialTicks)
	{
		if (visible)
		{
			boolean isButtonPressed = parX >= this.x && parY >= this.y && parX < this.x + this.width
					&& parY < this.y + this.height;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(GuiBookOfAirplanes.bookPageTexture);
			int textureX = 45;
			int textureY = 177;
			if (isButtonPressed)
			{
				textureY += 12;
			}
			drawTexturedModalRect(this.x, this.y, textureX, textureY, 23, 11);
		}
	}
}
