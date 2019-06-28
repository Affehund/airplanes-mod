package com.affehund.airplanes.init;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryInit {
	public static void registerOres () 
	{
		
		OreDictionary.registerOre("blockAluminum", BlockInit.ALUMINUM_BLOCK);
		OreDictionary.registerOre("blockCopper", BlockInit.COPPER_BLOCK);
		OreDictionary.registerOre("blockSteel", BlockInit.STEEL_BLOCK);
		OreDictionary.registerOre("blockTin", BlockInit.TIN_BLOCK);
		
		OreDictionary.registerOre("gearWood", ItemInit.WOODEN_GEAR);
		OreDictionary.registerOre("gearStone", ItemInit.STONE_GEAR);
		OreDictionary.registerOre("gearIron", ItemInit.IRON_GEAR);
		OreDictionary.registerOre("gearGold", ItemInit.GOLDEN_GEAR);
		OreDictionary.registerOre("gearDiamond", ItemInit.DIAMOND_GEAR);
		
		OreDictionary.registerOre("ingotAluminum", ItemInit.ALUMINUM_INGOT);	
		OreDictionary.registerOre("ingotCopper", ItemInit.COPPER_INGOT);
		OreDictionary.registerOre("ingotSteel", ItemInit.STEEL_INGOT);  
		OreDictionary.registerOre("ingotTin", ItemInit.TIN_INGOT);
		
		OreDictionary.registerOre("nuggetCopper", ItemInit.COPPER_NUGGET);
		OreDictionary.registerOre("nuggetTin", ItemInit.TIN_NUGGET);
		
		OreDictionary.registerOre("oreCopper", BlockInit.COPPER_ORE);
		OreDictionary.registerOre("oreBauxite", BlockInit.BAUXITE_ORE);		
		OreDictionary.registerOre("oreTin", BlockInit.TIN_ORE);
		
		OreDictionary.registerOre("plateAluminum", ItemInit.ALUMINUM_PLATE);	
		OreDictionary.registerOre("plateCopper", ItemInit.COPPER_PLATE);
		OreDictionary.registerOre("plateSteel", ItemInit.STEEL_PLATE);  
		
		OreDictionary.registerOre("stickAluminum", ItemInit.ALUMINUM_ROD);	
		OreDictionary.registerOre("stickCopper", ItemInit.COPPER_ROD);
		OreDictionary.registerOre("stickSteel", ItemInit.STEEL_ROD); 
		
		OreDictionary.registerOre("itemSilicon", ItemInit.SILICON); 
		OreDictionary.registerOre("ingotSilicon", ItemInit.SILICON); 
	}		
}


