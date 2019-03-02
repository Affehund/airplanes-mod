package com.affehund.airplanes.block;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;


public class CopperOre extends Block {
	public CopperOre() {
		super(Material.IRON);
		setCreativeTab(AirplanesTabs.tab);
		setHardness(1.5F);
		setResistance(80F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.0F);
	}
}