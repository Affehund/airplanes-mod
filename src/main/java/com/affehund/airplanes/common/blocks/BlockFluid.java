package com.affehund.airplanes.common.blocks;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.init.BlockInit;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluid extends BlockFluidClassic 
{
	public BlockFluid(String name, Fluid fluid, Material material, CreativeTabs tab)
	{
		super(fluid, material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);

		BlockInit.BLOCKS.add(this);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
}
