package org.auioc.mcmod.notenoughluck.server.unsei;

import org.auioc.mcmod.notenoughluck.server.config.NELServerConfig;
import org.auioc.mcmod.notenoughluck.utils.UnseiUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class UnseiEffectHandler {

    public static final int INTERVAL = NELServerConfig.UnseiEffectInterval.get() * 20;
    public static final int DURATION = INTERVAL + 5;

    public static void handle(MinecraftServer server) {
        var unseiPair = UnseiUtils.getUnseiPair();

        MobEffect effect;
        switch (unseiPair.getRight()) {
            case KICHI: {
                effect = MobEffects.LUCK;
                break;
            }
            case KYOU: {
                effect = MobEffects.UNLUCK;
                break;
            }
            default: {
                return;
            }
        }

        int amplifier = unseiPair.getLeft().id - 1;

        for (var player : server.getPlayerList().getPlayers()) {
            player.addEffect(new MobEffectInstance(effect, DURATION, amplifier, true, true));
        }
    }

}
