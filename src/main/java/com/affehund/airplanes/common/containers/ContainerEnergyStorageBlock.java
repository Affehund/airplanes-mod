package com.affehund.airplanes.common.containers;

import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerEnergyStorageBlock extends Container
{
	private final TileEntityEnergyStorageBlock tileentity;
	private int energy;
	
	public ContainerEnergyStorageBlock(InventoryPlayer player, TileEntityEnergyStorageBlock tileentity) 
	{
		this.tileentity = tileentity;

		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}

	@Override
	public void updateProgressBar(int id, int data) 
	{
		this.tileentity.setField(id, data);
	}

	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = this.listeners.get(i);
			if(this.energy != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
		}
		this.energy = this.tileentity.getField(0);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if(index >= 0 && index < 27)
			{
				if(!this.mergeItemStack(stack1, 27, 36, false)) return ItemStack.EMPTY;
			}
			else if(index >= 27 && index < 36)
			{
				if(!this.mergeItemStack(stack1, 0, 27, false)) return ItemStack.EMPTY;
			}
			else if(!this.mergeItemStack(stack1, 0, 36, false))
			{
				return ItemStack.EMPTY;
			}
			
			if(stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
			else slot.onSlotChanged();

			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}	
}