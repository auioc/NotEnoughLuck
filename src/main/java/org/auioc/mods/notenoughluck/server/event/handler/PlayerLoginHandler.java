package org.auioc.mods.notenoughluck.server.event.handler;

import org.auioc.mods.arnicalib.utils.game.MCTimeUtils;
import org.auioc.mods.notenoughluck.common.attribute.NELAttributes;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerLoginHandler {

    public static void handle(final PlayerEvent.PlayerLoggedInEvent event) {
        var player = (ServerPlayer) event.getPlayer();
        var level = player.getLevel();
        player.getAttribute(NELAttributes.UNSEI.get())
            .setBaseValue(
                UnseiUtils.getUnseiValue(
                    level.getSeed(),
                    MCTimeUtils.getDay(level.getDayTime())
                )
            );
    }

}
