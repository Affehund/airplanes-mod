package com.affehund.airplanes.client.gui;

import java.util.Collections;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.gui.ProgressBar.ProgressBarDirection;
import com.affehund.airplanes.common.containers.ContainerCombustionEngine;
import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCombustionEngine extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/combustion_engine.png");
	private final InventoryPlayer player;
	private final TileEntityCombustionEngine tileentity;

	public GuiCombustionEngine(InventoryPlayer player, TileEntityCombustionEngine tileentity) 
	{

		super(new ContainerCombustionEngine(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks)
	{
		this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, ticks);
		this.renderHoveredToolTip(mouseX, mouseY);
		if(mouseX > guiLeft + 150 && mouseX < guiLeft + 169 && mouseY > guiTop + 5 && mouseY < guiTop + 82)
		{
			drawHoveringText(Collections.singletonList(tileentity.energy + " FE" + " / " + tileentity.capacity + " FE " ), mouseX, mouseY, fontRenderer);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		float depletedPercent = this.tileentity.burnTime * 0.008333F;
		
		String tileName = this.tileentity.getDisplayName().getUnformattedText();

		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 8421504);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 8421504);

		this.fontRenderer.drawString(String.format("%.1f", depletedPercent) + "%", 67, 35, 2686720);


		if(this.tileentity.energy < 1000000)
		{
			this.fontRenderer.drawString(this.tileentity.energy + " FE", 100, 56, 8965023);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int k = this.getEnergyStoredScaled(75);
		this.drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 7, 176, 31, 16, 75 - k);
	}

	private int getEnergyStoredScaled(int pixels)
	{
		int i = this.tileentity.energy;
		int j = this.tileentity.capacity;
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}
}