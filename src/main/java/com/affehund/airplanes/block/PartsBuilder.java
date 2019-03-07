package com.affehund.airplanes.block;

import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;




public class PartsBuilder extends Block
{
    public PartsBuilder(Material materialIn) {
    	super(Material.GLASS);
		setCreativeTab(AirplanesTabs.tab);
		setHardness(1.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 0);
	}
   
}