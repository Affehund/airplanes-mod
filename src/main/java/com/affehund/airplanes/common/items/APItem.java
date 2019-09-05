package com.affehund.airplanes.common.items;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public interface APItem {
    void onContainerTick(Container c, Slot slot, ItemStack stack);
}
