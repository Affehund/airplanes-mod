package com.affehund.airplanes.core.init;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;

import com.affehund.airplanes.common.items.AirplaneItem;
import com.affehund.airplanes.common.items.AirplanesHammer;
import com.affehund.airplanes.common.items.AirplanesWrench;
import com.affehund.airplanes.common.items.ItemBase;
import com.affehund.airplanes.common.items.misc.BookOfAirplanes;
import com.affehund.airplanes.common.items.misc.Suitcase;
	
public class ItemInit 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();

	//Items and co.
	public static final Item AIRPLANE_ITEM = new AirplaneItem("airplane_item");	
	public static final Item AIRPLANES_HAMMER = new AirplanesHammer("airplanes_hammer");	
	public static final Item AIRPLANES_WRENCH = new AirplanesWrench("airplanes_wrench");
	
	public static final Item SUITCASE = new Suitcase("suitcase");
	
	//materials
	public static final Item ALUMINUM_INGOT = new ItemBase("aluminum_ingot");
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot");
	public static final Item STEEL_INGOT = new ItemBase("steel_ingot");
	public static final Item TIN_INGOT = new ItemBase("tin_ingot");
	
	public static final Item ALUMINUM_PLATE = new ItemBase("aluminum_plate");
	public static final Item COPPER_PLATE = new ItemBase("copper_plate");
	public static final Item IRON_PLATE = new ItemBase("iron_plate");
	public static final Item STEEL_PLATE = new ItemBase("steel_plate");	
	
	public static final Item ALUMINUM_ROD = new ItemBase("aluminum_rod");
	public static final Item COPPER_ROD = new ItemBase("copper_rod");
	public static final Item IRON_ROD = new ItemBase("iron_rod");
	public static final Item STEEL_ROD = new ItemBase("steel_rod");	
		
	public static final Item COPPER_NUGGET = new ItemBase("copper_nugget");	
	public static final Item TIN_NUGGET = new ItemBase("tin_nugget");
		
		
	public static final Item FUEL = new ItemBase("fuel");
	public static final Item SILICON = new ItemBase("silicon");
	public static final Item RAW_SILICON = new ItemBase("raw_silicon");
	
	//technical items
	public static final Item DIAMOND_CIRCUIT_BOARD = new ItemBase("diamond_circuit_board");
	public static final Item GOLDEN_CIRCUIT_BOARD = new ItemBase("golden_circuit_board");
	public static final Item IRON_CIRCUIT_BOARD = new ItemBase("iron_circuit_board");
	public static final Item DIAMOND_CPU = new ItemBase("diamond_cpu");
	public static final Item GOLDEN_CPU = new ItemBase("golden_cpu");
	public static final Item IRON_CPU = new ItemBase("iron_cpu");
	public static final Item DISPLAY = new ItemBase("display");
	
	//gears
	public static final Item DIAMOND_GEAR = new ItemBase("diamond_gear");
	public static final Item GOLDEN_GEAR = new ItemBase("golden_gear");
	public static final Item IRON_GEAR = new ItemBase("iron_gear");
	public static final Item STONE_GEAR = new ItemBase("stone_gear");
	public static final Item WOODEN_GEAR = new ItemBase("wooden_gear");

	public static final Item BOOK_OF_AIRPLANES = new BookOfAirplanes("book_of_airplanes");
	
}