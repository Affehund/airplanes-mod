package com.affehund.airplanes.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AsphaltBlock extends BlockBase {
	public AsphaltBlock(String name) {
		super(name, Material.CLAY);
		setHardness(1.25F);
		setResistance(150F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 0);
	}
}