package com.affehund.airplanes.objects.blocks.tank;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public interface IGuiTile {
    Container createContainer(EntityPlayer player);
    GuiContainer createGui(EntityPlayer player);
}
