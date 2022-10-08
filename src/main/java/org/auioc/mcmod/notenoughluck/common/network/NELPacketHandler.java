package org.auioc.mcmod.notenoughluck.common.network;

import org.auioc.mcmod.arnicalib.game.network.HPacketHandler;
import org.auioc.mcmod.arnicalib.game.network.IHPacket;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import org.auioc.mcmod.notenoughluck.common.network.packet.client.ClearClientUnseiCachePacket;
import org.auioc.mcmod.notenoughluck.common.network.packet.client.UpdateTungShingPacket;
import org.auioc.mcmod.notenoughluck.common.network.packet.server.RequestUpdateTungShingPacket;
import net.minecraft.server.level.ServerPlayer;

public class NELPacketHandler {

    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final HPacketHandler HANDLER = new HPacketHandler(NotEnoughLuck.MOD_ID, "main", PROTOCOL_VERSION);

    public static void init() {
        registerMessage();
    }

    private static void registerMessage() {
        HANDLER.registerServerToClient(ClearClientUnseiCachePacket.class, ClearClientUnseiCachePacket::decode);
        HANDLER.registerServerToClient(UpdateTungShingPacket.class, UpdateTungShingPacket::decode);
        HANDLER.registerClientToServer(RequestUpdateTungShingPacket.class, RequestUpdateTungShingPacket::decode);
    }

    public static <MSG extends IHPacket> void sendToServer(MSG msg) {
        HANDLER.sendToServer(msg);
    }

    public static <MSG extends IHPacket> void sendToClient(ServerPlayer player, MSG msg) {
        HANDLER.sendToClient(player, msg);
    }

}
