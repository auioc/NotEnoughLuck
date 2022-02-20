package org.auioc.mods.notenoughluck.common.network;

import org.auioc.mods.arnicalib.api.game.network.HPacketHandler;
import org.auioc.mods.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingScreenPacket;
import org.auioc.mods.notenoughluck.server.network.RequestUpdateTungShingScreenPacket;
import net.minecraft.server.level.ServerPlayer;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static HPacketHandler HANDLER;

    public static void init() {
        HANDLER = new HPacketHandler(NotEnoughLuck.MOD_ID, "main", PROTOCOL_VERSION);
        registerMessage();
    }

    private static void registerMessage() {
        HANDLER.registerServerToClient(UpdateTungShingScreenPacket.class, UpdateTungShingScreenPacket::decode);
        HANDLER.registerClientToServer(RequestUpdateTungShingScreenPacket.class, RequestUpdateTungShingScreenPacket::decode);
    }

    public static <MSG extends IHPacket> void sendToServer(MSG msg) {
        HANDLER.sendToServer(msg);
    }

    public static <MSG extends IHPacket> void sendToClient(ServerPlayer player, MSG msg) {
        HANDLER.sendToClient(player, msg);
    }

}
