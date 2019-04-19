package com.affehund.airplanes.objects.blocks.energy_storage;

import com.affehund.airplanes.objects.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class EnergyStorageBlock extends BlockBase {
	public EnergyStorageBlock(String name) {
		super(name, Material.IRON);
		setHardness(2.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.0F);
	}
}