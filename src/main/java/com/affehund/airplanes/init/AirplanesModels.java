package com.affehund.airplanes.init;



import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.minecraft.item.Item;

import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraftforge.client.model.ModelLoader;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class AirplanesModels {

	

	@SubscribeEvent

	public static void register(ModelRegistryEvent event) {


	}

	

	private static void register(Item item) {

		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}

	

}