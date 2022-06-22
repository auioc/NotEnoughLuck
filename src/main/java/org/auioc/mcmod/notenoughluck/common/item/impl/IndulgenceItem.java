package org.auioc.mcmod.notenoughluck.common.item.impl;

import org.auioc.mcmod.arnicalib.api.game.item.UsableItem;
import org.auioc.mcmod.arnicalib.utils.game.EffectUtils;
import org.auioc.mcmod.notenoughluck.common.effect.NELMobEffects;
import org.auioc.mcmod.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class IndulgenceItem extends UsableItem {

    private static final int USE_DURATION = 3 * 20;
    private static final int REDEMPTION_DURATION = 20 * 60 * 20;

    public IndulgenceItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .rarity(Rarity.RARE)
                .stacksTo(1),
            USE_DURATION,
            UseAnim.BOW
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (living instanceof ServerPlayer player) {
            player.addEffect(
                EffectUtils.makeIncurable(
                    new MobEffectInstance(NELMobEffects.REDEMPTION.get(), REDEMPTION_DURATION)
                )
            );
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return stack;
    }

}
