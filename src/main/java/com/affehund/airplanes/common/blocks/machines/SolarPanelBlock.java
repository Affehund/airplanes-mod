package com.affehund.airplanes.common.blocks.machines;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.common.blocks.BlockBase;
import com.affehund.airplanes.common.tileentities.TileEntitySolarPanel;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SolarPanelBlock extends BlockBase 
{
	public static AxisAlignedBB SOLAR_PANEL_AABB = new AxisAlignedBB(0, 0, 0, 1D, 0.375D, 1D);
	public SolarPanelBlock(String name) {
		super(name, Material.ROCK);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		setHardness(2.0F);
		setResistance(75F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
	} 
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return new TileEntitySolarPanel();
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false; 
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SOLAR_PANEL_AABB;
	}
}
