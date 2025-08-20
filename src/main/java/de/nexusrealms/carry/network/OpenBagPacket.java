package de.nexusrealms.carry.network;

import de.nexusrealms.carry.NexusCarry;
import de.nexusrealms.carry.item.BagItem;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public class OpenBagPacket implements ReceiverPacket<ServerPlayNetworking.Context> {
    public static final OpenBagPacket INSTANCE = new OpenBagPacket();
    public static final Id<OpenBagPacket> ID = new Id<>(NexusCarry.id("open_bag"));
    public static final PacketCodec<ByteBuf, OpenBagPacket> PACKET_CODEC = PacketCodec.unit(INSTANCE);
    @Override
    public void receive(ServerPlayNetworking.Context context) {
        BagItem.tryOpenScreen(context.player());
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
