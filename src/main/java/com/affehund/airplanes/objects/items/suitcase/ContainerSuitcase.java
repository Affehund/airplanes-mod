package com.affehund.airplanes.objects.items.suitcase;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerSuitcase extends ContainerChest {
	
	public boolean updateNotification;


	public ContainerSuitcase(IInventory playerInventory, IInventory chestInventory, EntityPlayer player) {
		super(playerInventory, chestInventory, player);
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size()) {
			tmpSlot = (Slot) inventorySlots.get(slotId);

		} else {
			tmpSlot = null;
		}
		
		if (tmpSlot != null) {
			if (tmpSlot.isHere(player.inventory, player.inventory.currentItem)) {
				return tmpSlot.getStack();
			}
		}
		
		if (clickTypeIn == ClickType.SWAP) {
			ItemStack stack = player.inventory.getStackInSlot(dragType);
			if (stack == player.inventory.getCurrentItem()) {
				return ItemStack.EMPTY;
			}
		}

		this.updateNotification = true;
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}