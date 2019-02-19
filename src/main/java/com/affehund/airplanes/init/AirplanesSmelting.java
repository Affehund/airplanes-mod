package com.affehund.airplanes.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AirplanesSmelting {
	public static void init() {
		GameRegistry.addSmelting(AirplanesBlocks.bauxite_ore, new ItemStack(AirplanesItems.aluminum_ingot, 1), 0.3F);
	}
}