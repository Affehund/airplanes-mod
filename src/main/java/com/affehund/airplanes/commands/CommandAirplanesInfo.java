package com.affehund.airplanes.commands;

import java.util.List;

import com.affehund.airplanes.Reference;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandAirplanesInfo extends CommandBase {
	
	public final List<String> aliases = Lists.newArrayList(Reference.MODID, "apinfo", "ai", "apinformations", "airplanesinformations");

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
			sender.sendMessage(new TextComponentString("You are running on version alpha0.8"));
			sender.sendMessage(new TextComponentString("You need help? Check out the wiki:"));
			sender.sendMessage(new TextComponentString("https://github.com/Affehund/airplanesmod/wiki"));
			sender.sendMessage(new TextComponentString("Report bugs in our github or on our discord."));
		}
		
	}

}
