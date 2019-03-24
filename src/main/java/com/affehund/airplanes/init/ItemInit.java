package com.affehund.airplanes.init;

import java.util.ArrayList;
import java.util.List;
import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.objects.ItemBase;
import com.affehund.airplanes.objects.items.AirplaneItem;
import com.affehund.airplanes.objects.items.AirplanesHammer;
import com.affehund.airplanes.objects.items.AirplanesWrench;

import net.minecraft.item.Item;
	
public class ItemInit 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();

	//Items
	public static final Item AIRPLANE_ITEM = new AirplaneItem("airplane_item");	
	public static final Item AIRPLANES_HAMMER = new AirplanesHammer("airplanes_hammer");	
	public static final Item AIRPLANES_WRENCH = new AirplanesWrench("airplanes_wrench");
	
	public static final Item ALUMINUM_INGOT = new ItemBase("aluminum_ingot", AirplanesMod.AIRPLANESTAB);	
	public static final Item ALUMINUM_PLATE = new ItemBase("aluminum_plate", AirplanesMod.AIRPLANESTAB);	
	public static final Item ALUMINUM_ROD = new ItemBase("aluminum_rod", AirplanesMod.AIRPLANESTAB);	
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot", AirplanesMod.AIRPLANESTAB);	
	public static final Item COPPER_NUGGET = new ItemBase("copper_nugget", AirplanesMod.AIRPLANESTAB);	
	public static final Item COPPER_PLATE = new ItemBase("copper_plate", AirplanesMod.AIRPLANESTAB);	
	public static final Item COPPER_ROD = new ItemBase("copper_rod", AirplanesMod.AIRPLANESTAB);	
	public static final Item FUEL = new ItemBase("fuel", AirplanesMod.AIRPLANESTAB);
	public static final Item IRON_PLATE = new ItemBase("iron_plate", AirplanesMod.AIRPLANESTAB);
	public static final Item IRON_ROD = new ItemBase("iron_rod", AirplanesMod.AIRPLANESTAB);
	public static final Item SILICON = new ItemBase("silicon", AirplanesMod.AIRPLANESTAB);
	public static final Item STEEL_INGOT = new ItemBase("steel_ingot", AirplanesMod.AIRPLANESTAB);	
	public static final Item STEEL_PLATE = new ItemBase("steel_plate", AirplanesMod.AIRPLANESTAB);	
	public static final Item STEEL_ROD = new ItemBase("steel_rod", AirplanesMod.AIRPLANESTAB);	
	public static final Item TIN_INGOT = new ItemBase("tin_ingot", AirplanesMod.AIRPLANESTAB);	
	public static final Item TIN_NUGGET = new ItemBase("tin_nugget", AirplanesMod.AIRPLANESTAB);
	
	public static final Item DIAMOND_GEAR = new ItemBase("diamond_gear", AirplanesMod.AIRPLANESTAB);
	public static final Item GOLDEN_GEAR = new ItemBase("golden_gear", AirplanesMod.AIRPLANESTAB);
	public static final Item IRON_GEAR = new ItemBase("iron_gear", AirplanesMod.AIRPLANESTAB);
	public static final Item STONE_GEAR = new ItemBase("stone_gear", AirplanesMod.AIRPLANESTAB);
	public static final Item WOODEN_GEAR = new ItemBase("wooden_gear", AirplanesMod.AIRPLANESTAB);

	
}