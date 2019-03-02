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
		register(AirplanesItems.copper_nugget);
		register(AirplanesItems.copper_plate);
		register(AirplanesItems.copper_rod);
		register(AirplanesItems.iron_plate);
		register(AirplanesItems.iron_rod);
		register(AirplanesItems.silicon);
		register(AirplanesItems.steel_ingot);
		register(AirplanesItems.steel_plate);
		register(AirplanesItems.steel_rod);
		register(AirplanesItems.tin_ingot);
		register(AirplanesItems.tin_nugget);
		
		register(AirplanesItems.airplanes_hammer);
		register(AirplanesItems.airplanes_wrench);
		register(AirplanesItems.fuel);
		
		
		//blocks
		register(Item.getItemFromBlock(AirplanesBlocks.aluminum_block));
		register(Item.getItemFromBlock(AirplanesBlocks.aluminum_coil));
		register(Item.getItemFromBlock(AirplanesBlocks.asphalt_block));		
		register(Item.getItemFromBlock(AirplanesBlocks.bauxite_ore));
		register(Item.getItemFromBlock(AirplanesBlocks.copper_block));
		register(Item.getItemFromBlock(AirplanesBlocks.copper_coil));
		register(Item.getItemFromBlock(AirplanesBlocks.copper_ore));
		register(Item.getItemFromBlock(AirplanesBlocks.gold_coil));
		register(Item.getItemFromBlock(AirplanesBlocks.steel_block));
		register(Item.getItemFromBlock(AirplanesBlocks.tin_block));
		register(Item.getItemFromBlock(AirplanesBlocks.tin_ore));
		
		register(Item.getItemFromBlock(AirplanesBlocks.combustion_engine));
		register(Item.getItemFromBlock(AirplanesBlocks.machine_block));
		register(Item.getItemFromBlock(AirplanesBlocks.parts_builder));
	}


	private static void register(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}