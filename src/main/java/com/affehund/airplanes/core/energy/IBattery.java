package com.affehund.airplanes.core.energy;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
public interface IBattery
{
	public static int maxEnergy = 1000;
	public static final int energy = maxEnergy;
	
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flags);
	
	static void addInformationLocalized(List<String> tooltip, String key, Object... parameters)
	{
		String translated = I18n.format(key, parameters);
		Collections.addAll(tooltip, StringUtils.split(translated, "\n"));
	}
	
	public static int getEnergy()
	{
		return energy;
	}
	
	public static int getMaxEnergy()
	{
		return maxEnergy;
	}
}
