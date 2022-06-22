package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.notenoughluck.common.attribute.NELAttributes;
import org.auioc.mcmod.notenoughluck.utils.UnseiUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerLoginHandler {

    public static void handle(final PlayerEvent.PlayerLoggedInEvent event) {
        ((ServerPlayer) event.getPlayer())
            .getAttribute(NELAttributes.UNSEI.get())
            .setBaseValue(UnseiUtils.getUnseiValue());
    }

}
