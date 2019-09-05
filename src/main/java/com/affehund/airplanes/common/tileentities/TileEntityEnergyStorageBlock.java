package com.affehund.airplanes.common.tileentities;

import com.affehund.airplanes.core.energy.AirplanesForgeEnergyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;


public class TileEntityEnergyStorageBlock extends TileEntity implements ITickable
{
	public int capacity = 1000000;
	public int maxInput = 40;
	public int maxOutput = 40;
	public AirplanesForgeEnergyStorage energyStorage = new AirplanesForgeEnergyStorage(capacity, maxInput);
	public int energy = energyStorage.getEnergyStored();
	private String customName;

	@Override
	public void update()
	{
		if(!world.isRemote)
		{
			outputEnergy();
			inputEnergy();
		}
	}

	public void inputEnergy()
	{
		
	}

	public void outputEnergy()
	{
		if(energyStorage.getEnergyStored() > 0)
		{
            for(EnumFacing facing : EnumFacing.VALUES)
            {
                TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
                if(tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
                {
                    IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());

                    if(handler != null && handler.canReceive())
                    {
                        int accepted = handler.receiveEnergy(energyStorage.getEnergyStored(), false);
                        energyStorage.consumeEnergy(accepted);
                        if(energyStorage.getEnergyStored() <= 0)
                        {
                            break;
                        }
                    }
                }
            }
            markDirty();
		}
	}


	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CapabilityEnergy.ENERGY)
		{
			return CapabilityEnergy.ENERGY.cast(energyStorage);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(capability == CapabilityEnergy.ENERGY)
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("GUIEnergy", this.energy);
		compound.setString("Name", getDisplayName().toString());
		compound.setInteger("Energy", energyStorage.getEnergyStored());
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.energy = compound.getInteger("GUIEnergy");
		this.customName = compound.getString("Name");
		energyStorage.setEnergy(compound.getInteger("Energy"));
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentTranslation("container.energy_storage_block");
	}

	public int getEnergyStored()
	{
		return this.energy;
	}

	public int getMaxEnergyStored()
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	public void setField(int id, int value)
	{
		switch(id)
		{
		case 0:
			this.energy = value;
		}
	}
	
	public int getField(int id)
	{
		switch(id)
		{
		case 0:
			this.energyStorage.getEnergyStored();
		}
		return id;
	}

	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq(
				(double)this.pos.getX() + 0.5D, 
				(double)this.pos.getY() + 0.5D, 
				(double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
}

