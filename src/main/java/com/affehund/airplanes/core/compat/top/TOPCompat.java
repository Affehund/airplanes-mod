package com.affehund.airplanes.core.compat.top;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.affehund.airplanes.AirplanesMod;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;

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
public class TOPCompat
{

	private static boolean registered;

	public static void register()
	{
		if (registered)
			return;
		registered = true;
		FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe",
				"com.affehund.airplanes.core.compat.top.TOPCompat$GetTheOneProbe");
	}

	public static class GetTheOneProbe implements Function<ITheOneProbe, Void>
	{

		public static ITheOneProbe probe;

		@Nullable
		@Override
		public Void apply(ITheOneProbe theOneProbe)
		{
			probe = theOneProbe;
			AirplanesMod.log.info("Enabled support for The One Probe");
			probe.registerProvider(new IProbeInfoProvider()
			{
				@Override
				public String getID()
				{
					return "airplanes:default";
				}

				@Override
				public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world,
						IBlockState blockState, IProbeHitData data)
				{
					if (blockState.getBlock() instanceof ITOPInfoProvider)
					{
						ITOPInfoProvider provider = (ITOPInfoProvider) blockState.getBlock();
						provider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
					}

				}
			});
			return null;
		}
	}
}
