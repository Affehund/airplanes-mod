package com.affehund.airplanes.proxy;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;
import com.affehund.airplanes.client.gui.book.pages.BookLoader;
import com.affehund.airplanes.client.render.RenderBoeing737;
import com.affehund.airplanes.client.render.RenderCessna172;
import com.affehund.airplanes.client.tesr.TESRFluidTank;
import com.affehund.airplanes.common.entities.EntityBoeing737;
import com.affehund.airplanes.common.entities.EntityCessna172;
import com.affehund.airplanes.common.tileentities.TileEntityFluidTank;
import com.affehund.airplanes.core.init.BlockInit;
import com.affehund.airplanes.core.init.ItemInit;
import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for (Item item : ItemInit.ITEMS)
		{
			AirplanesMod.proxy.registerItemRenderer(item, 0, "inventory");
		}
		
		for (Block block : BlockInit.BLOCKS)
		{
			AirplanesMod.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
		AirplanesMod.log.info("Registered item renderer for blocks and items");
		
		ClientProxy.registerCustomMeshesAndStates();
		ClientProxy.registerEntityRenders();
		ClientProxy.registerTESR();
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id)
	{

		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	public static void registerCustomMeshesAndStates()
	{
		AirplanesMod.log.info("Registered custom meshes and states (fluids)");
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.OIL), new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return new ModelResourceLocation("airplanes:oil", "fluid");
			}
		});

		ModelLoader.setCustomStateMapper(BlockInit.OIL, new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return new ModelResourceLocation("airplanes:oil", "fluid");
			}
		});
	}

	public static void registerEntityRenders()
	{
		AirplanesMod.log.info("Registered entity renders");
		RenderingRegistry.registerEntityRenderingHandler(EntityBoeing737.class, RenderBoeing737::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityCessna172.class, RenderCessna172::new);
//		{
//			@Override
//			public Render<? super EntityCessna172> createRenderFor(RenderManager manager)
//			{
//				return new RenderCessna172(manager);
//			}
//		});
	}
	
	public static void registerTESR()
	{
		AirplanesMod.log.info("Registered tile entity special renderer");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidTank.class, new TESRFluidTank());
	}
	
	
	

	public static void openBookGui()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiBookOfAirplanes());
	}

	@Override
	public void loadGuide()
	{
		BookLoader guideLoader = new BookLoader();
		guideLoader.load();
	}
	
	@Override
	public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
	{
		return Minecraft.getMinecraft().addScheduledTask(runnableToSchedule);
	}

	@Override
	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().player;
	}
}