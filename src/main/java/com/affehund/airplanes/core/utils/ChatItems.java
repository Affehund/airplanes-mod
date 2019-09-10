package com.affehund.airplanes.core.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatItems {
	@SubscribeEvent
	public void giveItems(ServerChatEvent event) {
		if (event.getMessage().contains("potato")) {
			event.getPlayer().inventory.addItemStackToInventory(new ItemStack(
					Items.POTATO, 64));
			event.getPlayer().sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Hey"), true);
		}
	}
}
