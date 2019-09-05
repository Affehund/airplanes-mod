//package com.affehund.airplanes.client.discord;
//
//import com.affehund.airplanes.AirplanesMod;
//
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.network.FMLNetworkEvent;
//
//public class EventHandler {
//
//    AirplanesMod main;
//
//    public EventHandler (AirplanesMod main) 
//    {
//        this.main = main;
//    }
//
//    @SubscribeEvent 
//    public void onClientConnectedToServerEvent(FMLNetworkEvent.ClientConnectedToServerEvent event) { 
//        if (event.isLocal()) 
//            main.proxy.rpcupdate(main, "Singleplayer"); 
//        else 
//            main.proxy.rpcupdate(main, "Multiplayer"); 
//    } 
//}
