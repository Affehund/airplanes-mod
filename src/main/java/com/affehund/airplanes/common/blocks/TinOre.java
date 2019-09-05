package com.affehund.airplanes.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TinOre extends BlockBase {
	public TinOre(String name) {
		super(name, Material.IRON);
		setHardness(2.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 3);
	}
}