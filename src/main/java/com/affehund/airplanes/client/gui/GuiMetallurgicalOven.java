package com.affehund.airplanes.client.gui;

import java.util.Collections;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.containers.ContainerMetallurgicalOven;
import com.affehund.airplanes.common.tileentities.TileEntityMetallurgicalOven;
import com.affehund.airplanes.core.config.ConfigAirplanes;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
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
public class GuiMetallurgicalOven extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(
			Reference.MODID + ":textures/gui/metallurgical_oven.png");
	private final InventoryPlayer player;
	private final TileEntityMetallurgicalOven tileentity;

	private ButtonInfoWidget buttonInfoWidget;

	public GuiMetallurgicalOven(InventoryPlayer player, TileEntityMetallurgicalOven tileentity)
	{
		super(new ContainerMetallurgicalOven(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}

//	@Override
//	public void initGui()
//	{
//		super.initGui();
//		int xOffset = this.width / 2 - 192 / 2 + 10;
//		int yOffset = this.height / 2 - 176 / 2 + 10;
//
//		this.buttonInfoWidget = new ButtonInfoWidget(1, xOffset, yOffset, "");
//		this.buttonList.add(buttonInfoWidget);
//	}
//
//	@Override
//	protected void actionPerformed(GuiButton button)
//	{
//		if (button == buttonInfoWidget)
//		{
//			System.out.println("pressed button");
//		}
//	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, ticks);
		this.renderHoveredToolTip(mouseX, mouseY);
		if (mouseX > guiLeft + 150 && mouseX < guiLeft + 169 && mouseY > guiTop + 5 && mouseY < guiTop + 82)
		{
			drawHoveringText(Collections.singletonList(
					tileentity.energy + "/" + tileentity.capacity + " " + ConfigAirplanes.Global.Energy.ENERGY_UNIT),
					mouseX, mouseY, fontRenderer);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String tileName = this.tileentity.getDisplayName().getUnformattedText();

		this.fontRenderer.drawString(tileName, this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2, 6,
				8421504);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2,
				8421504);

		if (this.tileentity.energy < 10000)
		{
			this.fontRenderer.drawString(this.tileentity.energy + " " + ConfigAirplanes.Global.Energy.ENERGY_UNIT, 95,
					74, 8421504);
		}
		if (this.tileentity.energy >= 10000)
		{
			this.fontRenderer.drawString(String.format("%.1f", this.tileentity.energy / 1_000F) + " k"
					+ ConfigAirplanes.Global.Energy.ENERGY_UNIT, 95, 74, 8421504);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 68, this.guiTop + 36, 176, 0, l + 1, 16);

		int k = this.getEnergyStoredScaled(76);
		drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 7, 176, 17, 16, 76 - k);
	}

	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileentity.getCookTime();
		int j = this.tileentity.getTotalCookTime();
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	private int getEnergyStoredScaled(int pixels)
	{
		int f = this.tileentity.energy;
		int g = this.tileentity.capacity;
		return f != 0 && g != 0 ? f * pixels / g : 0;
	}
}
