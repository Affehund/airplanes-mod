package com.affehund.airplanes.objects.items;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.ItemInit;
import com.affehund.airplanes.util.interfaces.IHasModel;

import net.minecraft.item.Item;

public class AirplaneItem extends Item implements IHasModel
{
	public AirplaneItem(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name); 
		setMaxStackSize(1);
		setCreativeTab(AirplanesMod.AIRPLANESTAB);

		ItemInit.ITEMS.add(this);  
	}

	@Override
	public void registerModels() 
	{
		AirplanesMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
}