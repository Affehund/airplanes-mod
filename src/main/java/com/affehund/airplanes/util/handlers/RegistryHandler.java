package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.AirplanesSmelting;
import com.affehund.airplanes.init.AirplanesWorldGeneration;
import com.affehund.airplanes.init.BlockInit;
import com.affehund.airplanes.init.FluidInit;
import com.affehund.airplanes.init.ItemInit;
import com.affehund.airplanes.util.compat.AirplanesConfiguration;
import com.affehund.airplanes.util.compat.OreDictionaryCompat;
import com.affehund.airplanes.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) 
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) 
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}


	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) 
	{
		for(Item item : ItemInit.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel) item).registerModels();
				
			}
		}
		
		for(Block block : BlockInit.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel) block).registerModels();
				
			}
		}
	}

	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		FluidInit.registerFluids();
		
		
		GameRegistry.registerWorldGenerator(new AirplanesWorldGeneration(), 0);
		RenderHandler.registerEntityRenders();
		RenderHandler.registerCustomMeshesAndStates();
		AirplanesConfiguration.registerConfig(event);
	}
	

	public static void initRegistries(FMLInitializationEvent event)
	{
		AirplanesSmelting.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(AirplanesMod.instance, new GuiHandler());
		OreDictionaryCompat.registerOres();
	}
	

	public static void postInitRegistries(FMLPostInitializationEvent event)
	{
		
	}

	public static void serverRegistries(FMLServerStartingEvent event)
	{
		
	}
}