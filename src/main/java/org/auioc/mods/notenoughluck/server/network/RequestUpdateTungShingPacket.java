package org.auioc.mods.notenoughluck.server.network;

import org.auioc.mcmod.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
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
        UnseiUtils.sendUpdateTungShingPacket(player, player.getLevel().getSeed(), this.day, this.classic);
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
