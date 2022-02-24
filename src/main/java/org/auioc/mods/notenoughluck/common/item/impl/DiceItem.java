package org.auioc.mods.notenoughluck.common.item.impl;

import java.util.function.Consumer;
import org.auioc.mods.arnicalib.utils.game.EffectUtils;
import org.auioc.mods.notenoughluck.client.renderer.DiceItemRenderer;
import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

public class DiceItem extends Item {

    private static final int EFFECT_DURATION = 60 * 20;

    public DiceItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.remove("used");
        nbt.putInt("pips", player.getRandom().nextInt(1, 7));
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (level.isClientSide || !stack.hasTag() || !(entity instanceof ServerPlayer)) {
            return;
        }
        ServerPlayer player = (ServerPlayer) entity;
        CompoundTag nbt = stack.getTag();

        if (nbt.getBoolean("used")) {
            return;
        }
        nbt.putBoolean("used", true);

        int pips = nbt.getInt("pips");

        EffectUtils.removeEffect(player, (e) -> e.getEffect() == MobEffects.LUCK || e.getEffect() == MobEffects.UNLUCK);

        if (pips < 4) {
            player.addEffect(new MobEffectInstance(MobEffects.UNLUCK, EFFECT_DURATION, (4 - pips) - 1));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, EFFECT_DURATION, (pips - 3) - 1));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return DiceItemRenderer.INSTANCE;
            }
        });
    }

}
