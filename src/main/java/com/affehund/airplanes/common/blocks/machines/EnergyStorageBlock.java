package com.affehund.airplanes.common.blocks.machines;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.blocks.BlockBase;
import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyStorageBlock extends BlockBase 
{
	public EnergyStorageBlock(String name) 
	{
		super(name, Material.IRON);
		setHardness(2.0F);
		setResistance(150F);
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
		return new TileEntityEnergyStorageBlock();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(AirplanesMod.instance, Reference.GUI_ENERGY_STORAGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
}