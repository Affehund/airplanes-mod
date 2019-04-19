package com.affehund.airplanes.objects.items;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.ItemInit;
import com.affehund.airplanes.util.interfaces.IHasModel;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AirplanesHammer extends Item implements IHasModel {
	
	public AirplanesHammer(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name); 
		this.setMaxStackSize(1);
		this.setMaxDamage(255);
		this.setNoRepair();
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);

		ItemInit.ITEMS.add(this);  
	}

	@Override
	public void registerModels() 
	{
		AirplanesMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	
    @Override
    public ItemStack getContainerItem(ItemStack itemstack){
        ItemStack stack = itemstack.copy();
        stack.attemptDamageItem(1, itemRand, null);
        return stack;
    } 
}

