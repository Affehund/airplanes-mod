package com.affehund.airplanes.objects.blocks;

import com.affehund.airplanes.objects.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PartsBuilder extends BlockBase
{
    public PartsBuilder(String name) {
    	super(name, Material.GLASS);
		setHardness(1.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 0);
	}
   
}