package com.affehund.airplanes.core.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

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
public class RenderUtils
{
	public static void renderGuiTank(FluidTank tank, double x, double y, double height, double width)
	{
		renderGuiTank(tank.getFluid(), tank.getCapacity(), tank.getFluidAmount(), x, y, height, width);
	}

	public static void renderGuiTank(FluidStack fluid, int capacity, int amount, double x, double y, double height,
			double width)
	{
		if (fluid == null || fluid.getFluid() == null || amount <= 0)
		{
			return;
		}

		TextureAtlasSprite icon = Minecraft.getMinecraft().getTextureMapBlocks()
				.getTextureExtry(fluid.getFluid().getStill().toString());
		if (icon == null)
		{
			icon = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
		}
		int renderAmount = (int) Math.max(Math.min(height, amount * height / capacity), 1);
		int posY = (int) (y + height - renderAmount);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		int color = fluid.getFluid().getColor(fluid);
		GlStateManager.color((color >> 16 & 0xFF) / 255f, (color >> 8 & 0xFF) / 255f, (color & 0xFF) / 255f);
		GlStateManager.disableBlend();
		for (int i = 0; i < width; i += 16)
		{
			for (int j = 0; j < renderAmount; j += 16)
			{
				int drawWidth = (int) Math.min(width - i, 16);
				int drawHeight = Math.min(renderAmount - j, 16);
				int drawX = (int) (x + i);
				int drawY = posY + j;
				double minU;
				double minV;
				{
					minU = icon.getMinU();
					minV = icon.getMinV();
				}
				double maxU = icon.getMaxU();
				double maxV = icon.getMaxV();
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder tes = tessellator.getBuffer();
				tes.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				tes.pos(drawX, drawY + drawHeight, 0).tex(minU, minV + (maxV - minV) * drawHeight / 16F).endVertex();
				tes.pos(drawX + drawWidth, drawY + drawHeight, 0)
						.tex(minU + (maxU - minU) * drawWidth / 16F, minV + (maxV - minV) * drawHeight / 16F)
						.endVertex();
				tes.pos(drawX + drawWidth, drawY, 0).tex(minU + (maxU - minU) * drawWidth / 16F, minV).endVertex();
				tes.pos(drawX, drawY, 0).tex(minU, minV).endVertex();
				tessellator.draw();
			}
		}
		GlStateManager.enableBlend();
		GlStateManager.color(1, 1, 1);
	}
}
