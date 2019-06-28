package com.affehund.airplanes.objects.blocks.solar_panel;

import com.affehund.airplanes.energy.AirplanesEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntitySolarPanel extends TileEntity implements ITickable 
{
	
	private AirplanesEnergyStorage storage = new AirplanesEnergyStorage(250000, 0, 10);
	public int energy;
	private String customName;

	@Override
	public void update() 
	{	
		this.storage.extractEnergy(200, false);
		if(this.hasWorld() && !this.world.isRemote && world.canBlockSeeSky(this.pos.up()) && world.isDaytime()) 
				{
					this.storage.receiveEnergy(10, false);
				}
	}
	
			
	@Override
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
	}


    @Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY)
			return (T) getStorage();
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	public AirplanesEnergyStorage getStorage() 
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
    }
}
