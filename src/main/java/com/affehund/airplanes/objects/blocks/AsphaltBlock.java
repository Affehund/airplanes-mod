package com.affehund.airplanes.objects.blocks;

import com.affehund.airplanes.objects.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AsphaltBlock extends BlockBase {
	public AsphaltBlock(String name) {
		super(name, Material.ROCK);
		setHardness(1.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 0);
		setLightLevel(0.0F);
	}
}