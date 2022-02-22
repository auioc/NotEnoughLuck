package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.client.gui.screen.tungshing.TungShingScreenUtils;
import org.auioc.mods.notenoughluck.common.item.NELItems;
import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class TungShingItem extends Item {

    public static final int COOLDOWN = 10 * 20;

    public TungShingItem() {
        super(
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
            addCooldown(player, COOLDOWN);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    public static void addCooldown(Player player, int cooldown) {
        player.getCooldowns().addCooldown(NELItems.TUNG_SHING_ITEM.get(), cooldown);
    }

    public static void removeCooldown(Player player) {
        player.getCooldowns().removeCooldown(NELItems.TUNG_SHING_ITEM.get());
    }

}
