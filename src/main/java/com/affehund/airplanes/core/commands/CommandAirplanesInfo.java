package com.affehund.airplanes.core.commands;

import java.util.List;

import com.affehund.airplanes.Reference;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         \"Software\"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class CommandAirplanesInfo extends CommandBase
{

	public final List<String> aliases = Lists.newArrayList(Reference.MODID, "apinfo", "apinformations",
			"airplanesinformations");

	@Override
	public String getName()
	{
		return "apinfo";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "apinfo";
	}

	@Override
	public List<String> getAliases()
	{
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		{
			String message = getMessage();
			TextComponentString text = new TextComponentString(message);

			sender.sendMessage(text);
		}
	}

	public String getMessage()
	{
		String mess = "{\"text\":\"You are running on version\"}" + Reference.VERSION + ".\n"
				+ "{\"text\":\"To download a new version click here\n\"}"
				+ "{\"text\":\"here\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.curseforge.com/minecraft/mc-mods/airplanes-mod\"}"
				+ ".\n" + "{\"text\":\"You need help? Check out the \"}"
				+ "{\"text\":\"wiki\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://github.com/Affehund/airplanes-mod/wiki/\"}"
				+ ".\n" + "{\"text\":\"Or use the airplanes guide book.\"}";
		return mess;
	}
}
