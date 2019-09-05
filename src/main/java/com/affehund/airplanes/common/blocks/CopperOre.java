package com.affehund.airplanes.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CopperOre extends BlockBase {
	public CopperOre(String name) {
		super(name, Material.IRON);
		setHardness(1.5F);
		setResistance(80F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
	}
}