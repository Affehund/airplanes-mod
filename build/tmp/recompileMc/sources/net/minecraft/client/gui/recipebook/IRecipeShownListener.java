package net.minecraft.client.gui.recipebook;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IRecipeShownListener
{
    void recipesUpdated();
}