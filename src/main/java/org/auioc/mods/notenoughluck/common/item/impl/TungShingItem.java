package org.auioc.mods.notenoughluck.common.item.impl;

import java.util.HashMap;
import java.util.Map;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingPacket;
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

    public TungShingItem() {
        super(
            new Item.Properties()
                .tab(ItemGroupRegistry.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            int day = UnseiUtils.getDay(level.getDayTime());
            long seed = ((ServerLevel) level).getSeed();
            Map<Integer, Integer> unseiMap = new HashMap<Integer, Integer>() {
                {
                    put(day - 1, UnseiUtils.getUnseiValue(seed, day - 1));
                    put(day, UnseiUtils.getUnseiValue(seed, day));
                    put(day + 1, UnseiUtils.getUnseiValue(seed, day + 1));
                }
            };
            PacketHandler.sendToClient(((ServerPlayer) player), new UpdateTungShingPacket(unseiMap));
            // DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TungShingScreen::open);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

}
