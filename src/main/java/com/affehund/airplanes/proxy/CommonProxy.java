package com.affehund.airplanes.proxy;

import com.affehund.airplanes.AirplanesMod;
import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

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
    
    public void rpcinit(AirplanesMod main) 
    { 
    } 
    
    public void rpcupdate(AirplanesMod main, String details) 
    { 
    } 
}

