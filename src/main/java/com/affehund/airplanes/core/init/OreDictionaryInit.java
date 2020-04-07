package com.affehund.airplanes.core.init;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

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
public class OreDictionaryInit
{
	public static void registerOres()
	{
//		OreDictionary.registerOre("blockAluminum", BlockInit.ALUMINUM_BLOCK);
//		OreDictionary.registerOre("blockCopper", BlockInit.COPPER_BLOCK);
//		OreDictionary.registerOre("blockSteel", BlockInit.STEEL_BLOCK);
//		OreDictionary.registerOre("blockTin", BlockInit.TIN_BLOCK);

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

//		OreDictionary.registerOre("oreCopper", BlockInit.COPPER_ORE);
//		OreDictionary.registerOre("oreBauxite", BlockInit.BAUXITE_ORE);
//		OreDictionary.registerOre("oreTin", BlockInit.TIN_ORE);

		OreDictionary.registerOre("plateAluminum", ItemInit.ALUMINUM_PLATE);
		OreDictionary.registerOre("plateCopper", ItemInit.COPPER_PLATE);
		OreDictionary.registerOre("plateSteel", ItemInit.STEEL_PLATE);

		OreDictionary.registerOre("stickAluminum", ItemInit.ALUMINUM_ROD);
		OreDictionary.registerOre("stickCopper", ItemInit.COPPER_ROD);
		OreDictionary.registerOre("stickSteel", ItemInit.STEEL_ROD);

		OreDictionary.registerOre("itemSilicon", ItemInit.SILICON);
		OreDictionary.registerOre("ingotSilicon", ItemInit.SILICON);
		
		
		OreDictionary.registerOre("ingotCopper", Items.APPLE);
	}
}
