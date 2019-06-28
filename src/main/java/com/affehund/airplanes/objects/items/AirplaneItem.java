package com.affehund.airplanes.objects.items;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.ItemInit;

import net.minecraft.item.Item;

public class AirplaneItem extends Item
{
	public AirplaneItem(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name); 
		setMaxStackSize(1);
		setCreativeTab(AirplanesMod.AIRPLANESTAB2);

		ItemInit.ITEMS.add(this);  
	}
}