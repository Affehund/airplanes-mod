
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class IronPlate extends Item {
	public static ModelRegistryEvent iron_plate;
	

	public IronPlate() {
		setCreativeTab(AirplanesTabs.tab);
	}
}