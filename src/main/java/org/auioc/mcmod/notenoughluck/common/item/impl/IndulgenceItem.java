package org.auioc.mcmod.notenoughluck.common.item.impl;

import java.util.ArrayList;
import org.auioc.mcmod.notenoughluck.common.effect.NELMobEffects;
import org.auioc.mcmod.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class IndulgenceItem extends Item {

    private static final int USE_DURATION = 3 * 20;
    private static final int REDEMPTION_DURATION = 20 * 60 * 20;

    public IndulgenceItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .rarity(Rarity.RARE)
                .stacksTo(1)
        );
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (living instanceof ServerPlayer player) {
            var redemptionEffect = new MobEffectInstance(NELMobEffects.REDEMPTION.get(), REDEMPTION_DURATION);
            redemptionEffect.setCurativeItems(new ArrayList<ItemStack>());
            player.addEffect(redemptionEffect);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return stack;
    }

}
