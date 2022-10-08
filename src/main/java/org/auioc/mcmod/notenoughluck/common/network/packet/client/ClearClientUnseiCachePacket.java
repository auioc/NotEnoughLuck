package org.auioc.mcmod.notenoughluck.common.network.packet.client;

import org.auioc.mcmod.arnicalib.game.network.IHPacket;
import org.auioc.mcmod.notenoughluck.client.unsei.ClientUnseiCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public class ClearClientUnseiCachePacket implements IHPacket {

    @Override
    public void handle(Context ctx) {
        ClientUnseiCache.clear();
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {}

    public static ClearClientUnseiCachePacket decode(FriendlyByteBuf buffer) {
        return new ClearClientUnseiCachePacket();
    }

}
