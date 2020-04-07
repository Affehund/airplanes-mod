package com.affehund.airplanes.common.tileentities;

import javax.annotation.Nullable;

import com.affehund.airplanes.common.blocks.machines.BlockMetallurgicalOven;
import com.affehund.airplanes.common.blocks.tools.IRestorableTileEntity;
import com.affehund.airplanes.common.containers.ContainerMetallurgicalOven;
import com.affehund.airplanes.common.recipes.metallurgical_oven.IMetallurgicalOvenRecipe;
import com.affehund.airplanes.common.recipes.metallurgical_oven.MetallurgicalOvenRegistry;
import com.affehund.airplanes.core.config.ConfigAirplanes;
import com.affehund.airplanes.core.energy.AirplanesForgeEnergyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

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

public class TileEntityMetallurgicalOven extends TileEntityLockable
		implements ITickable, ISidedInventory, IRestorableTileEntity
{
	public int capacity = ConfigAirplanes.Global.Energy.MAX_CAPACITY_METALLURGICAL_OVEN;
	public int maxInput = ConfigAirplanes.Global.Energy.MAX_INPUT_METALLURGICAL_OVEN;
	public int energyPerProgress = ConfigAirplanes.Global.Energy.ENERGY_PER_PROGRESS_METALLURGICAL_OVEN;
	public AirplanesForgeEnergyStorage energyStorage = new AirplanesForgeEnergyStorage(capacity, maxInput);
	public int energy = energyStorage.getEnergyStored();

	private static final int[] SLOTS_TOP = new int[]
	{ 0, 1, 2, 3 };
	private static final int[] SLOTS_BOTTOM = new int[]
	{ 4 };

	public int cookTime = 0;
	public int totalCookTime;
	private String customName;

	private NonNullList<ItemStack> inventory;
	private ItemStack outputInventory;
	private boolean updatingRecipe = true;

	public IMetallurgicalOvenRecipe currentRecipe;

	public TileEntityMetallurgicalOven()
	{
		this.inventory = NonNullList.withSize(5, ItemStack.EMPTY);
		this.outputInventory = ItemStack.EMPTY;
	}

	protected void onContentsChanged(int slot)
	{
		this.markDirty();
	}

	@Override
	public int getSizeInventory()
	{
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty()
	{
		for (ItemStack stack : this.inventory)
		{
			if (!stack.isEmpty())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		updatingRecipe = true;
		if (index == 4)
		{
			return this.outputInventory;
		} else if (index < 4)
		{
			return this.inventory.get(index);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int index1, int index2)
	{
		ItemStack metInventory = getStackInSlot(index1);
		if (metInventory.isEmpty())
		{
			return ItemStack.EMPTY;
		}
		ItemStack ret = ItemStack.EMPTY;
		if (metInventory.getCount() < index2)
		{
			ret = metInventory;
			inventory = null;
		} else
		{
			ret = metInventory.splitStack(index2);
			if (metInventory.getCount() <= 0)
			{
				if (index2 == 4)
				{
					outputInventory = ItemStack.EMPTY;
				} else
				{
					inventory.set(index1, ItemStack.EMPTY);
				}
			}
		}
		return ret;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return getStackInSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if (index == 4)
		{
			outputInventory = stack;
		} else
		{
			inventory.set(index, stack);
		}
		updatingRecipe = true;
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "container.metallurgical_oven";
	}

	@Override
	public boolean hasCustomName()
	{
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomInventoryName(String customName)
	{
		this.customName = customName;
	}

	public static void registerFixesFurnace(DataFixer fixer)
	{
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityMetallurgicalOven.class, new String[]
				{ "Items" }));
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		for (int i = 0; i < 4; i++)
		{
			NBTTagCompound tc = new NBTTagCompound();
			inventory.get(i).writeToNBT(tc);
			compound.setTag("inventory" + i, tc);
		}
		if (outputInventory != null)
		{
			NBTTagCompound outputCompound = new NBTTagCompound();
			outputInventory.writeToNBT(outputCompound);
			compound.setTag("outputInventory", outputCompound);
		}

		compound.setInteger("CookTime", (short) this.cookTime);
		compound.setInteger("CookTimeTotal", (short) this.totalCookTime);
		if (this.hasCustomName())
		{
			compound.setString("CustomName", this.customName);
		}
		compound.setInteger("GUIEnergy", this.energy);
		compound.setInteger("Energy", energyStorage.getEnergyStored());
		if (this.energyStorage.getEnergyStored() > 0)
		{
			this.writeRestorableToNBT(compound);
		}
		return compound;
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		for (int i = 0; i < 4; i++)
		{
			NBTTagCompound tc = compound.getCompoundTag("inventory" + i);
			inventory.set(i, new ItemStack(tc));
		}
		outputInventory = new ItemStack(compound.getCompoundTag("outputInventory"));

		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		if (compound.hasKey("CustomName", 8))
		{
			this.customName = compound.getString("CustomName");
		}
		this.energy = compound.getInteger("GUIEnergy");
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

	public int getEnergyStored()
	{
		return this.energy;
	}

	public int getMaxEnergyStored()
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	public int getCookTime()
	{
		return this.cookTime;
	}

	public int getTotalCookTime()
	{
		return this.totalCookTime;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	public boolean isBurning()
	{
		return this.cookTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory)
	{
		return inventory.getField(0) > 0;
	}

	public void update()
	{
		boolean flag = this.isBurning();
		boolean flag1 = false;
		if (!this.world.isRemote)
		{
			if (updatingRecipe)
			{
				currentRecipe = MetallurgicalOvenRegistry.getInstance().getMatchingRecipe(inventory, outputInventory);
				updatingRecipe = false;
			}
			if (currentRecipe != null && this.energyStorage.getEnergyStored() >= this.energyPerProgress)
			{
				++this.cookTime;
				this.totalCookTime = this.getCookTime(this.inventory.get(0));
				if (this.cookTime == this.totalCookTime)
				{
					this.cookTime = 0;
					{
						flag1 = true;
						if (!outputInventory.isEmpty())
						{
							outputInventory.setCount(
									outputInventory.getCount() + currentRecipe.getCraftingResult(inventory).getCount());
						} else
						{
							outputInventory = currentRecipe.getCraftingResult(inventory).copy();
						}
						currentRecipe.useItems(inventory);
						updatingRecipe = true;
						this.energyStorage.consumeEnergy(energyPerProgress);
						this.markDirty();
					}
				}
			} else
			{
				this.cookTime = 0;
				this.markDirty();
			}
		}
		if (flag != this.isBurning())
		{
			flag1 = true;
			BlockMetallurgicalOven.setState(this.isBurning(), this.world, this.pos);
			this.markDirty();
		} else if (!this.isBurning() && this.cookTime > 0)
		{
			this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
		}
		if (flag1)
		{
			this.markDirty();
		}
	}

	public int getCookTime(ItemStack stack)
	{
		return 200;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		if (this.world.getTileEntity(this.pos) != this)
		{
			return false;
		} else
		{
			return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	public void openInventory(EntityPlayer player)
	{
		this.markDirty();
	}

	public void closeInventory(EntityPlayer player)
	{
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (index == 4)
		{
			return false;
		} else
			return true;
	}

	public int[] getSlotsForFace(EnumFacing side)
	{
		if (side == EnumFacing.DOWN)
		{
			return SLOTS_BOTTOM;
		} else
		{
			return SLOTS_TOP;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		return index == 4;
	}

	@Override
	public String getGuiID()
	{
		return "airplanes:metallurgical_oven";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerMetallurgicalOven(playerInventory, this);
	}

	public int getField(int id)
	{
		switch (id)
		{
		case 0:
			return this.cookTime;
		case 1:
			return this.totalCookTime;
		case 2:
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
			this.cookTime = value;
			break;
		case 1:
			this.totalCookTime = value;
			break;
		case 2:
			this.energy = value;
			break;
		default:
			break;
		}
	}

	@Override
	public int getFieldCount()
	{
		return 3;
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

	@Override
	public void clear()
	{
		this.inventory.clear();
	}

	IItemHandler handlerInput = new SidedInvWrapper(this, EnumFacing.UP);
	IItemHandler handlerOutput = new SidedInvWrapper(this, EnumFacing.DOWN);
	IItemHandler handlerSides = new SidedInvWrapper(this, EnumFacing.NORTH);

	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		if (capability == CapabilityEnergy.ENERGY)
		{
			return CapabilityEnergy.ENERGY.cast(energyStorage);
		}
		if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			if (facing == EnumFacing.UP)
				return (T) handlerInput;
			else if (facing == EnumFacing.DOWN)
				return (T) handlerOutput;
			else
				return (T) handlerSides;
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
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}
}
