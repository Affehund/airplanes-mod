package com.affehund.airplanes.common.blocks.machines;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.blocks.tools.BlockMachineBase;
import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;
import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.init.BlockInit;
import com.affehund.airplanes.core.utils.TextUtilities;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class BlockCombustionEngine extends BlockMachineBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool BURNING = PropertyBool.create("burning");

	public BlockCombustionEngine(String name)
	{
		super(name, Material.ROCK);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		setHardness(2.5F);
		setResistance(150F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
		this.setDefaultState(
				this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntityCombustionEngine tile = (TileEntityCombustionEngine) worldIn.getTileEntity(pos);
		if (!worldIn.isRemote)
		{
			if (playerIn.isSneaking())
			{
				playerIn.sendStatusMessage(new TextComponentString("Energy: " + TextFormatting.GREEN + tile.energy + "/"
						+ tile.capacity + " " + ConfigAirplanes.Global.Energy.ENERGY_UNIT), true);
			} else
			{
				playerIn.openGui(AirplanesMod.instance, Reference.GUI_COMBUSTION_ENGINE, worldIn, pos.getX(),
						pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flags)
	{
		NBTTagCompound tagCompound = stack.getTagCompound();
		if (tagCompound != null)
		{
			int energy = tagCompound.getInteger("energy");
			if (energy <= 0)
			{
				return;
			} else
				TextUtilities.addInformationLocalizedForEnergyAndFluid(tooltip, "airplanes.message.energy",
						energy + " " + ConfigAirplanes.Global.Energy.ENERGY_UNIT);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		if (state.getValue(BURNING))
		{
			int facing = state.getValue(FACING).ordinal();
			float f = pos.getX() + 0.5F;
			float f1 = pos.getY() + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
			float f2 = pos.getZ() + 0.5F;
			float f3 = 0.52F;
			float f4 = rand.nextFloat() * 0.6F - 0.3F;

			if (facing == 4)
			{
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if (facing == 5)
			{
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if (facing == 2)
			{
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			} else if (facing == 3)
			{
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntityCombustionEngine tileentity = (TileEntityCombustionEngine) worldIn.getTileEntity(pos);
		if (tileentity.isBurning())
		{
			worldIn.setBlockState(pos, BlockInit.COMBUSTION_ENGINE.getDefaultState()
					.withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
		} else
			worldIn.setBlockState(pos, BlockInit.COMBUSTION_ENGINE.getDefaultState()
					.withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);
		if (tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityCombustionEngine();
	}

	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntityCombustionEngine te = (TileEntityCombustionEngine) world.getTileEntity(pos);

		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for (int slot = 0; slot < handler.getSlots(); slot++)
		{
			ItemStack stack = handler.getStackInSlot(slot);
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]
		{ BURNING, FACING });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y)
			facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}
}