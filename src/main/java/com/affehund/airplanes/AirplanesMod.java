package com.affehund.airplanes;


import java.io.File;

import com.affehund.airplanes.creativetabs.AirplanesTab1;
import com.affehund.airplanes.creativetabs.AirplanesTab2;
import com.affehund.airplanes.proxy.CommonProxy;
import com.affehund.airplanes.util.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = AirplanesConstants.MODID, name = AirplanesConstants.NAME, version = AirplanesConstants.VERSION)

public class AirplanesMod 
{
	@Instance
	public static AirplanesMod instance;
	
	public static AirplanesMod getInstance() {
		return instance;
	}

	@SidedProxy(clientSide = AirplanesConstants.CLIENT, serverSide = AirplanesConstants.SERVER)
	public static CommonProxy proxy;
	
	public static final CreativeTabs AIRPLANESTAB1 = new AirplanesTab1();
	public static final CreativeTabs AIRPLANESTAB2 = new AirplanesTab2();

	public static File config;
	
	static { FluidRegistry.enableUniversalBucket(); }

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) 
	{	
		RegistryHandler.preInitRegistries(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries(event);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) 
	{
		RegistryHandler.postInitRegistries(event);
	}

	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
	}
}
