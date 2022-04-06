package org.auioc.mods.notenoughluck.mixin.server;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Triple;
import org.auioc.mcmod.arnicalib.utils.game.EffectUtils;
import org.auioc.mcmod.arnicalib.utils.java.RandomUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Mixin(value = Player.class)
public abstract class MixinPlayer extends LivingEntity {

    protected MixinPlayer(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    // ====================================================================== //
    //#region A

    // @Inject(
    //     method = "Lnet/minecraft/world/entity/player/Player;attack(Lnet/minecraft/world/entity/Entity;)V",
    //     at = @At(value = "JUMP", opcode = Opcodes.IFNE, ordinal = 1, shift = Shift.AFTER),
    //     cancellable = true, require = 1, allow = 1
    // )
    // private void modifyAttackDamageUnluck(Entity p_36347_, CallbackInfo ci) {
    //     ci.cancel();
    // }

    private static final List<Triple<Integer, Function<Integer, Float>, Function<Integer, Float>>> ATTACK_LUCK_BONUS_MAP =
        new ArrayList<Triple<Integer, Function<Integer, Float>, Function<Integer, Float>>>() {
            {
                add(Triple.of(15, (n) -> 1.25F, (n) -> 1.0F));
                add(Triple.of(30, (n) -> 1.5F, (n) -> 1.0F));
                add(Triple.of(45, (n) -> 1.75F, (n) -> 1.0F));
                add(Triple.of(60, (n) -> 2.0F, (n) -> 1.0F));
                add(Triple.of(75, (n) -> 1.0F + 0.2F * ((float) n), (n) -> 0.75F + ((float) Math.pow(1.5D, ((double) n) - 4.0D))));
            }
        };

    @ModifyVariable(
        method = "Lnet/minecraft/world/entity/player/Player;attack(Lnet/minecraft/world/entity/Entity;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            ordinal = 0,
            shift = Shift.BY, by = -3
        ),
        index = 2,
        require = 1,
        allow = 1
    )
    private float modifyAttackDamage(float f) {
        Player player = (Player) (Object) this;

        if (player.level.isClientSide) {
            return f;
        }

        int unluck = EffectUtils.getEffectLevel(player, MobEffects.UNLUCK);
        if (unluck > 0 && !(player.getRandom().nextDouble() < Math.pow(0.8, (double) unluck))) {
            return 0.0F;
        }

        int luck = EffectUtils.getEffectLevel(player, MobEffects.LUCK);
        if (luck > 0) {
            Triple<Integer, Function<Integer, Float>, Function<Integer, Float>> bonusTriple =
                ATTACK_LUCK_BONUS_MAP.get(Math.min(luck - 1, ATTACK_LUCK_BONUS_MAP.size() - 1));

            if (RandomUtils.percentageChance(bonusTriple.getLeft(), player.getRandom())) {
                f *= bonusTriple.getMiddle().apply(luck);
            } else {
                f *= bonusTriple.getRight().apply(luck);
            }
        }

        return f;
    }

    //#endregion A

}
