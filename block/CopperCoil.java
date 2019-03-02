package com.affehund.airplanes.block;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CopperCoil extends Block {
	public CopperCoil() {
		super(Material.IRON);
		setCreativeTab(AirplanesTabs.tab);
		setHardness(2.5F);
		setResistance(100F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.0F);
	}
}