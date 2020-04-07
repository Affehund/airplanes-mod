package com.affehund.airplanes.core.events;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.utils.NullRecipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */
public class EventRemoveCraftingRecipes 
{
	@SubscribeEvent
	public void registerCraftingRecipes(RegistryEvent.Register<IRecipe> event)
	{
		IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>)event.getRegistry();
		removeCraftingRecipe(modRegistry, new ResourceLocation("minecraft:bread"));
	}
	
	public static void removeCraftingRecipe(IForgeRegistryModifiable<IRecipe> modRegistry, ResourceLocation recipe)
	{
		IRecipe p = (IRecipe)modRegistry.getValue(recipe);
		modRegistry.remove(recipe);
		modRegistry.register(NullRecipe.from(p));
		AirplanesMod.log.info("Removed crafting recipe '" + recipe + "'");
	}
}
