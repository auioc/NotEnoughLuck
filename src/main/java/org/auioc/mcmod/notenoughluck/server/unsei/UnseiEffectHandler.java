package org.auioc.mcmod.notenoughluck.server.unsei;

import org.auioc.mcmod.notenoughluck.utils.UnseiUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class UnseiEffectHandler {

    public static final int INTERVAL = 10 * 20;

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

        System.err.println(effect.getDescriptionId() + " " + amplifier);

        for (var player : server.getPlayerList().getPlayers()) {
            player.addEffect(new MobEffectInstance(effect, INTERVAL, amplifier, true, true));
        }
    }

}
