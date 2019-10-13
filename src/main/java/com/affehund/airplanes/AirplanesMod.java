package com.affehund.airplanes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.affehund.airplanes.client.discord.AirplanesDiscord;
//import com.affehund.airplanes.client.discord.AirplanesRPCClient;
//import com.affehund.airplanes.client.discord.DiscordEventHandler;
import com.affehund.airplanes.common.creativetabs.AirplanesTab1;
import com.affehund.airplanes.common.creativetabs.AirplanesTab2;
import com.affehund.airplanes.core.handlers.RegistryHandler;
import com.affehund.airplanes.core.init.ProfessionsInit;
import com.affehund.airplanes.proxy.CommonProxy;

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

@Mod.EventBusSubscriber
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
	
//	String domain = "airplanes";
//	String path = "models/entity/boeing_737_800.obj";

    //public AirplanesDiscord discord;

	static { FluidRegistry.enableUniversalBucket(); }
	

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) 
	{	
		System.out.println("Main PreInit");
		RegistryHandler.preInitRegistries(event);
		AirplanesMod.proxy.preInit(event);
		AirplanesMod.proxy.init(event);
//		discord = new AirplanesDiscord(new DiscordRichPresence());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		System.out.println("Main Init");
		RegistryHandler.initRegistries(event);
		ProfessionsInit.associateCareersAndTrades();
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) 
	{
		System.out.println("Main PostInit");
		RegistryHandler.postInitRegistries(event);
    } 

	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		System.out.println("Main ServerInit");
		RegistryHandler.serverRegistries(event);
	}
}
