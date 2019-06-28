package com.affehund.airplanes.util.handlers;

import com.affehund.airplanes.Reference;
import com.affehund.airplanes.objects.blocks.combustion_engine.ContainerCombustionEngine;
import com.affehund.airplanes.objects.blocks.combustion_engine.GuiCombustionEngine;
import com.affehund.airplanes.objects.blocks.combustion_engine.TileEntityCombustionEngine;
import com.affehund.airplanes.objects.blocks.energy_storage.ContainerEnergyStorageBlock;
import com.affehund.airplanes.objects.blocks.energy_storage.GuiEnergyStorageBlock;
import com.affehund.airplanes.objects.blocks.energy_storage.TileEntityEnergyStorageBlock;
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
		if(ID == Reference.GUI_SUITCASE) return new ContainerSuitcase(player.inventory, new InventorySuitcase(player.inventory.getCurrentItem()), player);
		if(ID == Reference.GUI_COMBUSTION_ENGINE) return new ContainerCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_ENERGY_STORAGE) return new ContainerEnergyStorageBlock(player.inventory, (TileEntityEnergyStorageBlock)world.getTileEntity(new BlockPos(x,y,z)));
		
			return null;
		}
		
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SUITCASE) return new GuiSuitcase(player.inventory, new InventorySuitcase(player.inventory.getCurrentItem()));
		if(ID == Reference.GUI_COMBUSTION_ENGINE) return new GuiCombustionEngine(player.inventory, (TileEntityCombustionEngine)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_ENERGY_STORAGE) return new GuiEnergyStorageBlock(player.inventory, (TileEntityEnergyStorageBlock)world.getTileEntity(new BlockPos(x,y,z)));
		
			return null;
		}
}
