package org.auioc.mcmod.notenoughluck.common.network.packet.server;

import org.auioc.mcmod.arnicalib.game.network.IHPacket;
import org.auioc.mcmod.notenoughluck.server.unsei.ServerUnseiUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;

public class RequestUpdateTungShingPacket implements IHPacket {

    private final int day;
    private final boolean classic;

    public RequestUpdateTungShingPacket(int day, boolean classic) {
        this.day = day;
        this.classic = classic;

    }

    @Override
    public void handle(Context ctx) {
        ServerPlayer player = ctx.getSender();
        ServerUnseiUtils.sendUpdateTungShingPacket(player, this.day, this.classic);
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.day);
        buffer.writeBoolean(this.classic);
    }

    public static RequestUpdateTungShingPacket decode(FriendlyByteBuf buffer) {
        return new RequestUpdateTungShingPacket(buffer.readInt(), buffer.readBoolean());
    }

}
