package com.affehund.airplanes.core.init;

import java.util.ArrayList;
import java.util.List;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.common.blocks.AirplanesBuilder;
import com.affehund.airplanes.common.blocks.AluminumBlock;
import com.affehund.airplanes.common.blocks.AluminumCoil;
import com.affehund.airplanes.common.blocks.AsphaltBlock;
import com.affehund.airplanes.common.blocks.BauxiteOre;
import com.affehund.airplanes.common.blocks.BlockFluid;
import com.affehund.airplanes.common.blocks.Computer_T1;
import com.affehund.airplanes.common.blocks.Computer_T2;
import com.affehund.airplanes.common.blocks.Computer_T3;
import com.affehund.airplanes.common.blocks.CopperBlock;
import com.affehund.airplanes.common.blocks.CopperCoil;
import com.affehund.airplanes.common.blocks.CopperOre;
import com.affehund.airplanes.common.blocks.ElectronicsAssemblerBlock;
import com.affehund.airplanes.common.blocks.GoldCoil;
import com.affehund.airplanes.common.blocks.MachineBlock;
import com.affehund.airplanes.common.blocks.SteelBlock;
import com.affehund.airplanes.common.blocks.TinBlock;
import com.affehund.airplanes.common.blocks.TinOre;
import com.affehund.airplanes.common.blocks.machines.CombustionEngineBlock;
import com.affehund.airplanes.common.blocks.machines.EnergyStorageBlock;
import com.affehund.airplanes.common.blocks.machines.SolarPanelBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	//Materials
	public static final Block ALUMINUM_BLOCK = new AluminumBlock("aluminum_block");
	public static final Block COPPER_BLOCK = new CopperBlock("copper_block");
	public static final Block TIN_BLOCK = new TinBlock("tin_block");
	public static final Block STEEL_BLOCK = new SteelBlock("steel_block");


	public static final Block ALUMINUM_COIL = new AluminumCoil("aluminum_coil");
	public static final Block COPPER_COIL = new CopperCoil("copper_coil");
	public static final Block GOLD_COIL = new GoldCoil("gold_coil");


	public static final Block BAUXITE_ORE = new BauxiteOre("bauxite_ore");
	public static final Block COPPER_ORE = new CopperOre("copper_ore");
	public static final Block TIN_ORE = new TinOre("tin_ore");
	
	public static final Block AIRPLANES_BUILDER = new AirplanesBuilder("airplanes_builder");
	public static final Block ELECTRONICS_ASSEMBLER_BLOCK = new ElectronicsAssemblerBlock("electronics_assembler_block");
	public static final Block MACHINE_BLOCK = new MachineBlock("machine_block");
	public static final Block ENERGY_STORAGE_BLOCK = new EnergyStorageBlock("energy_storage_block");


	//Machines
	public static final Block COMBUSTION_ENGINE = new CombustionEngineBlock("combustion_engine");
	public static final Block COMPUTER_T1 = new Computer_T1("computer_t1");
	public static final Block COMPUTER_T2 = new Computer_T2("computer_t2");
	public static final Block COMPUTER_T3 = new Computer_T3("computer_t3");
	
	public static final Block SOLAR_PANEL = new SolarPanelBlock("solar_panel");
	
	//Oil
	public static final Block OIL = new BlockFluid("oil", FluidInit.OIL, Material.WATER, AirplanesMod.AIRPLANESTAB1);
	
	public static final Block ASPHALT_BLOCK = new AsphaltBlock("asphalt_block");

}
	