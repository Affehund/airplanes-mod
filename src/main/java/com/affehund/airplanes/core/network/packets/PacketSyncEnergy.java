package com.affehund.airplanes.core.network.packets;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.core.utils.IMachineStateContainer;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncEnergy implements IMessage
{
	private int energy;
    private int progress;

    @Override
    public void fromBytes(ByteBuf buf)
    {
        energy = buf.readInt();
        progress = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(energy);
        buf.writeByte(progress);
    }

    public PacketSyncEnergy()
    {
    	
    }

    public PacketSyncEnergy(int energy, int progress)
    {
        this.energy = energy;
        this.progress = progress;
    }

    public static class Handler implements IMessageHandler<PacketSyncEnergy, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSyncEnergy message, MessageContext ctx)
        {
            AirplanesMod.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }
        
        private void handle(PacketSyncEnergy message, MessageContext ctx)
        {
            EntityPlayer player = AirplanesMod.proxy.getClientPlayer();
            if (player.openContainer instanceof IMachineStateContainer)
            {
               ((IMachineStateContainer) player.openContainer).sync(message.energy, message.progress);
            }
        }
    }
}
