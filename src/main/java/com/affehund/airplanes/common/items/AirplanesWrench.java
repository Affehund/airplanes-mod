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


public class AirplanesWrench extends Item
{
	public AirplanesWrench(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name); 
		setMaxStackSize(1);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);

		ItemInit.ITEMS.add(this);  
	}
	
	@Override
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flags)
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
        	list.add(I18n.format("airplanes.tooltips_wrench1"));
        	list.add(I18n.format("airplanes.tooltips_wrench2"));
        }
        else
        {
        	list.add(I18n.format("airplanes.tooltips_wrench_normal"));
        }
    }
}