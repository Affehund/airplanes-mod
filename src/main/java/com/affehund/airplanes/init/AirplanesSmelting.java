package com.affehund.airplanes.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AirplanesSmelting {
	public static void init() {
		GameRegistry.addSmelting(AirplanesBlocks.bauxite_ore, new ItemStack(AirplanesItems.aluminum_ingot, 1), 0.1F);
		GameRegistry.addSmelting(AirplanesBlocks.copper_ore, new ItemStack(AirplanesItems.copper_ingot, 1), 0.1F);
		GameRegistry.addSmelting(AirplanesBlocks.tin_ore, new ItemStack(AirplanesItems.tin_ingot, 1), 0.1F);
		
		GameRegistry.addSmelting(Items.IRON_INGOT, new ItemStack(AirplanesItems.steel_ingot, 1), 0.1F);
		GameRegistry.addSmelting(Blocks.SANDSTONE, new ItemStack(AirplanesItems.silicon, 3), 0F);
		
	}
}