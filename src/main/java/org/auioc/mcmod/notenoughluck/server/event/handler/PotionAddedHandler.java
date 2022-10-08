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
                var redemptionEffect = player.getEffect(NELMobEffects.REDEMPTION.get());
                if (redemptionEffect != null) {
                    if (newEffect.getAmplifier() > 0) {
                        int newUnluckAmplifier = newEffect.getAmplifier() - (redemptionEffect.getAmplifier() + 1);
                        EffectUtils.setAmplifier(newEffect, Math.max(newUnluckAmplifier, 0));
                    } else {
                        EffectUtils.setDuration(newEffect, 1);
                    }
                }
            }
        }
    }

}
