package com.affehund.airplanes.common.blocks.machines;

import java.util.List;

import javax.annotation.Nullable;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.Reference;
import com.affehund.airplanes.common.blocks.BlockBase;
import com.affehund.airplanes.common.tileentities.TileEntityCombustionEngine;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CombustionEngineBlock extends BlockBase
{
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public CombustionEngineBlock(String name) {
		super(name, Material.ROCK);
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(AirplanesMod.AIRPLANESTAB1);
		setHardness(2.5F);
		setResistance(150F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(AirplanesMod.instance, Reference.GUI_COMBUSTION_ENGINE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	 @Override
	    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flags) {
	        NBTTagCompound tagCompound = stack.getTagCompound();
	        if (tagCompound != null) {
	            int energy = tagCompound.getInteger("energy");
	            addInformationLocalized(tooltip, "message.airplanesmod.combustion_engine", energy);
	        }
	    }

	private void addInformationLocalized(List<String> tooltip, String string, int energy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return new TileEntityCombustionEngine();
	}
	
	
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityCombustionEngine te = (TileEntityCombustionEngine) world.getTileEntity(pos);

		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for(int slot = 0; slot < handler.getSlots(); slot++) {
			ItemStack stack = handler.getStackInSlot(slot);
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
		super.breakBlock(world, pos, state);
	}
	
	@Nullable
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
}




