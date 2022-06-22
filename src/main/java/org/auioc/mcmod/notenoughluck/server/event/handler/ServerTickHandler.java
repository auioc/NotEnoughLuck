package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.notenoughluck.server.unsei.UnseiEffectHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ServerTickHandler {

    private static final MinecraftServer SERVER = ServerLifecycleHooks.getCurrentServer();

    public static void handleStartPhase() {}

    public static void handleEndPhase() {
        int tickCount = SERVER.getTickCount();
        if (tickCount % UnseiEffectHandler.INTERVAL == 0) UnseiEffectHandler.handle(SERVER);
    }

}
