package com.affehund.airplanes.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CopperCoil extends BlockBase {
	public CopperCoil(String name) {
		super(name, Material.IRON);
		setHardness(1.5F);
		setResistance(100F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}
}