package com.affehund.airplanes.core.init;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.entities.EntityBoeing737;
import com.affehund.airplanes.common.entities.EntityCessna172;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class EntityInit
{
	public static void registerEntities()
	{
		registerEntity("cessna172", EntityCessna172.class, Reference.AIRPLANE_CESSNA172, 50, 11437146, 000000);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "boeing737"), EntityBoeing737.class,
				"boeing737", Reference.AIRPLANE_BOEING737, AirplanesMod.instance, 128, 40, false);
	}

	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1,
			int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id,
				AirplanesMod.instance, range, 1, true, color1, color2);
	}
}
