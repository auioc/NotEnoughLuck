package org.auioc.mcmod.notenoughluck.common.item.impl;

import org.auioc.mcmod.notenoughluck.common.item.base.DiceItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class CommonDiceItem extends DiceItem {

    private static final int EFFECT_DURATION = 60 * 20;

    public CommonDiceItem() {
        super(Rarity.COMMON);
    }

    @Override
    protected int getCooldown() {
        return Config.cooldown.get() * 20;
    }

    @Override
    protected MobEffectInstance getEffect(int pips, CompoundTag nbt) {
        if (pips < 4) {
            return new MobEffectInstance(MobEffects.UNLUCK, EFFECT_DURATION, (4 - pips) - 1);
        } else {
            return new MobEffectInstance(MobEffects.LUCK, EFFECT_DURATION, (pips - 3) - 1);
        }
    }

    public static class Config {
        public static IntValue cooldown;

        public static void build(final ForgeConfigSpec.Builder b) {
            cooldown = b.defineInRange("cooldown", 10 * 60, 0, Integer.MAX_VALUE);
        }
    }

}
