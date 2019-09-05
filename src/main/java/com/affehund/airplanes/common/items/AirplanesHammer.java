package com.affehund.airplanes.common.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class AirplanesHammer extends Item 
{
	
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
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flags)
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
        	list.add(I18n.format("airplanes.tooltips_hammer1"));
        	list.add(I18n.format("airplanes.tooltips_hammer2"));
        }
        else
        {
        	list.add(I18n.format("airplanes.tooltips_hammer_normal"));
        }
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

