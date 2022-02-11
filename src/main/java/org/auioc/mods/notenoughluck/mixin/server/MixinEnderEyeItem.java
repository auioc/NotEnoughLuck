package org.auioc.mods.notenoughluck.mixin.server;

import org.auioc.mods.arnicalib.utils.game.EffectUtils;
import org.auioc.mods.notenoughluck.api.mixin.server.IMixinEyeOfEnder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;


@Mixin(value = EnderEyeItem.class)
public abstract class MixinEnderEyeItem {

    private static final float DEFAULT_CHANCE = 0.8F;
    private static final float LUCK_MULTIPLIER = 1.0F;
    private static final float UNLUCK_MULTIPLIER = -4.0F;
    private static final float BONUS_MULTIPLIER = 0.04F;

    @SuppressWarnings("rawtypes")
    @Inject(
        method = "Lnet/minecraft/world/item/EnderEyeItem;use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z",
            shift = At.Shift.BEFORE
        ),
        locals = LocalCapture.CAPTURE_FAILHARD,
        require = 1,
        allow = 1
    )
    private void use(Level p_41184_, Player p_41185_, InteractionHand p_41186_, CallbackInfoReturnable<InteractionResultHolder> cir, ItemStack itemstack, HitResult hitresult, BlockPos blockpos, EyeOfEnder eyeofender) {
        float luckBonus = ((float) EffectUtils.getEffectLevel(p_41185_, MobEffects.LUCK)) * LUCK_MULTIPLIER;
        float unluckBonus = ((float) EffectUtils.getEffectLevel(p_41185_, MobEffects.UNLUCK)) * UNLUCK_MULTIPLIER;
        float bonus = luckBonus + unluckBonus;
        float chance = Mth.clamp((DEFAULT_CHANCE + (bonus * BONUS_MULTIPLIER)), 0.0F, 1.0F);
        ((IMixinEyeOfEnder) eyeofender).setSurviveChance(chance);
    }

}
