package com.affehund.airplanes.client.discord;

import javax.annotation.Nullable;
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;


import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class AirplanesRPCClient {


	
    public static final String CLIENT_ID = "617715855161294869";
    private static Thread callbackRunner;

    public synchronized void init()
    {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordRPC.INSTANCE.Discord_Initialize(CLIENT_ID, handlers, true, null);

        if (callbackRunner == null)
        {
            callbackRunner = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    DiscordRPC.INSTANCE.Discord_RunCallbacks();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {}
                }
            }, "RPC-Callback-Handler");
            callbackRunner.start();
        }
 
        System.out.println("[Airplanes-RPC:] AirplanesRPCClient has been started.");
    }
    public void updatePresence(@Nullable String details)
    {
    	
    	
    	
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.largeImageKey = "airplanes_rpc";
        presence.largeImageText = "Airplanes Mod";
        presence.smallImageKey = "diamond";
        presence.smallImageText = "Download Airplanes Mod:" + '\n' + "https://www.curseforge.com/minecraft/mc-mods/airplanes-mod";
       
        if (details != null){
            presence.details = details;
            presence.startTimestamp = System.currentTimeMillis() / 1000;
        }
        DiscordRPC.INSTANCE.Discord_UpdatePresence(presence);
    }
}
