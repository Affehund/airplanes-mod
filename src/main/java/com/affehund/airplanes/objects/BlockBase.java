package com.affehund.airplanes.objects;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.BlockInit;
import com.affehund.airplanes.init.ItemInit;
import com.affehund.airplanes.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	@Override
	public void registerModels() 
	{
		AirplanesMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}