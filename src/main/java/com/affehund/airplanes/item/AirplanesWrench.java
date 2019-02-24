package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;

import net.minecraft.item.Item;

public class AirplanesWrench extends Item {
	
	public AirplanesWrench() {
		setCreativeTab(AirplanesTabs.tab);
		setMaxStackSize(1);
	}
}
