package com.affehund.airplanes.objects.blocks;

import com.affehund.airplanes.objects.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TinBlock extends BlockBase {
	public TinBlock(String name) {
		super(name, Material.IRON);
		setHardness(2.5F);
		setResistance(100F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.0F);
	}
}