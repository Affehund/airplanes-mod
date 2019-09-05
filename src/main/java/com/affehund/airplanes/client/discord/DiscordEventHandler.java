package com.affehund.airplanes.client.discord;

import com.affehund.airplanes.AirplanesMod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;


import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DiscordEventHandler 
{
	
	AirplanesMod main;

    public DiscordEventHandler (AirplanesMod main) 
    {
        this.main = main;
    }
    
  

//    @SubscribeEvent
//    public void onPlayerSwitchDim(PlayerEvent.PlayerChangedDimensionEvent event) 
//    {
//    	
//    	if(event.toDim  == -1) 
//    	{
//    		final String dimension = "in the Nether";
//    		return;
//		}
//		
//    	if(event.toDim  == 0) 
//		{
//    		final String dimension = "in the Overworld";
//    		return;
//		}
//		
//    	if(event.toDim  == 1) 
//		{
//    		final String dimension = "in the End";
//    		return;
//		}
//    	else {
//    		final String dimension = "";
//    		return;
//    	}
//    }
    
    @SubscribeEvent
    public void onClientConnectedToServerEvent(FMLNetworkEvent.ClientConnectedToServerEvent event) 
    {
    	
        
		if (event.isLocal())
			main.proxy.rpcupdate(main, "Flying in Singleplayer" );
        else
            main.proxy.rpcupdate(main, "Flying in LAN-World");
		if (!event.isLocal())
			main.proxy.rpcupdate(main, "Flying in Multiplayer");
			
    }
 
}
