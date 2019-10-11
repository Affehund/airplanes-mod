package com.affehund.airplanes.core.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingInit 
{
	public static void init() 
	{
		GameRegistry.addSmelting(BlockInit.BAUXITE_ORE, new ItemStack(ItemInit.ALUMINUM_INGOT, 1), 0.3F);
		GameRegistry.addSmelting(BlockInit.COPPER_ORE, new ItemStack(ItemInit.COPPER_INGOT, 1), 0.3F);
		GameRegistry.addSmelting(BlockInit.TIN_ORE, new ItemStack(ItemInit.TIN_INGOT, 1), 0.3F);
		
		GameRegistry.addSmelting(Items.IRON_INGOT, new ItemStack(ItemInit.STEEL_INGOT, 1), 0.2F);
		GameRegistry.addSmelting(ItemInit.RAW_SILICON, new ItemStack(ItemInit.SILICON, 1), 2F);	
	}
}