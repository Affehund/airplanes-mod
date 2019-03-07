package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;

public class AirplanesHammer extends Item {
	public AirplanesHammer() {
		setCreativeTab(AirplanesTabs.tab);
		setMaxStackSize(1);
		setMaxDamage(64);
	}
}

