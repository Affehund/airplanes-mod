package com.affehund.airplanes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.affehund.airplanes.client.discord.AirplanesDiscord;
//import com.affehund.airplanes.client.discord.AirplanesRPCClient;
//import com.affehund.airplanes.client.discord.DiscordEventHandler;
import com.affehund.airplanes.common.creativetabs.AirplanesTab1;
import com.affehund.airplanes.common.creativetabs.AirplanesTab2;
import com.affehund.airplanes.core.handlers.RegistryHandler;
import com.affehund.airplanes.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent; 
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.arikia.dev.drpc.DiscordRichPresence;

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

    public AirplanesDiscord discord;


	static { FluidRegistry.enableUniversalBucket(); }
	
	 
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{	
		RegistryHandler.preInitRegistries(event);
		discord = new AirplanesDiscord(new DiscordRichPresence());
		

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		RegistryHandler.postInitRegistries(event);
    } 

	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
	}
}
