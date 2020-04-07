package com.affehund.airplanes.common.blocks.tools;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.init.BlockInit;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
public class BlockMachineBase extends Block 
{
    public BlockMachineBase(String name, Material material) 
    {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void getDrops(NonNullList<ItemStack> result, IBlockAccess world, BlockPos pos, IBlockState metadata, int fortune) 
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        // Always check this!
        if (tileEntity instanceof IRestorableTileEntity) 
        {
            ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
            NBTTagCompound tagCompound = new NBTTagCompound();
            ((IRestorableTileEntity)tileEntity).writeRestorableToNBT(tagCompound);

            stack.setTagCompound(tagCompound);
            result.add(stack);
        } else{
            super.getDrops(result, world, pos, metadata, fortune);
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) 
    {
        if (willHarvest) 
        {
            return true; // If it will harvest, delay deletion of the block until after getDrops
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) 
    {
        super.harvestBlock(world, player, pos, state, te, stack);
        world.setBlockToAir(pos);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
    {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        TileEntity tileEntity = world.getTileEntity(pos);
		
        // Always check this!
        if (tileEntity instanceof IRestorableTileEntity) 
        {
            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound != null) 
            {
                ((IRestorableTileEntity) tileEntity).readRestorableFromNBT(tagCompound);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() 
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
