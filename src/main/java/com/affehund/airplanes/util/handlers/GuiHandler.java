package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.AirplanesConstants;
import com.affehund.airplanes.objects.blocks.containers.ContainerCombustionEngine;
import com.affehund.airplanes.objects.blocks.guis.GuiCombustionEngine;
import com.affehund.airplanes.tileentities.TileEntityCombustionEngine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
	{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
		{
		
		if(ID == AirplanesConstants.GUI_COMBUSTION_ENGINE) return new ContainerCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
			return null;
		}
		
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
		{
		if(ID == AirplanesConstants.GUI_COMBUSTION_ENGINE) return new GuiCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
			return null;
		}
}
