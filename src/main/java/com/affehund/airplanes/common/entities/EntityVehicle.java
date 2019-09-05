package com.affehund.airplanes.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityVehicle extends Entity implements IEntityAdditionalSpawnData {

	public EntityVehicle(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	public int getDestroyedStage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
