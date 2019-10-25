package com.affehund.airplanes.core.init;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.entities.EntityBoeing737;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInit 
{
	public static void registerEntities() 
	{
		int id = 1;
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "boeing737"), EntityBoeing737.class, "boeing737", id++, AirplanesMod.instance, 128, 40, false);
		AirplanesMod.log.debug("Next entity id: " + id);
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() 
	{
		
    }
}
