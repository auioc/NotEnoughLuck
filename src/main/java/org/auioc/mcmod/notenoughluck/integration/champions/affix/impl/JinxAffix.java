package org.auioc.mcmod.notenoughluck.integration.champions.affix.impl;

import java.util.function.ToIntFunction;
import org.auioc.mcmod.arnicalib.utils.java.RandomUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import top.theillusivec4.champions.api.AffixCategory;
import top.theillusivec4.champions.api.IChampion;
import top.theillusivec4.champions.common.affix.core.BasicAffix;
import top.theillusivec4.champions.common.rank.Rank;
import top.theillusivec4.champions.common.rank.RankManager;

public class JinxAffix extends BasicAffix {

    private static final int EFFECT_DURATION = 5 * 20;
    private static final ToIntFunction<Rank> EFFECT_AMPLIFIER = (rank) -> RandomUtils.nextInt(0, rank.getTier() + 1); // effectLevel∈[1, tier+2)

    public JinxAffix() {
        super("jinx", AffixCategory.OFFENSE);
    }

    @Override
    public float onDamage(IChampion champion, DamageSource source, float amount, float newAmount) {
        if (source.getEntity() instanceof LivingEntity) {
            var rank = champion.getServer().getRank().orElse(RankManager.getLowestRank());
            ((LivingEntity) source.getEntity()).addEffect(
                new MobEffectInstance(
                    MobEffects.UNLUCK,
                    EFFECT_DURATION,
                    EFFECT_AMPLIFIER.applyAsInt(rank)
                )
            );
        }
        return super.onDamage(champion, source, amount, newAmount);
    }

}
