package com.affehund.airplanes.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AirplanesSmelting {
	public static void init() {
		GameRegistry.addSmelting(BlockInit.BAUXITE_ORE, new ItemStack(ItemInit.ALUMINUM_INGOT, 1), 0.1F);
		GameRegistry.addSmelting(BlockInit.COPPER_ORE, new ItemStack(ItemInit.COPPER_INGOT, 1), 0.1F);
		GameRegistry.addSmelting(BlockInit.TIN_ORE, new ItemStack(ItemInit.TIN_INGOT, 1), 0.1F);
		
		GameRegistry.addSmelting(Items.IRON_INGOT, new ItemStack(ItemInit.STEEL_INGOT, 1), 0.1F);
		GameRegistry.addSmelting(Blocks.SANDSTONE, new ItemStack(ItemInit.SILICON, 3), 0F);
		
	}
}