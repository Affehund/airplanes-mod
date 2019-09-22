package com.affehund.airplanes.core.init;

import java.util.Random;

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
				System.out.println("Registering Villager Professions");
				registry.register(new VillagerProfession(Reference.MODID + ":crew", Reference.MODID + ":textures/entity/crew.png", Reference.MODID + ":textures/entity/crew.png"));
			}
		} 


	    public static void associateCareersAndTrades()
	    {
	        // DEBUG
	        System.out.println("Associating careers and trades to villager professions");
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
