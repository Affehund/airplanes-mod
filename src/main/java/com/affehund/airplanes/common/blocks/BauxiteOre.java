package com.affehund.airplanes.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BauxiteOre extends BlockBase {
	public BauxiteOre(String name) {
		super(name, Material.IRON);
		setHardness(2.0F);
		setResistance(150F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
	}
}