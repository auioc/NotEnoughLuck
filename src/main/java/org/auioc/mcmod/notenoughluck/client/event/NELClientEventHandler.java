package org.auioc.mcmod.notenoughluck.client.event;

import org.auioc.mcmod.notenoughluck.client.unsei.ClientUnseiCache;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NELClientEventHandler {

    @SubscribeEvent
    public static void onPlayerLoggedIn(final LoggedInEvent event) {
        ClientUnseiCache.clear();
    }

}
