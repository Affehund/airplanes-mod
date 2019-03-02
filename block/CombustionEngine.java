package com.affehund.airplanes.block;


import javax.annotation.Nullable;

import com.affehund.airplanes.init.AirplanesTabs;
import com.affehund.airplanes.tileentity.TileEntityCombustionEngine;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;


public class CombustionEngine extends Block implements ITileEntityProvider {
	

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public CombustionEngine() {
		super(Material.ROCK);
		setCreativeTab(AirplanesTabs.tab);
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCombustionEngine();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}	
}