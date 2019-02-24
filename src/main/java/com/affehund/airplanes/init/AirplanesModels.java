package com.affehund.airplanes.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AirplanesModels {

	

	@SubscribeEvent
	public static void register(ModelRegistryEvent event) {

		//materials
		register(AirplanesItems.aluminum_ingot);
		register(AirplanesItems.aluminum_plate);
		register(AirplanesItems.aluminum_rod);
		register(AirplanesItems.copper_ingot);
		register(AirplanesItems.copper_plate);
		register(AirplanesItems.copper_rod);
		register(AirplanesItems.iron_plate);
		register(AirplanesItems.iron_rod);
		register(AirplanesItems.silicon);
		register(AirplanesItems.steel_ingot);
		register(AirplanesItems.steel_plate);
		register(AirplanesItems.steel_rod);
		
		register(AirplanesItems.fuel);
		register(AirplanesItems.airplanes_wrench);
		
		//blocks
		register(Item.getItemFromBlock(AirplanesBlocks.asphalt_block));
		register(Item.getItemFromBlock(AirplanesBlocks.bauxite_ore));
		register(Item.getItemFromBlock(AirplanesBlocks.copper_ore));
		register(Item.getItemFromBlock(AirplanesBlocks.airplanes_builder));

	}


	private static void register(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}