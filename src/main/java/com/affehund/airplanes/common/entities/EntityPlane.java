package com.affehund.airplanes.common.entities;

import net.minecraft.world.World;

public abstract class EntityPlane extends EntityPoweredVehicle {


    public float prevBodyRotationX;
    public float prevBodyRotationY;
    public float prevBodyRotationZ;

    public float bodyRotationX;
    public float bodyRotationY;
    public float bodyRotationZ;

	public EntityPlane(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

}
