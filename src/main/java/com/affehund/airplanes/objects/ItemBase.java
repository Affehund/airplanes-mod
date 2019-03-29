package com.affehund.airplanes.objects;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.ItemInit;
import com.affehund.airplanes.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
	public ItemBase(String name, CreativeTabs tab) 
	{
		setUnlocalizedName(name);
		setRegistryName(name); 
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);

		ItemInit.ITEMS.add(this);  
	}

	@Override
	public void registerModels() 
	{
		AirplanesMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
}