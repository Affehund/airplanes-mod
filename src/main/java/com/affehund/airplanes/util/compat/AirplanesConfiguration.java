package com.affehund.airplanes.util.compat;



import java.io.File;
import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.AirplanesMod;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;





public class AirplanesConfiguration 

{

	public static Configuration config;
	public static int GUI_COMBUSTION_ENGINE_ID = 1;

	public static void init(File file)
	{
		config = new Configuration(file);
		String category;
		category = "GUI IDs";
		GUI_COMBUSTION_ENGINE_ID = config.getInt("GUI_COMBUSTION_ENGINE_ID", category, 3, 1, 999, "Set the ID for the Combustion Engine");
	}

	public static void registerConfig(FMLPreInitializationEvent event)
	{
		AirplanesMod.config = new File(event.getModConfigurationDirectory() + "/" + AirplanesConstants.MODID);
		AirplanesMod.config.mkdirs();
		init(new File(AirplanesMod.config.getPath(), AirplanesConstants.MODID + ".cfg"));
	}
} 