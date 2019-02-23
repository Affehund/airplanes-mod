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
	public static final AsphaltBlock asphalt_block = new AsphaltBlock();
	public static final BauxiteOre bauxite_ore = new BauxiteOre();
	public static final CopperOre copper_ore = new CopperOre();
	public static final AirplanesBuilder airplanes_builder = new AirplanesBuilder(null);
	
	public static void init() {
		setName(asphalt_block, "asphalt_block");
		setName(bauxite_ore, "bauxite_ore");
		setName(copper_ore, "copper_ore");
		setName(airplanes_builder, "airplanes_builder");
	}

	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.register(asphalt_block);
		registry.register(bauxite_ore);
		registry.register(copper_ore);
		registry.register(airplanes_builder);
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		registry.register(new ItemBlock(asphalt_block).setRegistryName(asphalt_block.getRegistryName()));
		registry.register(new ItemBlock(bauxite_ore).setRegistryName(bauxite_ore.getRegistryName()));
		registry.register(new ItemBlock(copper_ore).setRegistryName(copper_ore.getRegistryName()));
		registry.register(new ItemBlock(airplanes_builder).setRegistryName(airplanes_builder.getRegistryName()));
	}

	
	public static void setName(Block block, String name) {
		block.setRegistryName(new ResourceLocation(AirplanesConstants.MODID, name));
		block.setUnlocalizedName(name);
	}
}