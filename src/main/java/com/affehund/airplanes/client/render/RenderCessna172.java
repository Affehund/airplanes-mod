package com.affehund.airplanes.client.render;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.entities.EntityCessna172;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCessna172 extends Render<EntityCessna172>
{
public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/cessna172.png");

	public RenderCessna172(RenderManager manager) 
	{
		super(manager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCessna172 entity) 
	{
		return TEXTURES;
	}
}
