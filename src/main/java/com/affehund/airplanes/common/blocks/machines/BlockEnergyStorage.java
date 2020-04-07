package com.affehund.airplanes.common.blocks.machines;

import java.util.List;

import javax.annotation.Nullable;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.blocks.tools.BlockMachineBase;
import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;
import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.utils.TextUtilities;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

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
public class BlockEnergyStorage extends BlockMachineBase
{
	public BlockEnergyStorage(String name)
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			if (playerIn.isSneaking() == true)
			{
				playerIn.sendStatusMessage(new TextComponentString("Energy: " + TextFormatting.GREEN
						+ ((TileEntityEnergyStorageBlock) worldIn.getTileEntity(pos)).energyStorage.getEnergyStored()
						+ "/" + ((TileEntityEnergyStorageBlock) worldIn.getTileEntity(pos)).capacity + " "
						+ ConfigAirplanes.Global.Energy.ENERGY_UNIT), true);
			} else
			{
				playerIn.openGui(AirplanesMod.instance, Reference.GUI_ENERGY_STORAGE, worldIn, pos.getX(), pos.getY(),
						pos.getZ());
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
}