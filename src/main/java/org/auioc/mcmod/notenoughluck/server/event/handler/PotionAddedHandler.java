package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.arnicalib.game.effect.EffectUtils;
import org.auioc.mcmod.notenoughluck.common.effect.NELMobEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;

public class PotionAddedHandler {

    public static void handle(final PotionAddedEvent event) {
        if (event.getEntityLiving() instanceof ServerPlayer player) {
            var newEffect = event.getPotionEffect();
            if (newEffect.getEffect() == MobEffects.UNLUCK) {
                int redemption = EffectUtils.getEffectLevel(player, NELMobEffects.REDEMPTION.get());
                if (redemption > 0) {
                    if (newEffect.getAmplifier() > 0) {
                        int newUnluckAmplifier = newEffect.getAmplifier() - redemption;
                        EffectUtils.setAmplifier(newEffect, Math.max(newUnluckAmplifier, 0));
                    } else {
                        EffectUtils.setDuration(newEffect, 1);
                    }
                }
            }
        }
    }

}
