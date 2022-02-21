package org.auioc.mods.notenoughluck.client.network;

import org.auioc.mods.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.notenoughluck.client.unsei.ClientUnseiCache;
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
