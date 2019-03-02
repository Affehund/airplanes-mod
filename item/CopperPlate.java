
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class CopperPlate extends Item {
	public static ModelRegistryEvent copper_plate;
	

	public CopperPlate() {
		setCreativeTab(AirplanesTabs.tab);
	}
}