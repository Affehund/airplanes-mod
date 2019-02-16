package com.affehund.airplanes.tab;

import net.minecraft.creativetab.CreativeTabs;

import net.minecraft.init.Blocks;

import net.minecraft.item.ItemStack;

public class AirplanesTab extends CreativeTabs {
	public AirplanesTab() {
		super("AirplanesTab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.ANVIL);
	}
}