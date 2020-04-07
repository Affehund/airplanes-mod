package com.affehund.airplanes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.affehund.airplanes.common.creativetabs.TabAirplanesItems;
import com.affehund.airplanes.common.creativetabs.TabAirplanesPlanes;
import com.affehund.airplanes.core.handlers.RegistryHandler;
import com.affehund.airplanes.core.utils.Utilities;
import com.affehund.airplanes.proxy.CommonProxy;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
@Mod.EventBusSubscriber
@Mod(modid = Reference.MODID, name = Reference.NAME, updateJSON = Reference.UPDATEJSON, version = Reference.VERSION)

public class AirplanesMod
{
	@Instance
	public static AirplanesMod instance;

	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;

	public static final CreativeTabs AIRPLANESTAB1 = new TabAirplanesItems();
	public static final CreativeTabs AIRPLANESTAB2 = new TabAirplanesPlanes();

	public static Logger log = LogManager.getLogger(Reference.MODID);

	static
	{
		FluidRegistry.enableUniversalBucket();
	}

	public static final Material FLUID = (new MaterialLiquid(MapColor.WATER));

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		log.info("[Airplanes Mod] Running on OS: " + Utilities.getOSName());
		log.info("Main PreInit");
		RegistryHandler.preInitRegistries(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		log.info("Main Init");
		RegistryHandler.initRegistries(event);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		log.info("Main PostInit");
		RegistryHandler.postInitRegistries(event);
	}

	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		log.info("Main ServerInit");
		RegistryHandler.serverRegistries(event);
	}

	@Mod.EventHandler
	public void loadComplete(FMLLoadCompleteEvent event)
	{
		// Reload when resources change
		Minecraft minecraft = Minecraft.getMinecraft();
		IReloadableResourceManager reloadableResourceManager = (IReloadableResourceManager) minecraft
				.getResourceManager();
		reloadableResourceManager.registerReloadListener(resourceManager -> {
			event.description();
			Utilities.start();
		});
	}
}
