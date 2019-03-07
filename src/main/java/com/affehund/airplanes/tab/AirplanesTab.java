package com.affehund.airplanes.tab;

import com.affehund.airplanes.init.AirplanesItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AirplanesTab extends CreativeTabs {
	public AirplanesTab() {
		super("Airplanes");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(AirplanesItems.fuel);
	}
}