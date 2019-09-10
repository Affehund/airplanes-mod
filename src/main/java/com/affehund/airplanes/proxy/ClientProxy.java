package com.affehund.airplanes.proxy;

import com.affehund.airplanes.client.discord.DiscordConnectionHandler;
import com.affehund.airplanes.client.discord.DiscordEventHandler;
import com.affehund.airplanes.core.handlers.RenderHandler;
import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
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
    	
		RenderHandler.registerEntityRenders();
		RenderHandler.registerCustomMeshesAndStates();
		
        DiscordConnectionHandler connectionHandler = new DiscordConnectionHandler();
        MinecraftForge.EVENT_BUS.register(new DiscordEventHandler());
    }
}