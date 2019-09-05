package com.affehund.airplanes.common.items.misc;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BookOfAirplanes extends Item {

	public BookOfAirplanes(String name) 
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
        	list.add(I18n.format("airplanes.tooltips_book1"));
        	list.add(I18n.format("airplanes.tooltips_book2"));
        }
        else
        {
        	list.add(I18n.format("airplanes.tooltips_book_normal1"));
        	list.add(I18n.format("airplanes.tooltips_book_normal2"));
        }
    }
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {

		player.openGui(AirplanesMod.instance, Reference.GUI_BOOK, world, x, y, z);
		return true;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui(AirplanesMod.instance, Reference.GUI_BOOK, world, -1, -1, -1);
		return stack;
	}
}
