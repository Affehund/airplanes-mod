package com.affehund.airplanes.proxy;

import com.affehund.airplanes.init.AirplanesBlocks;
import com.affehund.airplanes.init.AirplanesItems;
import com.affehund.airplanes.init.AirplanesSmelting;
import com.affehund.airplanes.init.AirplanesWorldGeneration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preinit(FMLPreInitializationEvent event) {
		AirplanesBlocks.init();
		AirplanesItems.init();
		
		MinecraftForge.EVENT_BUS.register(AirplanesBlocks.class);
		MinecraftForge.EVENT_BUS.register(AirplanesItems.class);
		
		GameRegistry.registerWorldGenerator(new AirplanesWorldGeneration(), 0);
	}
	
public void init(FMLInitializationEvent event) {	
	AirplanesSmelting.init();
	}


	public void postinit(FMLPostInitializationEvent event) {	
	}
}
