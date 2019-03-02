package com.affehund.airplanes.util.compat;

import com.affehund.airplanes.init.AirplanesBlocks;
import com.affehund.airplanes.init.AirplanesItems;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat {
	public static void registerOreDictionary () {
		
		OreDictionary.registerOre("oreCopper", AirplanesBlocks.copper_ore);
		OreDictionary.registerOre("oreBauxite", AirplanesBlocks.bauxite_ore);		
		OreDictionary.registerOre("oreTin", AirplanesBlocks.bauxite_ore);
		
		
		OreDictionary.registerOre("blockAluminum", AirplanesBlocks.aluminum_block);
		OreDictionary.registerOre("blockCopper", AirplanesBlocks.copper_block);
		OreDictionary.registerOre("blockSteel", AirplanesBlocks.steel_block);
		OreDictionary.registerOre("blockTin", AirplanesBlocks.tin_block);
		
		OreDictionary.registerOre("ingotAluminum", AirplanesItems.aluminum_ingot);	
		OreDictionary.registerOre("ingotCopper", AirplanesItems.copper_ingot);
		OreDictionary.registerOre("ingotSteel", AirplanesItems.steel_ingot);  
		OreDictionary.registerOre("ingotTin", AirplanesItems.tin_ingot);
		
		OreDictionary.registerOre("nuggetCopper", AirplanesItems.copper_nugget);
		OreDictionary.registerOre("nuggetTin", AirplanesItems.tin_nugget);
		
		OreDictionary.registerOre("plateAluminum", AirplanesItems.aluminum_plate);	
		OreDictionary.registerOre("plateCopper", AirplanesItems.copper_plate);
		OreDictionary.registerOre("plateSteel", AirplanesItems.steel_plate);  
		
		OreDictionary.registerOre("stickAluminum", AirplanesItems.aluminum_rod);	
		OreDictionary.registerOre("stickCopper", AirplanesItems.copper_rod);
		OreDictionary.registerOre("stickSteel", AirplanesItems.steel_rod); 
	}		
}


