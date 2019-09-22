package com.affehund.airplanes.proxy;

import com.affehund.airplanes.client.discord.DiscordConnectionHandler;
import com.affehund.airplanes.client.discord.DiscordEventHandler;
import com.affehund.airplanes.client.render.RenderBoeing737;
import com.affehund.airplanes.common.entities.EntityBoeing737;
import com.affehund.airplanes.core.init.BlockInit;
import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy
{
	@Override
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
	{
        return Minecraft.getMinecraft().addScheduledTask(runnableToSchedule);
    }
	
    @Override
    public EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().player;
    }
    
    @Override
    public void preInit(FMLPreInitializationEvent event) {
    	
        DiscordConnectionHandler connectionHandler = new DiscordConnectionHandler();
        MinecraftForge.EVENT_BUS.register(new DiscordEventHandler());
        
        RenderingRegistry.registerEntityRenderingHandler(EntityBoeing737.class, RenderBoeing737.FACTORY);
    }
    
    public static void registerCustomMeshesAndStates()
	{
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.OIL), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) 
			{
				return new ModelResourceLocation("airplanes:oil", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlockInit.OIL, new StateMapperBase() 
		{	
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return new ModelResourceLocation("airplanes:oil", "fluid");
			}
		});
	}
	

}