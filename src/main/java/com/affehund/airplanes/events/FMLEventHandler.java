package com.affehund.airplanes.events;

import com.affehund.airplanes.AirplanesMod;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public enum FMLEventHandler {
 INSTANCE;
	
    @SubscribeEvent
    	public void firstJoin(PlayerLoggedInEvent event) {
	    EntityPlayer player = event.player;
	    NBTTagCompound entityData = player.getEntityData();
	    	if(!entityData.getBoolean("joinedBefore")) {
	    		entityData.setBoolean("joinedBefore", true);
	    		player.inventory.addItemStackToInventory(new ItemStack(Items.COMPASS));
	      }
	   }

	    
    @SubscribeEvent
    	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
	            AirplanesMod.config.sync();
	    }
}
	

