package com.affehund.airplanes.core.init;

import java.util.ArrayList;
import java.util.List;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.common.blocks.BlockAirplanesBuilder;
import com.affehund.airplanes.common.blocks.BlockComputer_T1;
import com.affehund.airplanes.common.blocks.BlockComputer_T2;
import com.affehund.airplanes.common.blocks.BlockComputer_T3;
import com.affehund.airplanes.common.blocks.BlockElectronicsAssembler;
import com.affehund.airplanes.common.blocks.BlockFluidTank;
import com.affehund.airplanes.common.blocks.BlockOil;
import com.affehund.airplanes.common.blocks.machines.BlockCombustionEngine;
import com.affehund.airplanes.common.blocks.machines.BlockEnergyStorage;
import com.affehund.airplanes.common.blocks.machines.BlockMetallurgicalOven;
import com.affehund.airplanes.common.blocks.machines.BlockSolarPanel;
import com.affehund.airplanes.common.blocks.tools.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

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
public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ASPHALT_BLOCK = new BlockBase("asphalt_block", Material.CLAY, SoundType.SAND, "pickaxe", 2, 100F, 1.0F);

	//Materials
	public static final Block ALUMINUM_BLOCK = new BlockBase("aluminum_block", Material.IRON, SoundType.METAL, "pickaxe", 2, 125F, 2.0F);
	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Material.IRON, SoundType.METAL, "pickaxe", 2, 125F, 2.0F);
	public static final Block TIN_BLOCK = new BlockBase("tin_block", Material.IRON, SoundType.METAL, "pickaxe", 2, 125F, 2.0F);
	public static final Block STEEL_BLOCK = new BlockBase("steel_block", Material.IRON, SoundType.METAL, "pickaxe", 2, 125F, 2.0F);

	public static final Block ALUMINUM_COIL = new BlockBase("aluminum_coil", Material.IRON, SoundType.METAL, "pickaxe", 2, 100F, 2.0F);
	public static final Block COPPER_COIL = new BlockBase("copper_coil", Material.IRON, SoundType.METAL, "pickaxe", 2, 100F, 2.0F);
	public static final Block GOLD_COIL = new BlockBase("gold_coil", Material.IRON, SoundType.METAL, "pickaxe", 2, 100F, 2.0F);

	public static final Block BAUXITE_ORE = new BlockBase("bauxite_ore", Material.ROCK, SoundType.STONE, "pickaxe", 3, 150F, 3.0F);
	public static final Block COPPER_ORE = new BlockBase("copper_ore", Material.ROCK, SoundType.STONE, "pickaxe", 1, 125F, 2.0F);
	public static final Block TIN_ORE = new BlockBase("tin_ore", Material.ROCK, SoundType.STONE, "pickaxe", 2, 100F, 2.0F);
	public static final Block MACHINE_CASING = new BlockBase("machine_casing", Material.ROCK, SoundType.STONE, "pickaxe", 1, 150F, 2.0F);

	//Machines
	public static final Block AIRPLANES_BUILDER = new BlockAirplanesBuilder("airplanes_builder");
	public static final Block ELECTRONICS_ASSEMBLER_BLOCK = new BlockElectronicsAssembler("electronics_assembler_block");
	public static final Block COMBUSTION_ENGINE = new BlockCombustionEngine("combustion_engine");
	public static final Block ENERGY_STORAGE_BLOCK = new BlockEnergyStorage("energy_storage_block");
	public static final Block FLUID_TANK = new BlockFluidTank("fluid_tank");

	public static final Block METALLURGICAL_OVEN = new BlockMetallurgicalOven("metallurgical_oven");
	public static final Block SOLAR_PANEL = new BlockSolarPanel("solar_panel");
	
	public static final Block COMPUTER_T1 = new BlockComputer_T1("computer_t1");
	public static final Block COMPUTER_T2 = new BlockComputer_T2("computer_t2");
	public static final Block COMPUTER_T3 = new BlockComputer_T3("computer_t3");
	
	public static final Block OIL = new BlockOil("oil", FluidInit.OIL_FLUID, AirplanesMod.FLUID, AirplanesMod.AIRPLANESTAB1);
}
	