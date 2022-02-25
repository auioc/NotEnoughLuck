package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.common.item.base.DiceItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Rarity;

public class CommonDiceItem extends DiceItem {

    private static final int EFFECT_DURATION = 60 * 20;
    private static final int COOLDOWN = 10 * 20;

    public CommonDiceItem() {
        super(Rarity.COMMON);
    }

    @Override
    protected int getCooldown() {
        return COOLDOWN;
    }

    @Override
    protected MobEffectInstance getEffect(int pips, CompoundTag nbt) {
        if (pips < 4) {
            return new MobEffectInstance(MobEffects.UNLUCK, EFFECT_DURATION, (4 - pips) - 1);
        } else {
            return new MobEffectInstance(MobEffects.LUCK, EFFECT_DURATION, (pips - 3) - 1);
        }
    }

}
