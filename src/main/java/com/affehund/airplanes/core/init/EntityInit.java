package com.affehund.airplanes.core.init;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.entities.EntityBoeing737;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	public static void registerEntities() {
		int id = 0;
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "boeing737"), EntityBoeing737.class, "boeing737", id++, AirplanesMod.instance, 64, 20, false);
	}
}
