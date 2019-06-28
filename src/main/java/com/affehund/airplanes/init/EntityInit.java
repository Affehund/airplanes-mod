package com.affehund.airplanes.init;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.entity.AirplaneCessna172;
import com.affehund.airplanes.AirplanesMod;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit 
{
	public static void registerEntities()

	{

		registerEntity("cessna172", AirplaneCessna172.class, Reference.AIRPLANE_CESSNA172, 50, 11437146, 000000);

	}

	

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)

	{

		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, AirplanesMod.instance, range, 1, true, color1, color2);

	}
}
