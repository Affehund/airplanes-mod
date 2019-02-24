package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;

import net.minecraft.item.Item;

public class Silicon extends Item {
	
	public Silicon() {
		setCreativeTab(AirplanesTabs.tab);
		setMaxStackSize(64);
	}
}
