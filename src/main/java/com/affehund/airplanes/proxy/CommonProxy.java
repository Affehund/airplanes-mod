package com.affehund.airplanes.proxy;

import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
	
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		
	}

	
	public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
	{
        throw new IllegalStateException("This should only be called from client side");
    }

    public EntityPlayer getClientPlayer()
    {
        throw new IllegalStateException("This should only be called from client side");
    }
    

    public void preInit(FMLPreInitializationEvent event) 
    {
    	
    }
    
    protected void registerRenderers() 
    {

    }
}

