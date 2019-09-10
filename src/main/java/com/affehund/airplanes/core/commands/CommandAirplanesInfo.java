package com.affehund.airplanes.core.commands;

import java.util.List;

import com.affehund.airplanes.Reference;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandAirplanesInfo extends CommandBase {
	
	public final List<String> aliases = Lists.newArrayList(Reference.MODID, "apinfo", "apinformations", "airplanesinformations");
	
	public static ITextComponent getOutdatedMessage()
	{
	
	String linkName1 = TextFormatting.GREEN + Reference.NAME + "" + TextFormatting.WHITE;
	String link1 = "https://github.com/Affehund/airplanesmod/wiki/";
	String linkComponent1 = "{\"text\":\"" + linkName1 + "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + link1 + "\"}}";

	String info1 = "\"" + TextFormatting.WHITE + "You are running on version" + Reference.VERSION + "\"";
	
	String linkName2 = "Wiki";
	String link2 = "https://github.com/Affehund/airplanesmod/wiki/";
	String linkComponent2 = "{\"text\":\"" + linkName2 + "\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + link2 + "\"}}";

	String info2 = "\"" + TextFormatting.WHITE + "You need help? Check out the " + "\"";
	
	String mess = "[" + info1 + "," + linkComponent1 + info2 + linkComponent2 + "\"]";
	return ITextComponent.Serializer.jsonToComponent(mess);
	}
	
	@Override
	public String getName() {
		return "apinfo";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "apinfo";
	}
	
	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		{
			ITextComponent mess = getOutdatedMessage();
			
			sender.sendMessage(mess);
		}
	}
}
