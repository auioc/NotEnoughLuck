package org.auioc.mods.notenoughluck.common.item.impl;

import javax.annotation.Nullable;
import org.auioc.mods.notenoughluck.common.item.base.DiceItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Rarity;

public class DiceOfTycheItem extends DiceItem {

    private static final int EFFECT_DURATION = 2 * 60 * 20;
    private static final int COOLDOWN = 10 * 20;

    public DiceOfTycheItem() {
        super(Rarity.RARE);
    }

    @Override
    protected int getCooldown() {
        return COOLDOWN;
    }

    @Override
    protected void afterPickup(ServerPlayer player, int pips) {
        if (pips == 6) {
            player.getCooldowns().removeCooldown(this);
        }
    }

    @Override
    @Nullable
    protected MobEffectInstance getEffect(int pips, CompoundTag nbt) {
        int bonus = nbt.getInt("Bonus");
        if (pips < 6) {
            nbt.putInt("Bonus", 0);
            return new MobEffectInstance(MobEffects.LUCK, EFFECT_DURATION, (pips + bonus) - 1);
        } else {
            nbt.putInt("Bonus", bonus + 1);
            return null;
        }
    }

}
