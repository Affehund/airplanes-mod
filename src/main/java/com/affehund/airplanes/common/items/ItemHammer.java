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

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */
public class ItemHammer extends Item 
{
	public ItemHammer(String name) 
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
        	list.add(I18n.format("airplanes.tooltips.hammer2"));
        }
        else
        {
        	list.add(I18n.format("airplanes.tooltips.hammer1"));
        	list.add(I18n.format("airplanes.tooltips.hold_shift"));
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

