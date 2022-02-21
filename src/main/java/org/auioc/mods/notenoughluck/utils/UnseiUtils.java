package org.auioc.mods.notenoughluck.utils;

import java.util.Random;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mods.arnicalib.utils.game.MCTimeUtils;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingPacket;
import org.auioc.mods.notenoughluck.common.item.impl.TungShingItem;
import org.auioc.mods.notenoughluck.common.network.NELPacketHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class UnseiUtils {

    public static int getUnseiValue(long seed, int day) {
        return new Random(seed + day).nextInt(37);
    }

    public static int getUnseiValue(ServerLevel level) {
        return getUnseiValue(level.getSeed(), getDay(level.getDayTime()));
    }

    public static int getDay(long dayTime) {
        return (((int) (dayTime % 2147483647L)) - MCTimeUtils.ticksAtMidnight + MCTimeUtils.ticksPerDay) / MCTimeUtils.ticksPerDay;
    }

    public static Pair<int[], int[]> getThreeDaysUnsei(long seed, int today) {
        return Pair.of(
            new int[] {
                today - 1,
                today,
                today + 1
            },
            new int[] {
                UnseiUtils.getUnseiValue(seed, today - 1),
                UnseiUtils.getUnseiValue(seed, today),
                UnseiUtils.getUnseiValue(seed, today + 1)
            }
        );
    }

    public static void sendUpdateTungShingPacket(ServerPlayer player, long seed, int today, int cooldown) {
        if (cooldown > 0) {
            TungShingItem.addCooldown(player, cooldown);
        } else {
            TungShingItem.removeCooldown(player);
        }

        Pair<int[], int[]> unsei = UnseiUtils.getThreeDaysUnsei(seed, today);
        NELPacketHandler.sendToClient(
            ((ServerPlayer) player),
            new UpdateTungShingPacket(unsei.getLeft(), unsei.getRight())
        );
    }

    public static void sendUpdateTungShingPacket(ServerPlayer player, long seed, int today) {
        sendUpdateTungShingPacket(player, seed, today, TungShingItem.COOLDOWN);
    }

}
