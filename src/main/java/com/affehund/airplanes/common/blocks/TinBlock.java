package com.affehund.airplanes.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TinBlock extends BlockBase {
	public TinBlock(String name) {
		super(name, Material.IRON);
		setHardness(2.5F);
		setResistance(125F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.0F);
	}
}