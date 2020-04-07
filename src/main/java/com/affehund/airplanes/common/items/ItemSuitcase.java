package com.affehund.airplanes.common.items;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.tileentities.TileEntitySuitcase;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
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
public class ItemSuitcase extends Item
{

	public ItemSuitcase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		super.onCreated(stack, worldIn, playerIn);
		new TileEntitySuitcase(stack);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if (!worldIn.isRemote)
		{
			playerIn.openGui(AirplanesMod.instance, Reference.GUI_SUITCASE, worldIn, 0, 0, 0);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
