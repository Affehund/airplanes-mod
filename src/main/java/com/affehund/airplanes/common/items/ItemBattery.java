package com.affehund.airplanes.common.items;

import java.util.List;

import javax.annotation.Nullable;

import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.energy.IBattery;
import com.affehund.airplanes.core.utils.TextUtilities;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
public class ItemBattery extends ItemBase implements IBattery
{
	public static int maxEnergy = 1000;
	public static int energy = maxEnergy;

	public ItemBattery(String name, CreativeTabs tab)
	{
		super(name);
		setMaxDamage(maxEnergy);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		energy = getEnergy(stack);
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	public int getEnergy(ItemStack stack)
	{
		return maxEnergy - getDamage(stack);
	}

	public int getMaxEnergy()
	{
		return maxEnergy;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flags)
	{
		if (energy >= maxEnergy)
		{
			return;
		} else
			TextUtilities.addInformationLocalizedForEnergyAndFluid(tooltip, "airplanes.message.energy",
					energy + " " + ConfigAirplanes.Global.Energy.ENERGY_UNIT);
	}
}
