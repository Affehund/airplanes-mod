package com.affehund.airplanes.objects.blocks.energy_storage;

import com.affehund.airplanes.AirplanesConstants;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiEnergyStorageBlock extends GuiContainer {

	public GuiEnergyStorageBlock(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}

	public static final ResourceLocation TEXTURE = new ResourceLocation(AirplanesConstants.MODID, "textures/gui/energy_storage.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

}
