package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;
import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;
import com.affehund.airplanes.common.tileentities.TileEntitySolarPanel;

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