package com.affehund.airplanes.block;

import java.util.Random;

import com.affehund.airplanes.init.AirplanesBlocks;
import com.affehund.airplanes.init.AirplanesItems;
import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class AsphaltBlock extends Block {
	public AsphaltBlock() {
		super(Material.ROCK);
		setCreativeTab(AirplanesTabs.tab);
		setHardness(2.5F);
		setResistance(100F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.5F);
	}
}