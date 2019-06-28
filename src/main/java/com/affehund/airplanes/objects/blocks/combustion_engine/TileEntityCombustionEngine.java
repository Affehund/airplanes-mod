package com.affehund.airplanes.objects.blocks.combustion_engine;

import com.affehund.airplanes.energy.AirplanesEnergyStorage;
import com.affehund.airplanes.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCombustionEngine extends TileEntity implements ITickable
{
	public ItemStackHandler handler = new ItemStackHandler(1);
	private AirplanesEnergyStorage storage = new AirplanesEnergyStorage(100000, 0, 200);
	//public int energy;
	public int energy = storage.getEnergyStored();
	private String customName;
	public int cookTime;
	

	@Override
	public void update() 
	{	
		this.storage.extractEnergy(200, false);
		if(!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0)))
		{
			if(energy < 100000) 
			{
				cookTime++;
				if(cookTime == 25)
				{
					energy += getFuelValue(handler.getStackInSlot(0));
					handler.getStackInSlot(0).shrink(1);
					cookTime = 0;
				}
			}
		}	
	}

	private boolean isItemFuel(ItemStack stack) 
	{
		return getFuelValue(stack) > 0;
	}

	private int getFuelValue(ItemStack stack) 
	{
		if(stack.getItem() == Items.COAL) return 1000;	
		if(stack.getItem() == ItemInit.FUEL) return 2000;
		else return 0;
	}
	
	
		 
	/*@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("Energy", getStorage().getEnergyStored());
		getStorage().writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("CookTime", this.cookTime);
		compound.setString("Name", this.getDisplayName().toString());
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		getStorage().readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.cookTime = compound.getInteger("CookTime");
		this.energy = compound.getInteger("Energy");
		this.customName = compound.getString("Name");
	}*/
	
	@Override

	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("CookTime", this.cookTime);
		compound.setInteger("Energy", this.energy);
		compound.setString("Name", getDisplayName().toString());
		this.storage.writeToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.cookTime = compound.getInteger("CookTime");
		this.energy = compound.getInteger("Energy");
		this.customName = compound.getString("Name");
		this.storage.readFromNBT(compound);
	}
	
	/*@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if (capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)) 
		{
            return true;
        }
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if (facing == null || capability != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (capability == CapabilityEnergy.ENERGY) {
                return (T) getStorage();
            	}
        	} 
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}*/
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY) return (T)this.storage;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T)this.handler;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityEnergy.ENERGY) return true;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}


	@Override
	public ITextComponent getDisplayName()
	{

		return new TextComponentTranslation("container.combustion_engine");

	}

	/*public AirplanesEnergyStorage getStorage() {
        return this.storage;
    }

    public void setStorage(AirplanesEnergyStorage storage) {
        this.storage = storage;
    }


	public int getEnergyStored() {
        return this.storage.getEnergyStored();
    }

    public int getMaxEnergyStored() {
        return getStorage().getMaxEnergyStored();
    }*/
	
	public int getEnergyStored()
	{
		return this.energy;
	}

	public int getMaxEnergyStored()
	{
		return this.storage.getMaxEnergyStored();
	}

	public int getField(int id)
	{
		switch(id)
		{
		case 0:
			return this.energy;
		case 1:
			return this.cookTime;
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
		case 1:
			this.cookTime = value;
		}
	}

	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}	
}

