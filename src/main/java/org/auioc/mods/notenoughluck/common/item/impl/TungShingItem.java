package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.common.item.NELItems;
import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TungShingItem extends Item {

    public static final int COOLDOWN = 1 * 20;

    public TungShingItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            UnseiUtils.sendUpdateTungShingPacket(((ServerPlayer) player), ((ServerLevel) level).getSeed(), UnseiUtils.getDay(level.getDayTime()));
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    public static void addCooldown(Player player) {
        player.getCooldowns().addCooldown(NELItems.TUNG_SHING_ITEM.get(), COOLDOWN);
    }

}
