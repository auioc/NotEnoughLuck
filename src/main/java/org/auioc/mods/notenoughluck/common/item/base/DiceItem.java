package org.auioc.mods.notenoughluck.common.item.base;

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
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putBoolean("Disabled", false);
            nbt.putInt("Pips", player.getRandom().nextInt(1, 7));
            beforeDrop(nbt);
        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (level.isClientSide || !stack.hasTag() || !(entity instanceof ServerPlayer)) {
            return;
        }
        ServerPlayer player = (ServerPlayer) entity;
        CompoundTag nbt = stack.getTag();

        if (nbt.getBoolean("Disabled")) {
            return;
        }
        nbt.putBoolean("Disabled", true);

        int pips = nbt.getInt("Pips");

        EffectUtils.removeEffect(player, (e) -> e.getEffect() == MobEffects.LUCK || e.getEffect() == MobEffects.UNLUCK);
        player.addEffect(getEffect(pips, nbt));
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

    protected abstract int getCooldown();

    protected abstract void beforeDrop(CompoundTag nbt);

    protected abstract MobEffectInstance getEffect(int pips, CompoundTag nbt);

}
