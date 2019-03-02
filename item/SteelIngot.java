
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class SteelIngot extends Item {
	public static ModelRegistryEvent steel_ingot;
	

	public SteelIngot() {
		setCreativeTab(AirplanesTabs.tab);
	}
}