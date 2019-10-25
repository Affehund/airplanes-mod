package com.affehund.airplanes.core.events;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public enum FMLEventHandler {
	
	INSTANCE;
	
	private static final String NBT_KEY = "airplanes.first_join";

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    	
        if (event.player.world.isRemote && event.player == FMLClientHandler.instance().getClientPlayerEntity())
        {
            MinecraftForge.EVENT_BUS.unregister(this);
            Object o = Loader.instance().getIndexedModList().get(Reference.MODID);
            net.minecraftforge.common.ForgeVersion.CheckResult result = ForgeVersion.getResult(((ModContainer) o));
            if (result.status == Status.OUTDATED)
            {
            	ITextComponent mess = getOutdatedMessage(result, "Airplanes Mod");
                (event.player).sendMessage(mess);
            }
        }
        
        NBTTagCompound data = event.player.getEntityData();
        NBTTagCompound persistent;
        if (!data.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            data.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persistent = new NBTTagCompound()));
        } else {
            persistent = data.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        }

        if (!persistent.hasKey(NBT_KEY)) 
        {
            persistent.setBoolean(NBT_KEY, true);
            event.player.inventory.addItemStackToInventory(new ItemStack(ItemInit.BOOK_OF_AIRPLANES));
            
//            String message = "Welcome to the Airplanes Mod";
//            (event.player).sendMessage(new TextComponentString(message));     
            }
    }
	
	
	public static ITextComponent getOutdatedMessage(net.minecraftforge.common.ForgeVersion.CheckResult result, String name)
	{
		String linkName = "[" + TextFormatting.GREEN + name + " " + result.target + TextFormatting.WHITE;
		String link = "" + result.url;
		String linkComponent = "{\"text\":\"" + linkName + "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\""
				+ link + "\"}}";

		String info = "\"" + TextFormatting.RED + "New " + name
				+ " version available, please update before reporting bugs.\nClick the green link for the page to download.\n"
				+ "\"";
		String mess = "[" + info + "," + linkComponent + ",\"]\"]";
		return ITextComponent.Serializer.jsonToComponent(mess);
	}
}
