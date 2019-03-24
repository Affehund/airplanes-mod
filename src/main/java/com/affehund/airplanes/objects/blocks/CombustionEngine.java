package com.affehund.airplanes.objects.blocks;

import javax.annotation.Nullable;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.tileentities.TileEntityCombustionEngine;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;


public class CombustionEngine extends Block implements ITileEntityProvider {
	

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public CombustionEngine(String string) {
		super(Material.ROCK);
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(AirplanesMod.AIRPLANESTAB);
	}
	
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
		{
			if(!worldIn.isRemote) 
			{
				playerIn.openGui(AirplanesMod.instance, AirplanesConstants.GUI_COMBUSTION_ENGINE, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCombustionEngine();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityCombustionEngine tileentity = (TileEntityCombustionEngine)worldIn.getTileEntity(pos);
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
		
		super.breakBlock(worldIn, pos, state);
	}
	
	@Nullable
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




