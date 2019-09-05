package com.affehund.airplanes.common.entities;

import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.world.World;

public abstract class EntityPoweredVehicle extends EntityVehicle implements IInventoryChangedListener {

	public EntityPoweredVehicle(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

}
