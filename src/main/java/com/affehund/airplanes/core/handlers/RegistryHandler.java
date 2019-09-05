package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.commands.CommandAirplanesInfo;
import com.affehund.airplanes.core.init.BlockInit;
import com.affehund.airplanes.core.init.EntityInit;
import com.affehund.airplanes.core.init.FluidInit;
import com.affehund.airplanes.core.init.ItemInit;
import com.affehund.airplanes.core.init.OreDictionaryInit;
import com.affehund.airplanes.core.init.SmeltingInit;
import com.affehund.airplanes.core.init.WorldGenerationInit;

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
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ItemInit.ITEMS)
		{
			AirplanesMod.proxy.registerItemRenderer(item, 0, "inventory");
		}

		for(Block block : BlockInit.BLOCKS)
		{
			AirplanesMod.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	
	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		FluidInit.registerFluids();
		
		GameRegistry.registerWorldGenerator(new WorldGenerationInit(), 0);
		RenderHandler.registerEntityRenders();
		RenderHandler.registerCustomMeshesAndStates();
		EntityInit.registerEntities();
	}
	
	


	public static void initRegistries(FMLInitializationEvent event)
	{
		SmeltingInit.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(AirplanesMod.instance, new GuiHandler());
		OreDictionaryInit.registerOres();
		
	
	}
	

	public static void postInitRegistries(FMLPostInitializationEvent event)
	{
		
	}

	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandAirplanesInfo());
	}
	
	
    protected void registerRenderers() 
    {
    	
    }

	public void registerModels() 
	{
		
    }
}