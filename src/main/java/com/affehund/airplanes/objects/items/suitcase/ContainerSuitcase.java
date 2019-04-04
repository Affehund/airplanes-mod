package com.affehund.airplanes.objects.items.suitcase;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class ContainerSuitcase extends ContainerChest {

	public ContainerSuitcase(IInventory playerInventory, IInventory chestInventory, EntityPlayer player) {
		super(playerInventory, chestInventory, player);
		}
}