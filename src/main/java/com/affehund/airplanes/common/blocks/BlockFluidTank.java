package com.affehund.airplanes.common.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.blocks.tools.BlockMachineBase;
import com.affehund.airplanes.common.tileentities.TileEntityFluidTank;
import com.affehund.airplanes.core.compat.top.ITOPInfoProvider;
import com.affehund.airplanes.core.utils.TextUtilities;

import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.NumberFormat;
import mcjty.theoneprobe.api.ProbeMode;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author Affehund
 * @edited 25.02.2020
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
public class BlockFluidTank extends BlockMachineBase implements ITileEntityProvider, ITOPInfoProvider
{
	public static final ResourceLocation TANK = new ResourceLocation(Reference.MODID, "tank");

	public BlockFluidTank(String name)
	{
		super(name, Material.GLASS);
		setHardness(1.0f);
		setSoundType(SoundType.GLASS);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flags)
	{
		NBTTagCompound tagCompound = stack.getTagCompound();
		if (tagCompound != null)
		{
			NBTTagCompound nbt = tagCompound.getCompoundTag("tank");
			FluidStack fluidStack = null;
			if (!nbt.hasKey("Empty"))
			{
				fluidStack = FluidStack.loadFluidStackFromNBT(nbt);
			}
			if (fluidStack == null)
			{
				return;
			} else
			{
				String name = fluidStack.getLocalizedName();
				TextUtilities.addInformationLocalizedForEnergyAndFluid(tooltip,
						TextFormatting.GREEN + name + ": " + fluidStack.amount + " mb");
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityFluidTank();
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			if (player.isSneaking())
			{
				player.openGui(AirplanesMod.instance, Reference.GUI_FLUID_TANK, world, pos.getX(), pos.getY(),
						pos.getZ());
			} else
			{
				FluidUtil.interactWithFluidHandler(player, hand, world, pos, side);
			}
		}
		return true;
	}

	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntityFluidTank te = (TileEntityFluidTank) world.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for (int slot = 0; slot < handler.getSlots(); slot++)
		{
			ItemStack stack = handler.getStackInSlot(slot);
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean isBlockNormalCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Optional.Method(modid = "theoneprobe")
	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world,
			IBlockState blockState, IProbeHitData data)
	{
		TileEntity tileentity = world.getTileEntity(data.getPos());
		if (tileentity instanceof TileEntityFluidTank)
		{
			TileEntityFluidTank tileEntity = (TileEntityFluidTank) tileentity;
			if (tileEntity.tank.getFluid() != null)
			{
				probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER))
						.item(FluidUtil.getFilledBucket(new FluidStack(tileEntity.tank.getFluid(), 1000)))
						.text("Fluid: " + tileEntity.tank.getFluid().getLocalizedName());

				probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER)).progress(
						tileEntity.tank.getFluidAmount(), tileEntity.tank.getCapacity(),
						new ProgressStyle().width(100).suffix("mB").numberFormat(NumberFormat.COMMAS)
								.filledColor(0xff005588).alternateFilledColor(0xff001133));
				if (mode != ProbeMode.NORMAL)
				{
					probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER))
							.text(TextFormatting.YELLOW + "Temperature: "
									+ (tileEntity.tank.getFluid().getFluid().getTemperature()
											- Reference.CALVINTOCELSIUS)
									+ " °C");
				}
			} else
			{
				probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER))
						.text(TextFormatting.RED + "No fluid");
			}
		}
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos pos)
	{
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileEntityFluidTank)
		{
			TileEntityFluidTank tank = (TileEntityFluidTank) tile;
			if (tank.getTank() != null)
			{
				return tank.getComparatorInputOverride();
			}
		}
		return 0;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}
}