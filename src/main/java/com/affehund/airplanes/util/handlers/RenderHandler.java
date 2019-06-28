package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.entity.AirplaneCessna172;
import com.affehund.airplanes.entity.render.RenderCessna172;
import com.affehund.airplanes.init.BlockInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(AirplaneCessna172.class, new IRenderFactory<AirplaneCessna172>()
		{
			@Override
			public Render<? super AirplaneCessna172> createRenderFor(RenderManager manager) 
			{
				return new RenderCessna172(manager);
			}
		});
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