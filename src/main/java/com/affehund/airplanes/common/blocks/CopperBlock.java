package com.affehund.airplanes.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CopperBlock extends BlockBase {
	public CopperBlock(String name) {
		super(name, Material.IRON);
		setHardness(2.0F);
		setResistance(125F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.0F);
	}
}