package com.affehund.airplanes.objects;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.ItemInit;

import net.minecraft.item.Item;

public class ItemBase extends Item
{
	public ItemBase(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		ItemInit.ITEMS.add(this);
	}
}