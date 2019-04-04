package com.affehund.airplanes.creativetabs;

import com.affehund.airplanes.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AirplanesTab2 extends CreativeTabs {
	public AirplanesTab2() {
		super("AirplanesVehicles");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.AIRPLANE_ITEM);
	}
}