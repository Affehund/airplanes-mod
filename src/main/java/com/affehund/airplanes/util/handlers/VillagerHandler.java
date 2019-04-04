package com.affehund.airplanes.util.handlers;

import java.util.Random;

import com.affehund.airplanes.AirplanesConstants;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;


public class VillagerHandler {
	
	private static final VillagerRegistry VILLAGER_REGISTRY = VillagerRegistry.instance();
	public static VillagerRegistry.VillagerProfession PROF_PILOTS;


	public static void initTrades()
	{
		PROF_PILOTS = new VillagerRegistry.VillagerProfession(AirplanesConstants.MODID+":pilot", "airplanes:textures/entities/pilot.png", "airplanes:textures/entities/zombie_pilot.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(PROF_PILOTS);

		VillagerRegistry.VillagerCareer career_pilot = new VillagerRegistry.VillagerCareer(PROF_PILOTS, AirplanesConstants.MODID+".pilot");
		career_pilot.addTrade(1, new TradeEmeraldsForBoots());
	}
	
	 public static class TradeEmeraldsForBoots implements ITradeList
	    {

	        public ItemStack stack;

	        public EntityVillager.PriceInfo priceInfo;

	        public TradeEmeraldsForBoots()
	        {
	            stack = new ItemStack(Items.GOLDEN_BOOTS);
	            priceInfo = new PriceInfo(17, 64);
	        }

	public static VillagerRegistry getVillagerRegistry() {
		return VILLAGER_REGISTRY;
	}

	  @Override
      public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
      {
          int actualPrice = 1;

          if (priceInfo != null)
          {
              actualPrice = priceInfo.getPrice(random);
          }

          ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);
          recipeList.add(new MerchantRecipe(stackToPay, stack));
      }
  }
}
