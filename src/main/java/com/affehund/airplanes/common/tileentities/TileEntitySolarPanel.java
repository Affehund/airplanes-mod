package com.affehund.airplanes.common.tileentities;

import com.affehund.airplanes.common.blocks.tools.IRestorableTileEntity;
import com.affehund.airplanes.core.energy.AirplanesForgeEnergyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class TileEntitySolarPanel extends TileEntity implements ITickable, IRestorableTileEntity
{
	public int capacity = 100000;
	public int maxInput = 0;
	public int maxOutput = 40;
	public int maxGeneration = 30;

	public AirplanesForgeEnergyStorage energyStorage = new AirplanesForgeEnergyStorage(capacity, maxInput);
	public int energy = energyStorage.getEnergyStored();
	@SuppressWarnings("unused")
	private String customName;
	float sunIntensity;

	@Override
	public void update()
	{
		if (!this.world.isRemote || energyStorage.getEnergyStored() < this.capacity)
		{
			outputEnergy(maxOutput);
			if (world.canBlockSeeSky(this.pos.up()) && world.isDaytime())
			{
				if (getCalculatedEnergy() <= 0)
					return;
				else
				{
//				System.out.println(getCalculatedEnergy());
					this.energyStorage.generateEnergy(getCalculatedEnergy());
				}
			}
			markDirty();
		}
	}

	public int getCalculatedEnergy()
	{
		if (energyStorage.getEnergyStored() < this.capacity)
		{
			calcSunIntensity(pos);
			int calculatedEnergy = (int) ((float) maxGeneration * (float) sunIntensity);
			return calculatedEnergy;
		} else
		{
			return 0;
		}
	}

	public void outputEnergy(int maxOutput)
	{
		if (energyStorage.getEnergyStored() > 0)
		{
			for (EnumFacing facing : EnumFacing.VALUES)
			{
				TileEntity tileEntity = world.getTileEntity(pos.offset(facing));

				if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
				{
					IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
					if (handler != null && handler.canReceive())
					{
						int accepted = handler.receiveEnergy(maxOutput, false);
						energyStorage.consumeEnergy(accepted);

						if (energyStorage.getEnergyStored() <= 0)
						{
							break;
						}
					}
				}
			}
			markDirty();
		}
	}

	public void calcSunIntensity(BlockPos pos)
	{
		// Celestial angle == 0 at zenith.
		float celestialAngleRadians = world.getCelestialAngleRadians(1.0f);
		if (celestialAngleRadians > Math.PI)
			celestialAngleRadians = (float) (2 * Math.PI - celestialAngleRadians);

		sunIntensity = MathHelper.cos(celestialAngleRadians);
		sunIntensity = Math.max(0, sunIntensity);
		sunIntensity = Math.min(1, sunIntensity);

		if (sunIntensity > 0)
		{
			if (world.isRaining())
			{
				sunIntensity *= 0.3;
			}
			if (world.isThundering())
			{
				sunIntensity *= 0;
			}
		} else
			sunIntensity = 0;
	}

	public float getSunIntensity()
	{
		return sunIntensity;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityEnergy.ENERGY)
		{
			return CapabilityEnergy.ENERGY.cast(energyStorage);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityEnergy.ENERGY)
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		compound.setString("Name", getDisplayName().toString());
		compound.setInteger("Energy", energyStorage.getEnergyStored());
		this.writeRestorableToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.customName = compound.getString("Name");
		energyStorage.setEnergy(compound.getInteger("Energy"));
		this.readRestorableFromNBT(compound);
	}

	@Override
	public void writeRestorableToNBT(NBTTagCompound compound)
	{
		compound.setInteger("energy", energyStorage.getEnergyStored());
	}

	@Override
	public void readRestorableFromNBT(NBTTagCompound compound)
	{
		energyStorage.setEnergy(compound.getInteger("energy"));
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentTranslation("container.combustion_engine");
	}

	public int getEnergyStored()
	{
		return this.energyStorage.getEnergyStored();
	}

	public int getMaxEnergyStored()
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	public int getField(int id)
	{
		switch (id)
		{
		case 0:
			return this.energyStorage.getEnergyStored();
		default:
			return 0;
		}
	}

	public void setField(int id, int value)
	{
		switch (id)
		{
		case 0:
			this.energy = value;
			break;
		default:
			break;
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
		this.handleUpdateTag(pkt.getNbtCompound());
	}

	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}
}
