package com.affehund.airplanes.core.init;

import java.util.Random;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;
 
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
@ObjectHolder(Reference.MODID)
public class ProfessionsInit
{
	    public final static VillagerProfession crew = null;
	    public static VillagerCareer pilot;
	    
		@Mod.EventBusSubscriber(modid=Reference.MODID)
		public static class RegistrationHandler
		{
			@SubscribeEvent
			public static void onEvent(final RegistryEvent.Register<VillagerProfession> event)
			{
				final IForgeRegistry<VillagerProfession> registry = event.getRegistry();
				AirplanesMod.log.info("Registering Villager Professions");
				registry.register(new VillagerProfession(Reference.MODID + ":crew", Reference.MODID + ":textures/entity/crew.png", Reference.MODID + ":textures/entity/crew.png"));
			}
		} 

	    public static void associateCareersAndTrades()
	    {
	        // DEBUG
	    	AirplanesMod.log.info("Associating careers and trades to villager professions");
	        pilot = (new VillagerCareer(crew, "pilot"))
	                .addTrade(1, new TradeEmeraldsForSolarPanel())
	        		.addTrade(1, new TradeEmeraldsForWrench());
	    }

		public static class TradeEmeraldsForSolarPanel implements ITradeList
		{
			public ItemStack stack;

			public EntityVillager.PriceInfo priceInfo;
			public TradeEmeraldsForSolarPanel()
			{
				stack = new ItemStack(BlockInit.SOLAR_PANEL, 1, 0);
				priceInfo = new PriceInfo(5, 12);
			}

			@Override
			public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
			{
				int actualPrice = 1;

				if(priceInfo != null)
				{
					actualPrice = priceInfo.getPrice(random);
				}

				ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);
				recipeList.add(new MerchantRecipe(stackToPay, stack));
				
				System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
			}
		}
	    
	    public static class TradeEmeraldsForWrench implements ITradeList
		{
			public ItemStack stack;

			public EntityVillager.PriceInfo priceInfo;
			public TradeEmeraldsForWrench()
			{
				stack = new ItemStack(ItemInit.AIRPLANES_WRENCH, 1, 0);
				priceInfo = new PriceInfo(2, 5);
			}

			@Override
			public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
			{
				int actualPrice = 1;
				if(priceInfo != null)
				{
					actualPrice = priceInfo.getPrice(random);
				}

				ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);
				recipeList.add(new MerchantRecipe(stackToPay, stack));

				System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
			}
		}
	}
