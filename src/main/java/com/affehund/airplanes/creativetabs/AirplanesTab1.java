package com.affehund.airplanes.creativetabs;

import com.affehund.airplanes.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AirplanesTab1 extends CreativeTabs {
	public AirplanesTab1() {
		super("AirplanesItems");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.FUEL);
	}
}