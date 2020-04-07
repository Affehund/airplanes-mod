package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.tileentities.TileEntityAirplanesBuilder;
import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;
import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;
import com.affehund.airplanes.common.tileentities.TileEntityFluidTank;
import com.affehund.airplanes.common.tileentities.TileEntityMetallurgicalOven;
import com.affehund.airplanes.common.tileentities.TileEntitySolarPanel;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCombustionEngine.class, new ResourceLocation(Reference.MODID + ":combustion_engine"));
		GameRegistry.registerTileEntity(TileEntityEnergyStorageBlock.class, new ResourceLocation(Reference.MODID + ":energy_storage_block"));
		GameRegistry.registerTileEntity(TileEntitySolarPanel.class, new ResourceLocation(Reference.MODID + ":solar_panel"));
		GameRegistry.registerTileEntity(TileEntityFluidTank.class, new ResourceLocation(Reference.MODID + ":fluid_tank"));
		GameRegistry.registerTileEntity(TileEntityMetallurgicalOven.class, new ResourceLocation(Reference.MODID + ":metallurgical_oven"));
		GameRegistry.registerTileEntity(TileEntityAirplanesBuilder.class, new ResourceLocation(Reference.MODID + ":airplanes_builder"));
	}
}