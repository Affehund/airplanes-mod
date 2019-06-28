package com.affehund.airplanes.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class AirplanesEnergyStorage extends EnergyStorage 
{
    public AirplanesEnergyStorage(int capacity) 
    {
        super(capacity, capacity, capacity, 0);
    }
    
    public AirplanesEnergyStorage(int capacity, int maxTransfer) 
    {
        super(capacity, maxTransfer, maxTransfer, 0);
    }
    
    public AirplanesEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
    	super(capacity, maxReceive, maxExtract, 0);
    }

    public AirplanesEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) 
    {
        super(capacity, maxReceive, maxExtract, energy);
    }

    
    public int receiveEnergy(int maxReceive, boolean simulate) 
    {
        return super.receiveEnergy(maxReceive, simulate);
    }

    public int extractEnergy(int maxExtract, boolean simulate) 
    {
        return super.extractEnergy(maxExtract, simulate);
    }

    
    /*public void setEnergyStored(int value) 
    {
        this.energy = value;
    }

    public int getMaxEnergyStored() 
    {
        return super.getMaxEnergyStored();
    }
    

    public boolean canExtract() 
    {
        return super.canExtract();
    }

    public boolean canReceive() 
    {
        return super.canReceive();
    }*/
    
    @Override
    public int getEnergyStored() 
    {
    	return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored()
    {
    	return super.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract()
    {
    	return super.canExtract();
    }

    @Override
    public boolean canReceive() 
    {
    	return super.canReceive();
    }
    

    public void readFromNBT(NBTTagCompound compound) 
    {
        this.energy = compound.getInteger("Energy");
        this.capacity = compound.getInteger("Capacity");
        this.maxReceive = compound.getInteger("MaxReceive");
        this.maxExtract = compound.getInteger("MaxExtract");
    }

    public void writeToNBT(NBTTagCompound compound) 
    {
    	compound.setInteger("Energy", this.energy);
    	compound.setInteger("Capacity", this.capacity);
    	compound.setInteger("MaxReceive", this.maxReceive);
    	compound.setInteger("MaxExtract", this.maxExtract);
    }
}
