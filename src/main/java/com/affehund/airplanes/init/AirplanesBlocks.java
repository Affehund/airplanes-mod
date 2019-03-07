package com.affehund.airplanes.init;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.block.*;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;


public class AirplanesBlocks {
	public static final AluminumBlock aluminum_block = new AluminumBlock();
	public static final AluminumCoil aluminum_coil = new AluminumCoil();
	public static final AsphaltBlock asphalt_block = new AsphaltBlock();
	public static final BauxiteOre bauxite_ore = new BauxiteOre();
	public static final CopperBlock copper_block = new CopperBlock();
	public static final CopperCoil copper_coil = new CopperCoil();
	public static final CopperOre copper_ore = new CopperOre();
	public static final GoldCoil gold_coil = new GoldCoil();
	public static final SteelBlock steel_block = new SteelBlock();
	public static final TinBlock tin_block = new TinBlock();
	public static final TinOre tin_ore = new TinOre();
	
	public static final CombustionEngine combustion_engine = new CombustionEngine();
	public static final PartsBuilder parts_builder = new PartsBuilder(null);
	public static final MachineBlock machine_block = new MachineBlock();
	
	public static void init() {
		setName(aluminum_block, "aluminum_block");
		setName(aluminum_coil, "aluminum_coil");
		setName(asphalt_block, "asphalt_block");
		setName(bauxite_ore, "bauxite_ore");
		setName(copper_block, "copper_block");
		setName(copper_coil, "copper_coil");
		setName(copper_ore, "copper_ore");
		setName(gold_coil, "gold_coil");
		setName(steel_block, "steel_block");
		setName(tin_block, "tin_block");
		setName(tin_ore, "tin_ore");
		
		setName(combustion_engine, "combustion_engine");
		setName(machine_block, "machine_block");
		setName(parts_builder, "parts_builder");
	}

	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.register(aluminum_block);
		registry.register(aluminum_coil);
		registry.register(asphalt_block);
		registry.register(bauxite_ore);
		registry.register(copper_block);
		registry.register(copper_coil);
		registry.register(copper_ore);
		registry.register(gold_coil);
		registry.register(steel_block);
		registry.register(tin_block);
		registry.register(tin_ore);
		
		registry.register(combustion_engine);
		registry.register(machine_block);
		registry.register(parts_builder);
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		registry.register(new ItemBlock(aluminum_block).setRegistryName(aluminum_block.getRegistryName()));
		registry.register(new ItemBlock(aluminum_coil).setRegistryName(aluminum_coil.getRegistryName()));
		registry.register(new ItemBlock(asphalt_block).setRegistryName(asphalt_block.getRegistryName()));
		registry.register(new ItemBlock(bauxite_ore).setRegistryName(bauxite_ore.getRegistryName()));
		registry.register(new ItemBlock(copper_block).setRegistryName(copper_block.getRegistryName()));
		registry.register(new ItemBlock(copper_coil).setRegistryName(copper_coil.getRegistryName()));
		registry.register(new ItemBlock(copper_ore).setRegistryName(copper_ore.getRegistryName()));
		registry.register(new ItemBlock(gold_coil).setRegistryName(gold_coil.getRegistryName()));
		registry.register(new ItemBlock(steel_block).setRegistryName(steel_block.getRegistryName()));
		registry.register(new ItemBlock(tin_block).setRegistryName(tin_block.getRegistryName()));
		registry.register(new ItemBlock(tin_ore).setRegistryName(tin_ore.getRegistryName()));
		
		registry.register(new ItemBlock(combustion_engine).setRegistryName(combustion_engine.getRegistryName()));
		registry.register(new ItemBlock(machine_block).setRegistryName(machine_block.getRegistryName()));
		registry.register(new ItemBlock(parts_builder).setRegistryName(parts_builder.getRegistryName()));
	}

	
	public static void setName(Block block, String name) {
		block.setRegistryName(new ResourceLocation(AirplanesConstants.MODID, name));
		block.setUnlocalizedName(name);
		}
}