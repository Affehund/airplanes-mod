
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class Fuel extends Item {
	public static ModelRegistryEvent fuel;
	

	public Fuel() {
		setCreativeTab(AirplanesTabs.tab);
	}
}