package com.affehund.airplanes.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityPlane extends Entity implements IEntityAdditionalSpawnData 
{
    public float prevBodyRotationX;
    public float prevBodyRotationY;
    public float prevBodyRotationZ;

    public float bodyRotationX;
    public float bodyRotationY;
    public float bodyRotationZ;

	public EntityPlane(World worldIn) 
	{
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
}
