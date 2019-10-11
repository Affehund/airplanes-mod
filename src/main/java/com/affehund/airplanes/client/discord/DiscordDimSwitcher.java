//package com.affehund.airplanes.client.discord;
//
//import java.time.OffsetDateTime;
//import com.affehund.airplanes.AirplanesMod;
//import net.arikia.dev.drpc.DiscordRichPresence;
//
//public class DiscordDimSwitcher {
//	
//	public static void switchDim(int dim) {
//        DiscordRichPresence presence = AirplanesMod.instance.discord.getPresence();
//
//            presence.largeImageText = "Airplanes Mod";
//            presence.largeImageKey = "airplanes_rpc";
//            if(dim  == -1) {
//                presence.state = "the Nether";
//                presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
//                AirplanesMod.instance.discord.setPresence(presence);
//                return;
//            } else if (dim == 0) {
//                presence.state = "Earth";
//                presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
//                AirplanesMod.instance.discord.setPresence(presence);
//                return;
//            } else if (dim == 1) {
//                presence.state = "The End";
//                presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
//                AirplanesMod.instance.discord.setPresence(presence);
//                return;
//            }}
//
//	
//    public static void switchServer(int server) {
//        DiscordRichPresence presence = AirplanesMod.instance.discord.getPresence();
//        switch (server) {
//            case 0:
//                presence.details = "Playing Singleplayer";
//                break;
//            case 1:
//                presence.details = "Playing Multiplayer";
//                break;
//        }
//        AirplanesMod.instance.discord.setPresence(presence);
//    }
//}
