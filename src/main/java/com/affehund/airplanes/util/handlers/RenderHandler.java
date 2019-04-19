package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.init.BlockInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		
	}

	public static void registerCustomMeshesAndStates()
	{
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.OIL), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) 
			{
				return new ModelResourceLocation("airplanes:oil", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlockInit.OIL, new StateMapperBase() 
		{	
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return new ModelResourceLocation("airplanes:oil", "fluid");

			}
		});
	}
}