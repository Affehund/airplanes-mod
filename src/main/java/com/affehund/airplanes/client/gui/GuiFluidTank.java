package com.affehund.airplanes.client.gui;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.containers.ContainerFluidTank;
import com.affehund.airplanes.common.tileentities.TileEntityFluidTank;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

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
public class GuiFluidTank extends GuiContainer
{
	private final TileEntityFluidTank tileentity;
	private GuiBasicTank tank;

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/gui/fluid_tank.png");

	public GuiFluidTank(InventoryPlayer player, TileEntityFluidTank tileentity)
	{
		super(new ContainerFluidTank(player, tileentity));
		this.tileentity = tileentity;
		this.tank = new GuiBasicTank(tileentity.getTank(), 79, 11, 63, 18);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		int currentTank = (tileentity.getTank().getFluidAmount() * 100) / (tileentity.getTank().getCapacity());
		if (this.tileentity.getTank().getFluid() != null)
		{
			this.tank.drawGuiFluidBar(this, currentTank);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(guiLeft + 79, guiTop + 11, 177, 1, 18, 63);
		if (this.tank.inTank(this, mouseX, mouseY))
		{
			this.drawHoveringText(this.tank.getTankTooltip(), mouseX, mouseY, this.fontRenderer);
		}
	}
}
