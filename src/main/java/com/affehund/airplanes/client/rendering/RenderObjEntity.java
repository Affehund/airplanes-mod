//package com.affehund.airplanes.client.rendering;
//
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.block.model.IBakedModel;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.client.model.IModel;
//
//public abstract class RenderObjEntity<T extends Entity> extends Render<T> 
//{
//	private IBakedModel[] bakedModels;
//
//	protected RenderObjEntity(RenderManager renderManager) 
//	{
//		super(renderManager);
//	}
//
//	@Override
//	protected ResourceLocation getEntityTexture(T entity) 
//	{
//		return null;
//	}
//	
//	protected abstract ResourceLocation[] getEntityModels();
//	protected abstract boolean preRender(T entity, int model, BufferBuilder buffer, double x, double y, double z, float entityYaw, float partialTicks);
//	protected IModel retexture(int i, IModel model) {return model;}
//	protected int getColor(int i, T entity) {return -1;}
//	
//	@Override
//	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) 
//	{
//		doRender(entity, x, y, z, entityYaw, partialTicks);
//	}
//	
//	buffer.begin;
//	
//	renderList;
//	
//	buffer.endVertex;
//}
