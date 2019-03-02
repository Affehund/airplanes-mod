
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class AluminumRod extends Item {
	public static ModelRegistryEvent aluminum_rod;
	

	public AluminumRod() {
		setCreativeTab(AirplanesTabs.tab);
	}
}