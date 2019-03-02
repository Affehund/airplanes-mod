
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class CopperRod extends Item {
	public static ModelRegistryEvent copper_rod;
	

	public CopperRod() {
		setCreativeTab(AirplanesTabs.tab);
	}
}