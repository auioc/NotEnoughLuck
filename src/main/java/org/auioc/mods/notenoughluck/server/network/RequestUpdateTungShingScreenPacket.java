package org.auioc.mods.notenoughluck.server.network;

import java.util.Map;
import java.util.UUID;
import org.auioc.mods.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingScreenPacket;
import org.auioc.mods.notenoughluck.common.network.PacketHandler;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.server.ServerLifecycleHooks;

public class RequestUpdateTungShingScreenPacket implements IHPacket {

    private final UUID playerUUID;
    private final int day;

    public RequestUpdateTungShingScreenPacket(UUID playerUUID, int day) {
        this.playerUUID = playerUUID;
        this.day = day;
    }

    @Override
    public void handle(Context ctx) {
        ServerPlayer player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(this.playerUUID);
        if (player == null) {
            return;
        }

        PacketHandler.sendToClient(
            ((ServerPlayer) player), new UpdateTungShingScreenPacket(
                Map.of(this.day, UnseiUtils.getUnseiValue(player.getLevel().getSeed(), day))
            )
        );
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.playerUUID);
        buffer.writeInt(this.day);
    }

    public static RequestUpdateTungShingScreenPacket decode(FriendlyByteBuf buffer) {
        return new RequestUpdateTungShingScreenPacket(buffer.readUUID(), buffer.readInt());
    }

}
