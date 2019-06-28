package com.affehund.airplanes.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class AirplaneCessna172 extends EntityLiving
{

	public AirplaneCessna172(World worldIn) 
	{
		super(worldIn);
		this.setSize(4.0F, 2.6F);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
	}
	
	@Override
	public float getEyeHeight() {
		return 2.6F;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entityIn) 
	{
		  return this.getEntityBoundingBox().grow(20);
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() 
	{
		return this.getEntityBoundingBox();
	}

	@Override
	public boolean isAIDisabled() 
	{
		return true;
	}
	
	@Override
	public void setNoAI(boolean disable) {
		super.setNoAI(disable);
	}
}
