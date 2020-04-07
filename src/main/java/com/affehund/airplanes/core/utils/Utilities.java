package com.affehund.airplanes.core.utils;

import com.affehund.airplanes.AirplanesMod;
import com.google.common.base.Stopwatch;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class Utilities
{
	public static boolean areItemStacksEqualRecipe(ItemStack stack1, ItemStack stack2)
	{
		return stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem()
				&& (stack1.getItemDamage() == stack2.getItemDamage()
						|| stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE
						|| stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack1.getItem().isDamageable());
	}

	/*
	 * get OS
	 */
	private static final String OS = System.getProperty("os.name").toUpperCase();

	public static String getOSName()
	{
		return OS;
	}

	public static boolean isWindows()
	{
		return OS.contains("WIN");
	}

	public static boolean isMac()
	{
		return OS.contains("MAC");
	}

	public static boolean isUnix()
	{
		return OS.contains("NIX") || OS.contains("NUX") || OS.contains("AIX");
	}

	/*
	 * Timer for logs
	 */
	public static class Timer
	{
		private final Stopwatch stopWatch = Stopwatch.createUnstarted();
		private String message = "";

		public void start(String message)
		{
			this.message = message;
			AirplanesMod.log.info("{}...", message);
			stopWatch.reset();
			stopWatch.start();
		}

		public void reset()
		{
			stopWatch.reset();
		}

		public void stop()
		{
			stopWatch.stop();
			AirplanesMod.log.info("{} took {}", message, stopWatch);
		}
	}

	// (re)starting event
	private static boolean started;

	public static void start()
	{
		if (hasStarted())
		{
			AirplanesMod.log.info("Restarting airplanes mod...");
		}
		started = true;
		Timer timer = new Timer();
		timer.start("Starting airplanes mod");
		AirplanesMod.log.info("Loading book...");
		try
		{
			AirplanesMod.proxy.loadGuide();
			timer.stop();
		} catch (Exception e)
		{
			timer.reset();
			AirplanesMod.log.info("Error while loading book");
			e.printStackTrace();
		}
	}

	public static boolean hasStarted()
	{
		return started;
	}
}