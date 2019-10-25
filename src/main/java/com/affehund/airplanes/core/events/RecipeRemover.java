package com.affehund.airplanes.core.events;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.core.utils.NullRecipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class RecipeRemover 
{
	@SuppressWarnings("rawtypes")
	@SubscribeEvent
	public void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{
		IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable)event.getRegistry();
		//removeRecipe(modRegistry, new ResourceLocation("minecraft:bread"), Reference.MODID);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeRecipe(IForgeRegistryModifiable modRegistry, ResourceLocation recipe, String modID)
	{
		IRecipe p = (IRecipe)modRegistry.getValue(recipe);
		modRegistry.remove(recipe);
		modRegistry.register(NullRecipe.from(p));
		System.out.println("Removing recipe for '" + recipe + "'");
	}
}
