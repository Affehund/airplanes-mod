package com.affehund.airplanes.core.events;

import net.minecraftforge.common.MinecraftForge;

public class EventHandler 
{
	public static void registerEvents()
	{
		RecipeRemover recipeEvent = new RecipeRemover();

		MinecraftForge.EVENT_BUS.register(recipeEvent);
	}
}
