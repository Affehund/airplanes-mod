package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.client.render.Boeing737Renderer;
import com.affehund.airplanes.common.entities.EntityBoeing737;
import com.affehund.airplanes.core.init.BlockInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler 
{
	@SubscribeEvent
    public static void registerEntityRenders()
    {
		RenderingRegistry.registerEntityRenderingHandler(EntityBoeing737.class, Boeing737Renderer.FACTORY);
		
    }
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