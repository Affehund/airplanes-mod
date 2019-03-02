
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class IronRod extends Item {
	public static ModelRegistryEvent iron_rod;
	

	public IronRod() {
		setCreativeTab(AirplanesTabs.tab);
	}
}