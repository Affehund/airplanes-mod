package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.commands.CommandAirplanesInfo;
import com.affehund.airplanes.core.events.EventHandler;
import com.affehund.airplanes.core.events.FMLEventHandler;
import com.affehund.airplanes.core.events.RecipeRemover;
import com.affehund.airplanes.core.init.BlockInit;
import com.affehund.airplanes.core.init.EntityInit;
import com.affehund.airplanes.core.init.FluidInit;
import com.affehund.airplanes.core.init.ItemInit;
import com.affehund.airplanes.core.init.OreDictionaryInit;
import com.affehund.airplanes.core.init.SmeltingInit;
import com.affehund.airplanes.core.init.WorldGenerationInit;
import com.affehund.airplanes.core.utils.ChatItems;
import com.affehund.airplanes.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
	public static void registerModels(ModelRegistryEvent event)
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
	
//	@SubscribeEvent
//	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) 
//	{
//		int entityId = 0;
//        event.getRegistry().registerAll(
//                EntityEntryBuilder.create().name("boeing737")
//                        .id(location("boeing737"), entityId++)
//                        .entity(EntityBoeing737.class).factory(EntityBoeing737::new)
//                        .tracker(80, 3, true).build()
//                        );
//                        AirplanesMod.log.debug("Next entity id: " + entityId);
//	}
//	
//    public static ResourceLocation location(String location)
//    {
//        return new ResourceLocation(Reference.MODID, location);
//    }

	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		FluidInit.registerFluids();
		GameRegistry.registerWorldGenerator(new WorldGenerationInit(), 0);
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();
		ClientProxy.registerCustomMeshesAndStates();
		EventHandler.registerEvents();
	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(AirplanesMod.instance, new GuiHandler());
		SmeltingInit.init();
		OreDictionaryInit.registerOres();
		FMLCommonHandler.instance().bus().register(FMLEventHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new ChatItems());
	}
	
	public static void postInitRegistries(FMLPostInitializationEvent event)
	{
		
	}

	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandAirplanesInfo());
	}

	public void registerModels() 
	{
		
    }
}