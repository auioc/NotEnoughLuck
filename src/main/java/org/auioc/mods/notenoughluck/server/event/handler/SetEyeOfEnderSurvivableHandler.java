package org.auioc.mods.notenoughluck.server.event.handler;

import org.auioc.mods.arnicalib.server.event.impl.SetEyeOfEnderSurvivableEvent;
import org.auioc.mods.arnicalib.utils.game.EffectUtils;
import org.auioc.mods.arnicalib.utils.java.RandomUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;

public class SetEyeOfEnderSurvivableHandler {

    private static final int DEFAULT_CHANCE = 80;
    private static final int LUCK_MULTIPLIER = 1;
    private static final int UNLUCK_MULTIPLIER = -4;
    private static final int BONUS_MULTIPLIER = 5;

    public static void handle(final SetEyeOfEnderSurvivableEvent event) {
        ServerPlayer player = (ServerPlayer) event.getPlayer();

        int luckBonus = EffectUtils.getEffectLevel(player, MobEffects.LUCK) * LUCK_MULTIPLIER;
        int unluckBonus = EffectUtils.getEffectLevel(player, MobEffects.UNLUCK) * UNLUCK_MULTIPLIER;
        int bonus = luckBonus + unluckBonus;
        int chance = Mth.clamp((DEFAULT_CHANCE + (bonus * BONUS_MULTIPLIER)), 0, 100);

        event.setSurvivable((random) -> RandomUtils.percentageChance(chance, random));
    }

}
