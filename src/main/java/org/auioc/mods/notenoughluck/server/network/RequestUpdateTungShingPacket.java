package org.auioc.mods.notenoughluck.server.network;

import java.util.UUID;
import org.auioc.mods.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingPacket;
import org.auioc.mods.notenoughluck.common.network.PacketHandler;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.server.ServerLifecycleHooks;

public class RequestUpdateTungShingPacket implements IHPacket {

    private final UUID playerUUID;
    private final int day;

    public RequestUpdateTungShingPacket(UUID playerUUID, int day) {
        this.playerUUID = playerUUID;
        this.day = day;
    }

    @Override
    public void handle(Context ctx) {
        ServerPlayer player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(this.playerUUID);

        int day = this.day;
        long seed = player.getLevel().getSeed();

        PacketHandler.sendToClient(
            ((ServerPlayer) player), new UpdateTungShingPacket(
                new int[] {
                    day - 1,
                    day,
                    day + 1
                },
                new int[] {
                    UnseiUtils.getUnseiValue(seed, day - 1),
                    UnseiUtils.getUnseiValue(seed, day),
                    UnseiUtils.getUnseiValue(seed, day + 1)
                }
            )
        );
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(this.playerUUID);
        buffer.writeInt(this.day);
    }

    public static RequestUpdateTungShingPacket decode(FriendlyByteBuf buffer) {
        return new RequestUpdateTungShingPacket(buffer.readUUID(), buffer.readInt());
    }

}
