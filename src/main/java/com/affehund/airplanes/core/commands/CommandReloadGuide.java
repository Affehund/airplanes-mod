package com.affehund.airplanes.core.commands;

import java.util.List;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.core.utils.Utilities;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

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
public class CommandReloadGuide extends CommandBase {
	
	public final List<String> aliases = Lists.newArrayList(Reference.MODID, "apguide", "airplanesGuide", "airplanesGuideReload");

	
	@Override
	public String getName() {
		return "airplanesGuideReload";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "airplanesGuideReload" + " to reload the guide book";
	}
	
	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		{
			Utilities.start();
			String message = "Reloaded guide book!";
	        TextComponentString text = new TextComponentString(message);
	        sender.sendMessage(text);
		}
	}
}
