//package com.affehund.airplanes.client.discord;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import net.arikia.dev.drpc.DiscordRPC;
//import net.arikia.dev.drpc.DiscordRichPresence;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.GuiMainMenu;
//import net.minecraftforge.client.event.GuiScreenEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.gameevent.PlayerEvent;
//import net.minecraftforge.fml.common.gameevent.TickEvent;
//import net.minecraftforge.fml.common.network.FMLNetworkEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class DiscordEventHandler 
//{
//	private Logger logger = LogManager.getLogger("Airplanes Mod");
//    private boolean firstInfoSent = false;
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent
//    public void guiScreenDetect(GuiScreenEvent.InitGuiEvent.Pre event) {
//        if (event.getGui() instanceof GuiMainMenu) {
//            logger.info("Detected main menu, updating presence");
//            DiscordRPC.discordUpdatePresence(new DiscordRichPresence.Builder("Flying the Main Menu")
//                    .setBigImage("airplanes_rpc", "Airplanes Mod")
//                    .setSmallImage("diamond", "Download Airplanes Mod: https://www.curseforge.com/minecraft/mc-mods/airplanes-mod")
//                    .build()
//            );
//        }
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent
//    public void onClientTick(TickEvent.ClientTickEvent event) {
//        try {
//            if(!firstInfoSent) {
//                DiscordDimSwitcher.switchDim(Minecraft.getMinecraft().player.world.provider.getDimension());
//                firstInfoSent = true;
//            }
//        } catch (NoSuchMethodError | NoSuchFieldError  | NullPointerException error) {
//            firstInfoSent = false;
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent
//    public void onPlayerLoggedout(PlayerEvent.PlayerLoggedOutEvent e) {
//        firstInfoSent = false;
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent()
//    public void onServerJoin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
//        if (!event.isLocal()) {
//            DiscordDimSwitcher.switchDim(1);
//        } else {
//            DiscordDimSwitcher.switchServer(0);
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent
//    public void onPlayerSwitchDim(PlayerEvent.PlayerChangedDimensionEvent event) {
//        DiscordDimSwitcher.switchDim(event.toDim);
//    }
//}
//
