package com.affehund.airplanes.core.init;

import java.util.ArrayList;
import java.util.List;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.common.items.ItemAirplaneItem;
import com.affehund.airplanes.common.items.FoodAirplanesBasic;
import com.affehund.airplanes.common.items.ItemHammer;
import com.affehund.airplanes.common.items.ItemWrench;
import com.affehund.airplanes.common.items.ItemBookOfAirplanes;
import com.affehund.airplanes.common.items.ItemBase;
import com.affehund.airplanes.common.items.ItemBattery;
import com.affehund.airplanes.common.items.ItemSuitcase;

import net.minecraft.item.Item;

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
public class ItemInit
{
	public static final List<Item> ITEMS = new ArrayList<Item>();

	// Items and co.
	public static final Item AIRPLANE_ITEM = new ItemAirplaneItem("airplane_item");
	public static final Item AIRPLANES_HAMMER = new ItemHammer("hammer");
	public static final Item AIRPLANES_WRENCH = new ItemWrench("wrench");

	public static final Item SUITCASE = new ItemSuitcase("suitcase");

	// materials
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

	// technical items
	public static final Item DIAMOND_CIRCUIT_BOARD = new ItemBase("diamond_circuit_board");
	public static final Item GOLDEN_CIRCUIT_BOARD = new ItemBase("golden_circuit_board");
	public static final Item IRON_CIRCUIT_BOARD = new ItemBase("iron_circuit_board");
	public static final Item DIAMOND_CPU = new ItemBase("diamond_cpu");
	public static final Item GOLDEN_CPU = new ItemBase("golden_cpu");
	public static final Item IRON_CPU = new ItemBase("iron_cpu");
	public static final Item DISPLAY = new ItemBase("display");

	// gears
	public static final Item DIAMOND_GEAR = new ItemBase("diamond_gear");
	public static final Item GOLDEN_GEAR = new ItemBase("golden_gear");
	public static final Item IRON_GEAR = new ItemBase("iron_gear");
	public static final Item STONE_GEAR = new ItemBase("stone_gear");
	public static final Item WOODEN_GEAR = new ItemBase("wooden_gear");

	public static final Item BOOK_OF_AIRPLANES = new ItemBookOfAirplanes("book_of_airplanes");

	public static final Item BANANA = new FoodAirplanesBasic("banana", 2, false);
	public static final Item BATTERY = new ItemBattery("battery", AirplanesMod.AIRPLANESTAB1);
}