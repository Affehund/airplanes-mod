package com.affehund.airplanes.client.gui.pages;

import com.affehund.airplanes.client.gui.GuiBookOfAirplanes;
import net.minecraft.world.World;

public abstract class Page {
	public abstract void draw(GuiBookOfAirplanes book, int x, int y);

	public boolean isInfoAbout(World world, int x, int y, int z) {
		return false;
	}
}
