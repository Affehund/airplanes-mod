package com.affehund.airplanes.objects.items.suitcase;

import com.affehund.airplanes.Reference;
import java.util.List;
import org.lwjgl.input.Keyboard;
import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.ItemInit;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class Suitcase extends Item
{
	
	public Suitcase(String name) 
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
        	list.add(I18n.format("airplanes.tooltips_suitcase1"));
        	list.add(I18n.format("airplanes.tooltips_suitcase2"));
        }
        else
        {
        	list.add(I18n.format("airplanes.tooltips_suitcase_normal"));
        }
    }
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		new InventorySuitcase(stack);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
		{
		if(!worldIn.isRemote) {
			playerIn.openGui(AirplanesMod.instance, Reference.GUI_SUITCASE, worldIn, 0, 0, 0);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
		}
}

