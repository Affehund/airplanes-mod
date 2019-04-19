package com.affehund.airplanes.init;

import com.affehund.airplanes.fluids.FluidLiquid;

import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;


public class FluidInit extends BlockFluidClassic 
{
	public FluidInit(Fluid fluid, Material material) {
		super(fluid, material);
	}

	public static final Fluid OIL = new FluidLiquid("oil", new ResourceLocation("airplanes:blocks/oil_still"), new ResourceLocation("airplanes:blocks/oil_flow")); 
	
	public static void registerFluids()
	{
		registerFluid(OIL);
	}
	
	public static void registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}
