package com.affehund.airplanes;

import static com.affehund.airplanes.AirplanesConstants.*;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.InstanceFactory;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import com.affehund.airplanes.proxy.CommonProxy;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class AirplanesMod {
	
	@Instance() 
	private static AirplanesMod instance;
	
	public static AirplanesMod getInstance() {
		return instance;
	}
	
	
	@SidedProxy(serverSide = "com.affehund.airplanes.proxy.CommonProxy", clientSide = "com.affehund.airplanes.proxy.ClientProxy")
    private static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		proxy.preinit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		
	}
	
	@EventHandler
	public void postinit (FMLPostInitializationEvent event) {
		proxy.postinit(event);
		
	}
}
