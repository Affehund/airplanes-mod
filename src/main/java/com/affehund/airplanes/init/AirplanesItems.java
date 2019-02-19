package com.affehund.airplanes.init;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.item.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class AirplanesItems {
	public static final AluminumIngot aluminum_ingot = new AluminumIngot();
	public static final Fuel fuel = new Fuel();

	
	public static void init() {
		setName(aluminum_ingot, "aluminum_ingot");
		setName(fuel, "fuel");
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		registry.register(aluminum_ingot);
		registry.register(fuel);
	}

	
	public static void setName(Item item, String name) {
		item.setRegistryName(new ResourceLocation(AirplanesConstants.MODID, name));
		item.setUnlocalizedName(name);
	}
}