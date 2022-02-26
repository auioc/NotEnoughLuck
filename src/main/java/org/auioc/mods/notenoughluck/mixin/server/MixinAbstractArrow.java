package org.auioc.mods.notenoughluck.mixin.server;

import javax.annotation.Nullable;
import org.auioc.mods.notenoughluck.api.mixin.server.IMixinAbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(value = AbstractArrow.class)
public abstract class MixinAbstractArrow extends Projectile implements IMixinAbstractArrow {

    protected MixinAbstractArrow(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    // ====================================================================== //
    //#region A

    @Inject(
        method = "Lnet/minecraft/world/entity/projectile/AbstractArrow;onHitBlock(Lnet/minecraft/world/phys/BlockHitResult;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;setPierceLevel(B)V", ordinal = 0),
        require = 1,
        allow = 1
    )
    protected void onHitBlock(BlockHitResult p_36755_, CallbackInfo ci) {
        if (
            (((AbstractArrow) (Object) this) instanceof Arrow)
                && !this.level.isClientSide()
                && this.getPierceLevel() > 0
                && this.piercingIgnoreEntityIds != null
                && this.piercingIgnoreEntityIds.size() > 0
        ) {
            CompoundTag nbt = new CompoundTag();
            this.addAdditionalSaveData(nbt);
            if (nbt.contains("Potion") || nbt.contains("CustomPotionEffects")) {
                this.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
        }
    }

    @Shadow
    protected abstract byte getPierceLevel();

    @Shadow
    protected abstract void addAdditionalSaveData(CompoundTag p_36772_);

    @Nullable
    @Shadow
    private IntOpenHashSet piercingIgnoreEntityIds;

    @Shadow
    private AbstractArrow.Pickup pickup;

    //#endregion A

    // ====================================================================== //
    //#region B

    private int luck = 0;
    private int unluck = 0;

    @Override
    public void setLuck(int luck) {
        this.luck = luck;
    }

    @Override
    public void setUnluck(int unluck) {
        this.unluck = unluck;
    }

    //#endregion B

}
