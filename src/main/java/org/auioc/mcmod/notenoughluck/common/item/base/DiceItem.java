package org.auioc.mcmod.notenoughluck.common.item.base;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.utils.game.EffectUtils;
import org.auioc.mcmod.arnicalib.utils.game.TextUtils;
import org.auioc.mcmod.notenoughluck.client.renderer.DiceItemRenderer;
import org.auioc.mcmod.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

public abstract class DiceItem extends Item {

    public DiceItem(Rarity rarity) {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(1)
                .rarity(rarity)
        );
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        if (!player.getCooldowns().isOnCooldown(this)) {
            player.getCooldowns().addCooldown(this, getCooldown());
            var nbt = stack.getOrCreateTag();
            nbt.putBoolean("Disabled", false);
            nbt.putInt("Pips", player.getRandom().nextInt(1, 7));
            beforeDrop(stack, player);
        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (level.isClientSide || !stack.hasTag() || !(entity instanceof ServerPlayer)) {
            return;
        }
        var player = (ServerPlayer) entity;
        var nbt = stack.getTag();

        if (nbt.getBoolean("Disabled")) {
            return;
        }
        nbt.putBoolean("Disabled", true);

        int pips = nbt.getInt("Pips");

        afterPickup(player, pips);

        EffectUtils.removeEffect(player, (e) -> e.getEffect() == MobEffects.LUCK || e.getEffect() == MobEffects.UNLUCK);

        var effect = getEffect(pips, nbt);
        if (effect != null) {
            player.addEffect(effect);
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        int pips = getPips(stack);
        if (pips > 0) {
            return ((TranslatableComponent) super.getName(stack)).append(TextUtils.getStringText(" (" + pips + ")"));
        }
        return super.getName(stack);
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

    protected void beforeDrop(ItemStack stack, Player player) {};

    protected void afterPickup(ServerPlayer player, int pips) {};

    protected int getCooldown() {
        return 0;
    };

    @Nullable
    protected abstract MobEffectInstance getEffect(int pips, CompoundTag nbt);

    public static int getPips(ItemStack stack) {
        if (stack.getItem() instanceof DiceItem) {
            if (stack.hasTag()) {
                return Mth.clamp(stack.getTag().getInt("Pips"), 0, 6);
            }
            return 0;
        } else {
            throw new UnsupportedOperationException("Can only get pips of a dice item");
        }
    }

}
