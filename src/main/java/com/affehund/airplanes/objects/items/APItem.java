package com.affehund.airplanes.objects.items;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public interface APItem {
    void onContainerTick(Container c, Slot slot, ItemStack stack);
}
