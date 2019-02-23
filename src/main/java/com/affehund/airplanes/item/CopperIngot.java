
package com.affehund.airplanes.item;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;



public class CopperIngot extends Item {
	public static ModelRegistryEvent copper_ingot;
	

	public CopperIngot() {
		setCreativeTab(AirplanesTabs.tab);
	}
}