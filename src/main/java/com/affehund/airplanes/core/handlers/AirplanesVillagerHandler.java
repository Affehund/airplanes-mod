package com.affehund.airplanes.core.handlers;

import java.util.Random;

import javax.annotation.Nonnull;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.core.init.ItemInit;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class AirplanesVillagerHandler
{

	private static final VillagerRegistry VILLAGER_REGISTRY = VillagerRegistry.instance();
	public static VillagerRegistry.VillagerProfession PROFESSION_PILOT;


	public static void initIEVillagerTrades()
	{
		PROFESSION_PILOT = new VillagerRegistry.VillagerProfession
				(
				Reference.MODID+":mysterious_stranger", 
                Reference.MODID+":textures/entity/pilot.png", 
                Reference.MODID+":textures/entity/zombie_pilot.png"
                );
		ForgeRegistries.VILLAGER_PROFESSIONS.register(PROFESSION_PILOT);

		VillagerRegistry.VillagerCareer career_pilot = new VillagerRegistry.VillagerCareer(PROFESSION_PILOT, Reference.MODID+".pilot");
		career_pilot.addTrade(1,
				new EmeraldForItemstack(new ItemStack(ItemInit.AIRPLANES_WRENCH, 1, 0), new EntityVillager.PriceInfo(8, 16))
		);

		VillagerRegistry.VillagerCareer career_stewardress = new VillagerRegistry.VillagerCareer(PROFESSION_PILOT, Reference.MODID+".stewardress");
		career_stewardress.addTrade(1,
				new EmeraldForItemstack(new ItemStack(ItemInit.AIRPLANES_HAMMER, 1, 6), new EntityVillager.PriceInfo(8, 16)),
				new ItemstackForEmerald(new ItemStack(ItemInit.COPPER_INGOT, 1, 0), new EntityVillager.PriceInfo(4, 7))
		);
		
	}
	
	public static VillagerRegistry getVillagerRegistry() {
		return VILLAGER_REGISTRY;
	}

	private static class EmeraldForItemstack implements ITradeList
	{
		public ItemStack buyingItem;
		public EntityVillager.PriceInfo buyAmounts;
		public EmeraldForItemstack(@Nonnull ItemStack item, @Nonnull EntityVillager.PriceInfo buyAmounts)
		{
			this.buyingItem = item;
			this.buyAmounts = buyAmounts;
		}

		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
		{
			recipeList.add(new MerchantRecipe(this.buyingItem, buyingItem, buyingItem, this.buyAmounts.getPrice(random), 0));
		}
	}

	private static class ItemstackForEmerald implements ITradeList
	{
		public ItemStack sellingItem;
		public EntityVillager.PriceInfo priceInfo;
		public ItemstackForEmerald(ItemStack stack, EntityVillager.PriceInfo priceInfo)
		{
			this.sellingItem = stack;
			this.priceInfo = priceInfo;
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
	           recipeList.add(new MerchantRecipe(stackToPay, sellingItem));

	           // DEBUG
	           System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
	     }
	}
}
