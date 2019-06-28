package com.affehund.airplanes.objects.blocks;

import com.affehund.airplanes.objects.BlockBase;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AluminumBlock extends BlockBase
{
	public AluminumBlock(String name) 
	{
		super(name, Material.IRON);
		setHardness(2.0F);
		setResistance(125F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
	}
}