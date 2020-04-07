package com.affehund.airplanes.core.events;

import org.lwjgl.input.Keyboard;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.init.ItemInit;
import com.affehund.airplanes.core.utils.TextUtilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */
@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EventPlayer
{
	private static final String NBT_KEY = "airplanes.first_join";

	@SubscribeEvent
	public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
	{
		/*
		 * checks if the player uses an outdated version
		 */
		if (event.player.world.isRemote && event.player == FMLClientHandler.instance().getClientPlayerEntity())
		{
			System.out.println("TEST");
			MinecraftForge.EVENT_BUS.unregister(this);
			Object o = Loader.instance().getIndexedModList().get(Reference.MODID);
			net.minecraftforge.common.ForgeVersion.CheckResult result = ForgeVersion.getResult(((ModContainer) o));
			if (result.status == Status.OUTDATED)
			{
				System.out.println("outdated version found");
            	ITextComponent mess = getOutdatedMessage(result, "Airplanes Mod");
                (event.player).sendMessage(mess);
			} 
			else AirplanesMod.log.info("Airplanes Mod is on the newest version");
		}

		/*
		 *  give book on join first time
		 */
		NBTTagCompound data = event.player.getEntityData();
		NBTTagCompound persistent;
		if (!data.hasKey(EntityPlayer.PERSISTED_NBT_TAG))
		{
			data.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persistent = new NBTTagCompound()));
		} else
		{
			persistent = data.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		}

		if (!persistent.hasKey(NBT_KEY))
		{
			persistent.setBoolean(NBT_KEY, true);
			if (ConfigAirplanes.Global.General.enableBook)
			{
				event.player.inventory.addItemStackToInventory(new ItemStack(ItemInit.BOOK_OF_AIRPLANES));
				String message = TextUtilities.stringToRainbow(event.player.getDisplayNameString() + " joined the Airplanes Mod for the first time!");
				event.player.sendMessage(new TextComponentString(message));
			}
		}
	}

	/*
	 *  outdated message
	 */
	public static ITextComponent getOutdatedMessage(net.minecraftforge.common.ForgeVersion.CheckResult result,
			String name)
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
	
	
	
	/*
	 * render events
	 */
	@SubscribeEvent
	public static void oneRenderPlayerEventPre(RenderPlayerEvent.Pre event)
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_C))
		{
			System.out.println("pressed c");
			event.getRenderer().getMainModel().bipedHead.isHidden = true;
		} else
			event.getRenderer().getMainModel().bipedHead.isHidden = false;
		return;
	}
}
