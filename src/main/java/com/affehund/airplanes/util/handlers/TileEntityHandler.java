package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.objects.blocks.combustion_engine.TileEntityCombustionEngine;
import com.affehund.airplanes.objects.blocks.energy_storage.TileEntityEnergyStorageBlock;
import com.affehund.airplanes.objects.blocks.solar_panel.TileEntitySolarPanel;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCombustionEngine.class, new ResourceLocation(Reference.MODID + ":combustion_engine"));
		GameRegistry.registerTileEntity(TileEntityEnergyStorageBlock.class, new ResourceLocation(Reference.MODID + ":energy_storage_block"));
		GameRegistry.registerTileEntity(TileEntitySolarPanel.class, new ResourceLocation(Reference.MODID + ":solar_panel"));
	}
}