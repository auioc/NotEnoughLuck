package org.auioc.mcmod.notenoughluck.common.item.impl;

import org.auioc.mcmod.notenoughluck.client.gui.screen.tungshing.TungShingScreenUtils;
import org.auioc.mcmod.notenoughluck.common.block.NELBlocks;
import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import org.auioc.mcmod.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.DistExecutor;

public class TungShingItem extends BlockItem {

    public TungShingItem() {
        super(
            NELBlocks.TUNG_SHING_BLOCK.get(),
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide) {
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TungShingScreenUtils::open);
        } else {
            addCooldown(player, getCooldown());
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    public static int getCooldown() {
        return Config.cooldown.get() * 20;
    }

    public static void addCooldown(Player player, int cooldown) {
        player.getCooldowns().addCooldown(NELItems.TUNG_SHING_ITEM.get(), cooldown);
    }

    public static void removeCooldown(Player player) {
        player.getCooldowns().removeCooldown(NELItems.TUNG_SHING_ITEM.get());
    }

    public static class Config {
        public static IntValue cooldown;

        public static void build(final ForgeConfigSpec.Builder b) {
            cooldown = b.defineInRange("cooldown", 3, 0, Integer.MAX_VALUE);
        }
    }

}
