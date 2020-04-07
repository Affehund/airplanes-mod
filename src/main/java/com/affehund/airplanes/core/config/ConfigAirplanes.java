package com.affehund.airplanes.core.config;

import com.affehund.airplanes.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
@EventBusSubscriber(modid = Reference.MODID)
public class ConfigAirplanes
{
	@Config(modid = Reference.MODID, name = Reference.MODID + "airplanes", category = "")
	public static class Global
	{

		@Config.Name("General config")
		@Config.Comment("")
		public static final General General = new General();
		@Config.Name("Energy config")
		@Config.Comment("")
		public static final Energy Energy = new Energy();
		@Config.Name("Ore generation config")
		@Config.Comment("")
		public static final OreGen OreGen = new OreGen();

		public static class General
		{
			@Config.Name("Enable Update Checker")
			@Config.Comment(
			{ "Whether mod updates should be announced, but not working at the moment", "Default: true" })
			@Config.RequiresWorldRestart
			public boolean enableUpdateChecker = true;

			@Config.Name("Enable Book on joining")
			@Config.Comment(
			{ "Whether the guide book should be given to the player", "Default: true" })
			@Config.RequiresWorldRestart
			public boolean enableBook = true;

			@Config.Name("Enable filling the tank when raining")
			@Config.Comment(
			{ "Whether the tank can be filled when raining", "Default: true" })
			@Config.RequiresWorldRestart
			public boolean enableTankRainFilling = true;
		}

		public static class Energy
		{
			@Config.Name("Energy Unit")
			@Config.Comment(
			{ "Setting the Energy Unit", "Default: FE" })
			@Config.RequiresWorldRestart
			public String ENERGY_UNIT = "FE";

			@Config.Name("Capacity combustion engine")
			@Config.Comment(
			{ "Amount of capacity for the combustion engine", "Default: 100000" })
			@Config.RangeInt(min = 1000, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int MAX_CAPACITY_COMBUSTION = 100000;

			@Config.Name("Output combustion engine")
			@Config.Comment(
			{ "Maximum amount of output for the combustion engine", "Default: 1000" })
			@Config.RangeInt(min = 100, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int MAX_OUTPUT_COMBUSTION = 1000;

			@Config.Name("Capacity energy storage block")
			@Config.Comment(
			{ "Maximum amount of capacity for the energy storage block", "Default: 1000000" })
			@Config.RangeInt(min = 1000, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int MAX_CAPACITY_ENERGY_STORAGE = 1000000;

			@Config.Name("Output energy storage block")
			@Config.Comment(
			{ "Maximum amount of output for the energy storage block", "Default: 1000" })
			@Config.RangeInt(min = 100, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int MAX_OUTPUT_ENERGY_STORAGE = 1000;

			@Config.Name("Input energy storage block")
			@Config.Comment(
			{ "Maximum amount of input for the energy storage block", "Default: 1000" })
			@Config.RangeInt(min = 100, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int MAX_INPUT_ENERGY_STORAGE = 1000;

			@Config.Name("Capacity metallurgical oven")
			@Config.Comment(
			{ "Maximum amount of capacity for the metallurgical oven", "Default: 100000" })
			@Config.RangeInt(min = 1000, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int MAX_CAPACITY_METALLURGICAL_OVEN = 100000;

			@Config.Name("Energy per progress metallurgical oven")
			@Config.Comment(
			{ "Amount of energy per progress for the metallurgical oven", "Default: 2048" })
			@Config.RangeInt(min = 100, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int ENERGY_PER_PROGRESS_METALLURGICAL_OVEN = 2048;

			@Config.Name("Input energy metallurgical oven")
			@Config.Comment(
			{ "Maximum amount of input for the metallurgical oven", "Default: 128" })
			@Config.RangeInt(min = 100, max = Integer.MAX_VALUE / 2)
			@Config.RequiresWorldRestart
			public int MAX_INPUT_METALLURGICAL_OVEN = 128;
		}

		public static class OreGen
		{
			@Config.Name("Generate ores")
			@Config.Comment(
			{ "Whether all ores should generate", "Default: true" })
			@Config.RequiresWorldRestart
			public boolean generateAllOres = true;

			@Config.Name("Generate bauxite ore")
			@Config.Comment(
			{ "Whether bauxite ore should generate", "Default: true" })
			@Config.RequiresWorldRestart
			public boolean generateBauxiteOre = true;

			@Config.Name("Generate copper ore")
			@Config.Comment(
			{ "Whether copper ore should generate", "Default: true" })
			@Config.RequiresWorldRestart
			public boolean generateCopperOre = true;

			@Config.Name("Generate tin ore")
			@Config.Comment(
			{ "Whether tin ore should generate", "Default: true" })
			@Config.RequiresWorldRestart
			public boolean generateTinOre = true;

		}
	}

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if (event.getModID().equals(Reference.MODID))
			{
				if (Global.OreGen.generateAllOres == false)
				{
					Global.OreGen.generateBauxiteOre = false;
					Global.OreGen.generateCopperOre = false;
					Global.OreGen.generateTinOre = false;
					System.out.println("set ores to false");
				}
				ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
				System.out.println("Changed airplanes mod config");
			}
		}
	}
}
