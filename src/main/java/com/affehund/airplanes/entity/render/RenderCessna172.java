package com.affehund.airplanes.entity.render;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.entity.AirplaneCessna172;
import com.affehund.airplanes.entity.model.ModelCessna172;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCessna172 extends RenderLiving<AirplaneCessna172>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/cessna172.png");

	public RenderCessna172(RenderManager manager) 

	{
		super(manager, new ModelCessna172(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(AirplaneCessna172 entity) 
	{
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(AirplaneCessna172 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
