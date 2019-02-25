package com.affehund.airplanes.block;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.init.AirplanesTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class PartsBuilder extends Block
{
    public PartsBuilder(Material materialIn) {
    	super(Material.ROCK);
		setCreativeTab(AirplanesTabs.tab);
		setHardness(1.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 0);
	}
   
}