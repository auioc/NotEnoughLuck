package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.notenoughluck.common.effect.NELMobEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class PotionAddedHandler {

    public static void handle(final PotionAddedEvent event) {
        if (event.getEntityLiving() instanceof ServerPlayer player) {
            var newEffect = event.getPotionEffect();
            if (
                newEffect.getEffect() == MobEffects.UNLUCK
                    && newEffect.getAmplifier() > 0
            ) {
                var redemptionEffect = player.getEffect(NELMobEffects.REDEMPTION.get());
                if (redemptionEffect != null) {
                    int newUnluckAmplifier = newEffect.getAmplifier() - (redemptionEffect.getAmplifier() + 1);
                    ObfuscationReflectionHelper.setPrivateValue(
                        MobEffectInstance.class,
                        newEffect,
                        Math.max(newUnluckAmplifier, 0),
                        "f_19504_" // amplifier
                    );
                }
            }
        }
    }

}
