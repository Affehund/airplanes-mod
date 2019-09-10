package com.affehund.airplanes.client.gui;

import java.util.Collections;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.containers.ContainerEnergyStorageBlock;
import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiEnergyStorageBlock extends GuiContainer 
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/energy_storage_block.png");
	private final InventoryPlayer player;
	private final TileEntityEnergyStorageBlock tileentity;

	public GuiEnergyStorageBlock(InventoryPlayer player, TileEntityEnergyStorageBlock tileentity) 
	{
		super(new ContainerEnergyStorageBlock(player, tileentity));
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
		
		String tileName = this.tileentity.getDisplayName().getUnformattedText();

		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 8421504);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 8421504);
		
		if(this.tileentity.energy <  10000)
		{
			this.fontRenderer.drawString(this.tileentity.energy + " FE", 95, 70, 8421504);
		}
		
		if(this.tileentity.energy >=  10000)
		{
			this.fontRenderer.drawString(String.format("%.2f", this.tileentity.energy / 1_000F ) + " kFE", 95, 70, 8421504);
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