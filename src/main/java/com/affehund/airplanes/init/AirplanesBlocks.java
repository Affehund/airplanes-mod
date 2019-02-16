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
	public static void init() {
		setName(asphalt_block, "asphaltblock");
	}

	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.register(asphalt_block);
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		registry.register(new ItemBlock(asphalt_block).setRegistryName(asphalt_block.getRegistryName()));
	}

	
	public static void setName(Block block, String name) {
		block.setRegistryName(new ResourceLocation(AirplanesConstants.MODID, name));
		block.setUnlocalizedName(name);
	}
}