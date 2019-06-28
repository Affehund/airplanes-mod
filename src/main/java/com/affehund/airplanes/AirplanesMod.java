package com.affehund.airplanes;

import com.affehund.airplanes.config.AirplanesConfig;
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

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)

public class AirplanesMod 
{
	@Instance
	public static AirplanesMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;

	public static AirplanesConfig config;
	
	public static final CreativeTabs AIRPLANESTAB1 = new AirplanesTab1();
	public static final CreativeTabs AIRPLANESTAB2 = new AirplanesTab2();

	
	static 
	{ 
		FluidRegistry.enableUniversalBucket();
	}
	
	

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
