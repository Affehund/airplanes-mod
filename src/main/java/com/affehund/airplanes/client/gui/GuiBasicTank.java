package com.affehund.airplanes.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.core.utils.RenderUtils;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

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
public class GuiBasicTank
{
	private int height;
	private int width;
	private int startX;
	private int startY;
	private FluidTank tank;

	public GuiBasicTank(FluidTank tank, int x, int y, int height, int width)
	{
		this.startX = x;
		this.startY = y;
		this.height = height;
		this.width = width;
		this.tank = tank;
	}

	public List<String> getTankTooltip()
	{
		FluidStack fluidStack = tank.getFluid();
		String fluidName = (fluidStack != null) ? fluidStack.getLocalizedName()
				: I18n.format("airplanes.gui.none.name");
		String fluidAmount = (fluidStack != null) ? fluidStack.amount + "/" + tank.getCapacity() + " mB" : null;
		String fluidTemp = (fluidStack != null)
				? "" + (fluidStack.getFluid().getTemperature(fluidStack) - Reference.CALVINTOCELSIUS) + " °C"
				: null;

		ArrayList<String> tankTips = new ArrayList<String>();
		tankTips.add(fluidName);
		if (fluidTemp != null)
			tankTips.add(fluidTemp);
		if (fluidAmount != null)
			tankTips.add(fluidAmount);
		return tankTips;
	}

	public void drawGuiFluidBar(GuiFluidTank gui, int fluidHeight)
	{
		RenderUtils.renderGuiTank(tank, this.startX, this.startY, this.height, this.width);
	}

	public boolean inTank(GuiFluidTank gui, int mouseX, int mouseY)
	{
		mouseX -= gui.getGuiLeft();
		mouseY -= gui.getGuiTop();
		return startX <= mouseX && mouseX <= startX + width && startY <= mouseY && mouseY <= startY + height;
	}
}
