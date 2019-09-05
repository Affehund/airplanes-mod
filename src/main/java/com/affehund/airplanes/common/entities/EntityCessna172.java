package com.affehund.airplanes.common.entities;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCessna172 extends EntityPlane
{
	public float prevWheelRotation;
	
	public EntityCessna172(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onInventoryChanged(IInventory invBasic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

}
