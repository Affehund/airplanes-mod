package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.objects.blocks.combustion_engine.TileEntityCombustionEngine;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCombustionEngine.class, new ResourceLocation(AirplanesConstants.MODID + ":combustion_engine"));
	}
}