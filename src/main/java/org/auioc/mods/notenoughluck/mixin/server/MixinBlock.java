package org.auioc.mods.notenoughluck.mixin.server;

import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

@Mixin(value = Block.class)
public abstract class MixinBlock {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Inject(
        method = "Lnet/minecraft/world/level/block/Block;getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;getDrops(Lnet/minecraft/world/level/storage/loot/LootContext$Builder;)Ljava/util/List;",
            ordinal = 0
        ),
        locals = LocalCapture.CAPTURE_FAILHARD,
        cancellable = true,
        require = 1,
        allow = 1
    )
    private static void getDrops(
        BlockState p_49875_, ServerLevel p_49876_, BlockPos p_49877_, BlockEntity p_49878_, Entity p_49879_, ItemStack p_49880_,
        CallbackInfoReturnable<List<ItemStack>> cir,
        LootContext.Builder lootcontext$builder
    ) {
        if (p_49879_ instanceof ServerPlayer) {
            cir.setReturnValue(p_49875_.getDrops(lootcontext$builder.withLuck(((ServerPlayer) p_49879_).getLuck())));
        }
    }

}
