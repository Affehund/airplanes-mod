package com.affehund.airplanes.init;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;
import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.objects.ItemBase;
import com.affehund.airplanes.objects.items.AirplaneItem;
import com.affehund.airplanes.objects.items.AirplanesHammer;
import com.affehund.airplanes.objects.items.AirplanesWrench;
import com.affehund.airplanes.objects.items.suitcase.Suitcase;
	
public class ItemInit 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();

	//Items and co.
	public static final Item AIRPLANE_ITEM = new AirplaneItem("airplane_item");	
	public static final Item AIRPLANES_HAMMER = new AirplanesHammer("airplanes_hammer");	
	public static final Item AIRPLANES_WRENCH = new AirplanesWrench("airplanes_wrench");
	
	public static final Item SUITCASE = new Suitcase("suitcase");
	
	//materials
	public static final Item ALUMINUM_INGOT = new ItemBase("aluminum_ingot", AirplanesMod.AIRPLANESTAB1);	
	public static final Item ALUMINUM_PLATE = new ItemBase("aluminum_plate", AirplanesMod.AIRPLANESTAB1);	
	public static final Item ALUMINUM_ROD = new ItemBase("aluminum_rod", AirplanesMod.AIRPLANESTAB1);	
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot", AirplanesMod.AIRPLANESTAB1);	
	public static final Item COPPER_NUGGET = new ItemBase("copper_nugget", AirplanesMod.AIRPLANESTAB1);	
	public static final Item COPPER_PLATE = new ItemBase("copper_plate", AirplanesMod.AIRPLANESTAB1);	
	public static final Item COPPER_ROD = new ItemBase("copper_rod", AirplanesMod.AIRPLANESTAB1);	
	public static final Item FUEL = new ItemBase("fuel", AirplanesMod.AIRPLANESTAB1);
	public static final Item IRON_PLATE = new ItemBase("iron_plate", AirplanesMod.AIRPLANESTAB1);
	public static final Item IRON_ROD = new ItemBase("iron_rod", AirplanesMod.AIRPLANESTAB1);
	public static final Item SILICON = new ItemBase("silicon", AirplanesMod.AIRPLANESTAB1);
	public static final Item STEEL_INGOT = new ItemBase("steel_ingot", AirplanesMod.AIRPLANESTAB1);	
	public static final Item STEEL_PLATE = new ItemBase("steel_plate", AirplanesMod.AIRPLANESTAB1);	
	public static final Item STEEL_ROD = new ItemBase("steel_rod", AirplanesMod.AIRPLANESTAB1);	
	public static final Item TIN_INGOT = new ItemBase("tin_ingot", AirplanesMod.AIRPLANESTAB1);	
	public static final Item TIN_NUGGET = new ItemBase("tin_nugget", AirplanesMod.AIRPLANESTAB1);
	
	//tech items
	public static final Item DIAMOND_CIRCUIT_BOARD = new ItemBase("diamond_circuit_board", AirplanesMod.AIRPLANESTAB1);
	public static final Item GOLDEN_CIRCUIT_BOARD = new ItemBase("golden_circuit_board", AirplanesMod.AIRPLANESTAB1);
	public static final Item IRON_CIRCUIT_BOARD = new ItemBase("iron_circuit_board", AirplanesMod.AIRPLANESTAB1);
	public static final Item DIAMOND_CPU = new ItemBase("diamond_cpu", AirplanesMod.AIRPLANESTAB1);
	public static final Item GOLDEN_CPU = new ItemBase("golden_cpu", AirplanesMod.AIRPLANESTAB1);
	public static final Item IRON_CPU = new ItemBase("iron_cpu", AirplanesMod.AIRPLANESTAB1);
	public static final Item DISPLAY = new ItemBase("display", AirplanesMod.AIRPLANESTAB1);
	
	//gears
	public static final Item DIAMOND_GEAR = new ItemBase("diamond_gear", AirplanesMod.AIRPLANESTAB1);
	public static final Item GOLDEN_GEAR = new ItemBase("golden_gear", AirplanesMod.AIRPLANESTAB1);
	public static final Item IRON_GEAR = new ItemBase("iron_gear", AirplanesMod.AIRPLANESTAB1);
	public static final Item STONE_GEAR = new ItemBase("stone_gear", AirplanesMod.AIRPLANESTAB1);
	public static final Item WOODEN_GEAR = new ItemBase("wooden_gear", AirplanesMod.AIRPLANESTAB1);

	
}