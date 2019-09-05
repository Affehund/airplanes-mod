package com.affehund.airplanes.common.entities;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBoat extends EntityPoweredVehicle {

	public EntityBoat(World worldIn) {
		super(worldIn);
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
