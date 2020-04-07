package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.gui.GuiCombustionEngine;
import com.affehund.airplanes.client.gui.GuiEnergyStorageBlock;
import com.affehund.airplanes.client.gui.GuiFluidTank;
import com.affehund.airplanes.client.gui.GuiMetallurgicalOven;
import com.affehund.airplanes.client.gui.GuiSuitcase;
import com.affehund.airplanes.common.containers.ContainerCombustionEngine;
import com.affehund.airplanes.common.containers.ContainerEnergyStorageBlock;
import com.affehund.airplanes.common.containers.ContainerFluidTank;
import com.affehund.airplanes.common.containers.ContainerMetallurgicalOven;
import com.affehund.airplanes.common.containers.ContainerSuitcase;
import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;
import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;
import com.affehund.airplanes.common.tileentities.TileEntityFluidTank;
import com.affehund.airplanes.common.tileentities.TileEntityMetallurgicalOven;
import com.affehund.airplanes.common.tileentities.TileEntitySuitcase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
*@author Affehund

*MIT License
*Copyright (c) 2020 Affehund Dev Team

*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in all
*copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*SOFTWARE.
 */
public class GuiHandler implements IGuiHandler
	{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
		{
		if(ID == Reference.GUI_SUITCASE) return new ContainerSuitcase(player.inventory, new TileEntitySuitcase(player.inventory.getCurrentItem()), player);
		if(ID == Reference.GUI_COMBUSTION_ENGINE) return new ContainerCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_ENERGY_STORAGE) return new ContainerEnergyStorageBlock(player.inventory, (TileEntityEnergyStorageBlock)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_FLUID_TANK) return new ContainerFluidTank(player.inventory, (TileEntityFluidTank)world.getTileEntity(new BlockPos(x,y,z)));	
		if(ID == Reference.GUI_METALLURGICAL_OVEN) return new ContainerMetallurgicalOven(player.inventory, (TileEntityMetallurgicalOven)world.getTileEntity(new BlockPos(x,y,z)));	
		return null;
		}
		
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_SUITCASE) return new GuiSuitcase(player.inventory, new TileEntitySuitcase(player.inventory.getCurrentItem()));
		if(ID == Reference.GUI_COMBUSTION_ENGINE) return new GuiCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_ENERGY_STORAGE) return new GuiEnergyStorageBlock(player.inventory, (TileEntityEnergyStorageBlock)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_FLUID_TANK) return new GuiFluidTank(player.inventory, (TileEntityFluidTank)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_METALLURGICAL_OVEN) return new GuiMetallurgicalOven(player.inventory, (TileEntityMetallurgicalOven)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
		}
}
