package com.affehund.airplanes.core.handlers;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.client.gui.GuiBookOfAirplanes;
import com.affehund.airplanes.client.gui.GuiCombustionEngine;
import com.affehund.airplanes.client.gui.GuiEnergyStorageBlock;
import com.affehund.airplanes.client.gui.GuiSuitcase;
import com.affehund.airplanes.common.containers.ContainerCombustionEngine;
import com.affehund.airplanes.common.containers.ContainerEnergyStorageBlock;
import com.affehund.airplanes.common.containers.ContainerSuitcase;
import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;
import com.affehund.airplanes.common.tileentities.TileEntityEnergyStorageBlock;
import com.affehund.airplanes.common.tileentities.TileEntitySuitcase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
	{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
		{
		if(ID == Reference.GUI_SUITCASE) return new ContainerSuitcase(player.inventory, new TileEntitySuitcase(player.inventory.getCurrentItem()), player);
		if(ID == Reference.GUI_COMBUSTION_ENGINE) return new ContainerCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_ENERGY_STORAGE) return new ContainerEnergyStorageBlock(player.inventory, (TileEntityEnergyStorageBlock)world.getTileEntity(new BlockPos(x,y,z)));
			return null;
		}
		
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_BOOK) return new GuiBookOfAirplanes(world, x, y, z, player);
		if(ID == Reference.GUI_SUITCASE) return new GuiSuitcase(player.inventory, new TileEntitySuitcase(player.inventory.getCurrentItem()));
		if(ID == Reference.GUI_COMBUSTION_ENGINE) return new GuiCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_ENERGY_STORAGE) return new GuiEnergyStorageBlock(player.inventory, (TileEntityEnergyStorageBlock)world.getTileEntity(new BlockPos(x,y,z)));
			return null;
		}
}
