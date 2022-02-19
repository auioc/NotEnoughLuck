package org.auioc.mods.notenoughluck.utils;

import org.auioc.mods.arnicalib.utils.game.EffectUtils;
import org.auioc.mods.arnicalib.utils.java.RandomUtils;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LuckUtils {

    public static int getLuckEffectLevel(LivingEntity living) {
        return EffectUtils.getEffectLevel(living, MobEffects.LUCK);
    }

    public static int getUnluckEffectLevel(LivingEntity living) {
        return EffectUtils.getEffectLevel(living, MobEffects.UNLUCK);
    }

    public static int getLuckValueByEffect(LivingEntity living) {
        return getLuckEffectLevel(living) - getUnluckEffectLevel(living);
    }

    public static double getLuckValueByAttribute(LivingEntity living) {
        return living.getAttributeValue(Attributes.LUCK);
    }

    public static int getChance(int defaultChance, int luckLevel, int unluckLevel, int luckMultiplier, int unluckMultiplier, int bonusMultiplier, int denominator) {
        int luckBonus = luckLevel * luckMultiplier;
        int unluckBonus = unluckLevel * unluckMultiplier;
        int bonus = luckBonus + unluckBonus;
        return Mth.clamp(defaultChance + (bonus * bonusMultiplier), 0, denominator);
    }

    public static int getChance(LivingEntity living, int defaultChance, int luckMultiplier, int unluckMultiplier, int bonusMultiplier, int denominator) {
        return getChance(defaultChance, getLuckEffectLevel(living), getUnluckEffectLevel(living), luckMultiplier, unluckMultiplier, bonusMultiplier, denominator);
    }

    public static int getChance(LivingEntity living, int defaultChance, int luckMultiplier, int unluckMultiplier, int bonusMultiplier) {
        return getChance(living, defaultChance, luckMultiplier, unluckMultiplier, bonusMultiplier, 100);
    }

    public static boolean getRandomBoolean(LivingEntity living, int defaultChance, int luckMultiplier, int unluckMultiplier, int bonusMultiplier, int denominator) {
        return RandomUtils.fractionChance(getChance(living, defaultChance, luckMultiplier, unluckMultiplier, bonusMultiplier, denominator), denominator, living.getRandom());
    }

    public static boolean getRandomBoolean(LivingEntity living, int defaultChance, int luckMultiplier, int unluckMultiplier, int bonusMultiplier) {
        return RandomUtils.fractionChance(getChance(living, defaultChance, luckMultiplier, unluckMultiplier, bonusMultiplier, 100), 100, living.getRandom());
    }

}
