package com.affehund.airplanes.client.tesr;

import org.lwjgl.opengl.GL11;

import com.affehund.airplanes.common.tileentities.TileEntityFluidTank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
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
public class TESRFluidTank extends TileEntitySpecialRenderer<TileEntityFluidTank>
{
	public static final float TANK_THICKNESS = 0.0625f;
	
	@Override
	public void render(TileEntityFluidTank tileEntity, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha)
	{
		GlStateManager.pushMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disableBlend();
		GlStateManager.translate((float) x, (float) y, (float) z);

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		renderFluid(tileEntity);
		GlStateManager.popMatrix();
	}

	private void renderFluid(TileEntityFluidTank tank)
	{
		if (tank == null)
		{
			return;
		}

		FluidStack fluid = tank.getTank().getFluid();
		if (fluid == null)
		{
			return;
		}

		Fluid renderFluid = fluid.getFluid();
		if (renderFluid == null)
		{
			return;
		}

		float scale = (1.0f - TANK_THICKNESS / 2 - TANK_THICKNESS) * fluid.amount / (tank.getTank().getCapacity());

		if (scale > 0.0f)
		{
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder renderer = tessellator.getBuffer();
			ResourceLocation still = renderFluid.getStill();
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(still.toString());

			net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
			GlStateManager.color(1, 1, 1, .5f);
			renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

			float u1 = sprite.getMinU();
			float v1 = sprite.getMinV();
			float u2 = sprite.getMaxU();
			float v2 = sprite.getMaxV();

			// Top
			renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v2)
					.color(255, 255, 255, 128).endVertex();
			renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2)
					.color(255, 255, 255, 128).endVertex();
			renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1)
					.color(255, 255, 255, 128).endVertex();

			// Bottom
			renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128)
					.endVertex();

			// Sides
			renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v1)
					.color(255, 255, 255, 128).endVertex();
			renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v1)
					.color(255, 255, 255, 128).endVertex();

			renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1)
					.color(255, 255, 255, 128).endVertex();
			renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128)
					.endVertex();

			renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v1)
					.color(255, 255, 255, 128).endVertex();
			renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1)
					.color(255, 255, 255, 128).endVertex();

			renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128)
					.endVertex();
			renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v1)
					.color(255, 255, 255, 128).endVertex();
			
			tessellator.draw();
			net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
		}
	}
}