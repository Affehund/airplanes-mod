package com.affehund.airplanes.objects.items.suitcase;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.ItemInit;
import com.affehund.airplanes.util.interfaces.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class Suitcase extends Item implements IHasModel {
	
	public Suitcase(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name); 
		setMaxStackSize(1);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);

		ItemInit.ITEMS.add(this);  
	}

	@Override
	public void registerModels() 
	{
		AirplanesMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		new InventorySuitcase(stack);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			playerIn.openGui(AirplanesMod.getInstance(), AirplanesConstants.GUI_SUITCASE, worldIn, 0, 0, 0);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
		}
	}

