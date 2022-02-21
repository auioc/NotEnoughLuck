package org.auioc.mods.notenoughluck.common.item.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingPacket;
import org.auioc.mods.notenoughluck.common.item.ItemRegistry;
import org.auioc.mods.notenoughluck.common.itemgroup.ItemGroupRegistry;
import org.auioc.mods.notenoughluck.common.network.PacketHandler;
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

    public static final int COOLDOWN = 10 * 20;

    public TungShingItem() {
        super(
            new Item.Properties()
                .tab(ItemGroupRegistry.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            addCooldown(player);

            Pair<int[], int[]> unsei = UnseiUtils.getThreeDaysUnsei(((ServerLevel) level).getSeed(), UnseiUtils.getDay(level.getDayTime()));
            PacketHandler.sendToClient(
                ((ServerPlayer) player),
                new UpdateTungShingPacket(unsei.getLeft(), unsei.getRight())
            );
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    public static void addCooldown(Player player) {
        player.getCooldowns().addCooldown(ItemRegistry.TUNG_SHING_ITEM.get(), COOLDOWN);
    }

}
