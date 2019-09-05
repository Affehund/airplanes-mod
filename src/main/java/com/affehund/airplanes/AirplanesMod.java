package com.affehund.airplanes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.affehund.airplanes.client.discord.AirplanesRPCClient;
import com.affehund.airplanes.client.discord.DiscordEventHandler;
import com.affehund.airplanes.common.creativetabs.AirplanesTab1;
import com.affehund.airplanes.common.creativetabs.AirplanesTab2;
import com.affehund.airplanes.core.handlers.RegistryHandler;
import com.affehund.airplanes.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent; 
import net.minecraftforge.fml.common.Mod.EventHandler;


@Mod(modid = Reference.MODID, name = Reference.NAME, updateJSON = Reference.UPDATEJSON, version = Reference.VERSION)

public class AirplanesMod 
{
	@Instance
	public static AirplanesMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	public static final CreativeTabs AIRPLANESTAB1 = new AirplanesTab1();
	public static final CreativeTabs AIRPLANESTAB2 = new AirplanesTab2();
	
	public static Logger log = LogManager.getLogger(Reference.MODID);
	
	public DiscordEventHandler eventHandler = new DiscordEventHandler(this); 
    public AirplanesRPCClient rpcClient = new AirplanesRPCClient();


	static { FluidRegistry.enableUniversalBucket(); }
	
	 
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{	
		RegistryHandler.preInitRegistries(event);
		

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries(event);
		
		MinecraftForge.EVENT_BUS.register(eventHandler);
		  
        System.out.println("Airplanes Mod is loading!");
		proxy.rpcinit(this);
		proxy.rpcupdate(this, "Starting Game");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		RegistryHandler.postInitRegistries(event);
		
		System.out.println("Airplanes Mod is ready!");
        proxy.rpcupdate(this, "Flying in Main Menu"); 
    } 

	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
	}
}
