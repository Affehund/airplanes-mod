package com.affehund.airplanes.objects.blocks.energy_storage;

import com.affehund.airplanes.energy.AirplanesEnergyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;


public class TileEntityEnergyStorageBlock extends TileEntity implements ITickable
{
	private AirplanesEnergyStorage storage = new AirplanesEnergyStorage(500000, 200, 0);
	//public int energy;
	public int energy = storage.getEnergyStored();
	private String customName;

	/*@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("Energy", getStorage().getEnergyStored());
		getStorage().writeToNBT(compound);
		compound.setString("Name", this.getDisplayName().toString());
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		getStorage().readFromNBT(compound);
		this.energy = compound.getInteger("Energy");
		this.customName = compound.getString("Name");
	}*/
	


	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("Energy", this.energy);
		compound.setString("Name", getDisplayName().toString());
		this.storage.writeToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.energy = compound.getInteger("Energy");
		this.customName = compound.getString("Name");
		this.storage.readFromNBT(compound);
	}

	/*public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)) {
            return true;
        }
        return false;
    }

    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing == null || capability != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (capability == CapabilityEnergy.ENERGY) {
                return (T) getStorage();
            	}
        	} 
        return super.getCapability(capability, facing);
        }*/
	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY) return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY) return (T)this.storage;
		return super.getCapability(capability, facing);
	}

	@Override
	public void update() 
	{		
		if(world.isBlockPowered(pos)) energy += 100;
	}
	
	public int getEnergyStored() 
	{
		return energy;
	}

	public int getMaxEnergyStored() 
	{
		return this.storage.getMaxEnergyStored();
	}
	
	/*public AirplanesEnergyStorage getStorage() 
	{
        return this.storage;
    }

    public void setStorage(AirplanesEnergyStorage storage) 
    {
        this.storage = storage;
    }


	public int getEnergyStored() 
	{
        return this.storage.getEnergyStored();
    }

    public int getMaxEnergyStored() 
    {
        return getStorage().getMaxEnergyStored();
    }*/
    
	public int getField(int id)
	{
		switch(id)
		{
		case 0:
			return this.energy;
		default:
			return 0;
		}
	}

	public void setField(int id, int value)
	{
		switch(id)
		{
		case 0:
			this.energy = value;
		}
	}
   
    
    public boolean isUsableByPlayer(EntityPlayer player)
	{
		return this.world.getTileEntity(pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64D;
	}
}