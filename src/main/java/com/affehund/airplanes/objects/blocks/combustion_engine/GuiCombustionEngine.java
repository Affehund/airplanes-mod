package com.affehund.airplanes.objects.blocks.combustion_engine;

import com.affehund.airplanes.AirplanesConstants;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCombustionEngine extends GuiContainer 
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(AirplanesConstants.MODID + ":textures/gui/combustion_engine.png");
	private final InventoryPlayer player;
	private final TileEntityCombustionEngine tileentity;

	public GuiCombustionEngine(InventoryPlayer player, TileEntityCombustionEngine tileentity) 

	{
		super(new ContainerCombustionEngine(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
		this.fontRenderer.drawString(Integer.toString(this.tileentity.getEnergyStored()), 115, 72, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		

		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 113, this.guiTop + 32, 176, 14, l + 1, 16);

		int k = this.getEnergyStoredScaled(75);
		this.drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 7, 176, 32, 16, 76 - k);
		
		
	}

	private int getEnergyStoredScaled(int pixels)
	{
		int i = this.tileentity.getEnergyStored();
		int j = this.tileentity.getMaxEnergyStored();
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}

	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileentity.cookTime;
		return i != 0 ? i * pixels / 25 : 0;
	}
}