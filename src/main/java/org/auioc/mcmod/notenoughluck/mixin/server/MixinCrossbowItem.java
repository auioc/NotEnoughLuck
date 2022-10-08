package org.auioc.mcmod.notenoughluck.mixin.server;

import org.auioc.mcmod.arnicalib.game.effect.EffectUtils;
import org.auioc.mcmod.notenoughluck.api.mixin.server.IMixinAbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(value = CrossbowItem.class)
public abstract class MixinCrossbowItem {

    @Inject(
        method = "Lnet/minecraft/world/item/CrossbowItem;getArrow(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/AbstractArrow;",
        at = @At(value = "TAIL"),
        locals = LocalCapture.CAPTURE_FAILHARD,
        require = 1,
        allow = 1
    )
    private static void getArrow(
        Level p_40915_, LivingEntity p_40916_, ItemStack p_40917_, ItemStack p_40918_,
        CallbackInfoReturnable<AbstractArrow> cir,
        ArrowItem arrowitem, AbstractArrow abstractarrow, int i
    ) {
        if (p_40916_ instanceof ServerPlayer) {
            IMixinAbstractArrow mixinabstractarrow = ((IMixinAbstractArrow) abstractarrow);
            mixinabstractarrow.setLuck(EffectUtils.getEffectLevel(p_40916_, MobEffects.LUCK));
            mixinabstractarrow.setUnluck(EffectUtils.getEffectLevel(p_40916_, MobEffects.UNLUCK));
        }
    }

}
