package com.affehund.airplanes;

import static com.affehund.airplanes.AirplanesConstants.*;
import com.affehund.airplanes.proxy.CommonProxy;
import com.affehund.airplanes.util.compat.OreDictionaryCompat;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = MODID, name = NAME, version = VERSION)
public class AirplanesMod {

	
	@Instance()
	public 
	static AirplanesMod instance;
	
	public static AirplanesMod getInstance() {
		return instance;
	}
	
	public enum GUI_Register 
	{
		AirplanesBuilder
	}
	
	
	@SidedProxy(serverSide = "com.affehund.airplanes.proxy.CommonProxy", clientSide = "com.affehund.airplanes.proxy.ClientProxy")
    private static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		proxy.preinit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		OreDictionaryCompat.registerOreDictionary();
		proxy.init(event);
		
	}
	
	@EventHandler
	public void postinit (FMLPostInitializationEvent event) {
		proxy.postinit(event);
		
		
	}
}
