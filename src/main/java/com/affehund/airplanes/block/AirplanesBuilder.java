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



public class AirplanesBuilder extends Block
{
    public AirplanesBuilder(Material materialIn) {
    	super(Material.ROCK);
		setCreativeTab(AirplanesTabs.tab);
		setHardness(1.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 0);
	}

    public boolean onBlockActivated(
          World parWorld, 
          BlockPos parBlockPos, 
          IBlockState parIBlockState, 
          EntityPlayer parPlayer, 
          EnumFacing parSide, 
          float hitX, 
          float hitY, 
          float hitZ)
    {
        if(!parWorld.isRemote)
        {
         // DEBUG
         System.out.println("AirplanesBuilder onBlockActivated");
         
         parPlayer.openGui(
        	   AirplanesMod.instance, 
        	   AirplanesMod.GUI_Register.AirplanesBuilder.ordinal(), 
               parWorld, 
               parBlockPos.getX(), 
               parBlockPos.getY(), 
               parBlockPos.getZ()
               );
        }
        return true;
    }   
}