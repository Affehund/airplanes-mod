package com.affehund.airplanes.common.entities;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBoeing737 extends EntityPlane 
{
	private float momentum;
	private float deltaRotation;
	private boolean leftInputDown;
	private boolean rightInputDown;
	private boolean forwardInputDown;
	private boolean backInputDown;
	private boolean jumpInputDown;

	public EntityBoeing737(World world)
	{
		super(world);
	}

	public EntityBoeing737(World world, double x, double y, double z)
	{
		super(world);
		setPosition(x, y, z);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}

	@Override
	protected void entityInit()
	{
		
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return getEntityBoundingBox().grow(2D);
	}
	
//	@Override
//	public void onCollideWithPlayer(EntityPlayer player)
//	{
//		super.onCollideWithPlayer(player);
//		if(getControllingPassenger() == null) player.startRiding(this);
//	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking())
		{
			return false;
		}
		else
		{
			if(!world.isRemote && !isBeingRidden())
			{
				player.startRiding(this);
			}

			return true;
		}
	}

	@Override
	public double getMountedYOffset()
	{
		return 2.75D;
	}

	@Override
	public boolean canPassengerSteer()
	{
		return true;
	}

	public boolean canBeSteered()
	{
		return getControllingPassenger() instanceof EntityLivingBase;
	}

	@Override
	public Entity getControllingPassenger()
	{
		return getPassengers().isEmpty() ? null : getPassengers().get(0);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(getControllingPassenger() != null)
		{
			updateMotion();
			if(world.isRemote) controlAirplane();
			move(MoverType.SELF, motionX, motionY, motionZ);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void applyOrientationToEntity(Entity entityToUpdate)
	{
		applyYawToEntity(entityToUpdate);
	}

	@Override
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotIncr, boolean teleport)
	{
		super.setPositionAndRotationDirect(x, y, z, yaw, pitch, posRotIncr, teleport);
	}

	@SideOnly(Side.CLIENT)
	public void updateInputs(boolean forwardDown, boolean backDown, boolean leftDown, boolean rightDown, boolean jumpDown)
	{
		leftInputDown = leftDown;
		rightInputDown = rightDown;
		forwardInputDown = forwardDown;
		backInputDown = backDown;
		jumpInputDown = jumpDown;
	}

	protected void applyYawToEntity(Entity entityToUpdate)
	{
		entityToUpdate.setRenderYawOffset(rotationYaw);
		float yawWrap = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - rotationYaw);
		float yawClamp = MathHelper.clamp(yawWrap, -90.0F, 90.0F);
		entityToUpdate.prevRotationYaw += yawClamp - yawWrap;
		entityToUpdate.rotationYaw += yawClamp - yawWrap;
		entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
	}
	
	private void updateMotion()
	{
		momentum = 0.75F;

		motionX *= (double)momentum;
		motionZ *= (double)momentum;
		deltaRotation *= momentum;
	}
	
	private void controlAirplane()
	{
		if(isBeingRidden())
		{
			float forwardMomentum = 0.0F;
			float upwardMomentum = 0.0F;

			if(leftInputDown)
			{
				--deltaRotation;
			}

			if(rightInputDown)
			{
				++deltaRotation;
			}

			if(rightInputDown != leftInputDown && !forwardInputDown && !backInputDown)
			{
				forwardMomentum += 0.005F;
			}

			rotationYaw += deltaRotation;

			if(forwardInputDown)
			{
				forwardMomentum += 0.04F;

				if(jumpInputDown)
				{
					upwardMomentum += 0.04F;
				}
			}

			else if(!(jumpInputDown && forwardInputDown))
			{
				upwardMomentum -= 0.04F;
			}

			if(backInputDown)
			{
				forwardMomentum -= 0.005F;
			}

			motionX += (double)(MathHelper.sin(-rotationYaw * 0.017453292F) * forwardMomentum);
			motionY += (double)(MathHelper.sin(-rotationPitch * 0.017453292F) * upwardMomentum);
			motionZ += (double)(MathHelper.cos(rotationYaw * 0.017453292F) * forwardMomentum);
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) 
	{
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) 
	{


	}
}
