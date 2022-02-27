package org.auioc.mods.notenoughluck.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.world.entity.player.Player;

@Mixin(value = Player.class)
public abstract class MixinPlayer {

    // @Inject(
    //     method = "Lnet/minecraft/world/entity/player/Player;attack(Lnet/minecraft/world/entity/Entity;)V",
    //     at = @At(
    //         value = "JUMP",
    //         opcode = Opcodes.IFNE,
    //         ordinal = 1,
    //         shift = Shift.AFTER
    //     ), cancellable = true,
    //     require = 1,
    //     allow = 1
    // )
    // private void modifyAttackDamageUnluck(Entity p_36347_, CallbackInfo ci) {
    //     ci.cancel();
    // }

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
        return f;
    }

}
