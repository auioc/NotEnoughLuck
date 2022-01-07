package org.auioc.mods.notenoughluck.mixin.common;

import javax.annotation.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(value = AbstractArrow.class)
public abstract class MixinAbstractArrow {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Inject(
        method = "Lnet/minecraft/world/entity/projectile/AbstractArrow;onHitBlock(Lnet/minecraft/world/phys/BlockHitResult;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;setPierceLevel(B)V", ordinal = 0),
        require = 1,
        allow = 1
    )
    protected void onHitBlock(BlockHitResult p_36755_, CallbackInfo ci) {
        if (this.getPierceLevel() > 0 && this.piercingIgnoreEntityIds != null && this.piercingIgnoreEntityIds.size() > 0) {
            this.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
    }


    @Shadow
    protected abstract byte getPierceLevel();

    @Nullable
    @Shadow
    private IntOpenHashSet piercingIgnoreEntityIds;

    @Shadow
    private AbstractArrow.Pickup pickup;

}
