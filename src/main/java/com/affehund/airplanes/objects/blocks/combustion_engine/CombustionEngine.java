package com.affehund.airplanes.objects.blocks.combustion_engine;

import javax.annotation.Nullable;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.objects.BlockBase;

import net.minecraft.block.SoundType;
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


public class CombustionEngine extends BlockBase
{
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public CombustionEngine(String name) {
		super(name, Material.ROCK);
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		setHardness(2.5F);
		setResistance(100F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
	}
	
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
		{
			if(!worldIn.isRemote) 
			{
				playerIn.openGui(AirplanesMod.instance, AirplanesConstants.GUI_COMBUSTION_ENGINE, worldIn,
						pos.getX(), pos.getY(), pos.getZ());
			}
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
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




