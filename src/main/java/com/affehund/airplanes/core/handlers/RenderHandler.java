package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.common.entities.EntityBoeing737;
import com.affehund.airplanes.core.init.BlockInit;

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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler 
{
	/*public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCessna172.class, new IRenderFactory<EntityCessna172>()
		{
			@Override
			public Render<? super EntityCessna172> createRenderFor(RenderManager manager) 
			{
				//return new RenderCessna172(manager);
			}
		});
	}*/
	
	public static void registerEntityRenders()
	{
//		RenderingRegistry.registerEntityRenderingHandler(EntityBoeing737.class, new IRenderFactory<EntityBoeing737>()
//		{
//			@Override
//			public Render<? super EntityBoeing737> createRenderFor(RenderManager manager) 
//			{
//				return new RenderCessna172(manager);
//			}
//		});
	}
	
//    @SideOnly(Side.CLIENT)
//	public static void registerCustomMeshesAndStates()
//	{
//		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.OIL), new ItemMeshDefinition() {
//			@Override
//			public ModelResourceLocation getModelLocation(ItemStack stack) 
//			{
//				return new ModelResourceLocation("airplanes:oil", "fluid");
//			}
//		});
//		
//		ModelLoader.setCustomStateMapper(BlockInit.OIL, new StateMapperBase() 
//		{	
//			@Override
//			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
//			{
//				return new ModelResourceLocation("airplanes:oil", "fluid");
//			}
//		});
//	}
}