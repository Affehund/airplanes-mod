package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.commands.CommandAirplanesInfo;
import com.affehund.airplanes.core.commands.CommandReloadGuide;
import com.affehund.airplanes.core.compat.AirplanesCompatHandler;
import com.affehund.airplanes.core.init.BlockInit;
import com.affehund.airplanes.core.init.EntityInit;
import com.affehund.airplanes.core.init.FluidInit;
import com.affehund.airplanes.core.init.ItemInit;
import com.affehund.airplanes.core.init.OreDictionaryInit;
import com.affehund.airplanes.core.init.OreGenInit;
import com.affehund.airplanes.core.init.ProfessionsInit;
import com.affehund.airplanes.core.init.RecipeInit;
import com.affehund.airplanes.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onModelRegister(ModelRegistryEvent event)
	{
		ClientProxy.onModelRegister(event);
	}

	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		FluidInit.registerFluids();
		EntityInit.registerEntities();
		AirplanesCompatHandler.registerCompats();
		GameRegistry.registerWorldGenerator(new OreGenInit(), 0);
		EventHandler.registerEvents();
	}

	public static void initRegistries(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(AirplanesMod.instance, new GuiHandler());
		OreDictionaryInit.registerOres();
		ProfessionsInit.associateCareersAndTrades();
		EnumHelper.addArt("airplanes", "airplanes", 80, 80, 112, 48);
	}

	public static void postInitRegistries(FMLPostInitializationEvent event)
	{
		RecipeInit.init();
	}

	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandAirplanesInfo());
		event.registerServerCommand(new CommandReloadGuide());
	}
}