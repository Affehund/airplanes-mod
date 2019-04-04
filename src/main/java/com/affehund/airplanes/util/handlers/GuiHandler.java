package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.objects.blocks.combustion_engine.ContainerCombustionEngine;
import com.affehund.airplanes.objects.blocks.combustion_engine.GuiCombustionEngine;
import com.affehund.airplanes.objects.blocks.combustion_engine.TileEntityCombustionEngine;
import com.affehund.airplanes.objects.items.suitcase.ContainerSuitcase;
import com.affehund.airplanes.objects.items.suitcase.GuiSuitcase;
import com.affehund.airplanes.objects.items.suitcase.InventorySuitcase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
	{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
		{
		if(ID == AirplanesConstants.GUI_SUITCASE) return new ContainerSuitcase(player.inventory, new InventorySuitcase(player.inventory.getCurrentItem()), player);
		if(ID == AirplanesConstants.GUI_COMBUSTION_ENGINE) return new ContainerCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		
			return null;
		}
		
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	
		if(ID == AirplanesConstants.GUI_SUITCASE) return new GuiSuitcase(player.inventory, new InventorySuitcase(player.inventory.getCurrentItem()));
		if(ID == AirplanesConstants.GUI_COMBUSTION_ENGINE) return new GuiCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		
			return null;
		}
}
