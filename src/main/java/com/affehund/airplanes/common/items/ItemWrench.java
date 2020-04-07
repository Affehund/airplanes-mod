package com.affehund.airplanes.common.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */
public class ItemWrench extends Item
{
	public ItemWrench(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		setMaxDamage(512);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flags)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add(I18n.format("airplanes.tooltips.wrench2"));
		} else
		{
			list.add(I18n.format("airplanes.tooltips.wrench1"));
			list.add(I18n.format("airplanes.tooltips.hold_shift"));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand)
	{
		ItemStack item = player.getHeldItem(hand);
		if (!world.isRemote)
		{
			IBlockState state = world.getBlockState(pos);
			for (IProperty<?> prop : state.getProperties().keySet())
			{
				if ((prop.getName().equals("facing") || prop.getName().equals("rotation"))
						&& prop.getValueClass() == EnumFacing.class)
				{
					if (prop.getAllowedValues().contains(side))
					{
						world.setBlockState(pos, state.withProperty((IProperty<EnumFacing>) prop, side));
						item.damageItem(1, player);
					}
				}
			}
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}