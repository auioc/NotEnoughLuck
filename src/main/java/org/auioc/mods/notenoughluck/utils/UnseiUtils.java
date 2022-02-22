package org.auioc.mods.notenoughluck.utils;

import java.util.Random;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mods.arnicalib.utils.game.MCTimeUtils;
import org.auioc.mods.arnicalib.utils.java.Validate;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingPacket;
import org.auioc.mods.notenoughluck.common.item.impl.TungShingItem;
import org.auioc.mods.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mods.notenoughluck.common.unsei.UnseiFortune;
import org.auioc.mods.notenoughluck.common.unsei.UnseiPrefix;
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

    public static Pair<UnseiPrefix, UnseiFortune> getUnseiPair(int unsei) {
        Validate.isInCloseInterval(0, 36, unsei);
        if (unsei < 1) {
            return Pair.of(UnseiPrefix.DAI, UnseiFortune.KICHI);
        }
        if (unsei < 3) {
            return Pair.of(UnseiPrefix.CHUU, UnseiFortune.KICHI);
        }
        if (unsei < 7) {
            return Pair.of(UnseiPrefix.SHOU, UnseiFortune.KICHI);
        }
        if (unsei < 15) {
            return Pair.of(UnseiPrefix.SUE, UnseiFortune.KICHI);
        }
        if (unsei < 25) {
            return Pair.of(UnseiPrefix.HEI, UnseiFortune.HEI);
        }
        if (unsei < 31) {
            return Pair.of(UnseiPrefix.SUE, UnseiFortune.KYOU);
        }
        if (unsei < 34) {
            return Pair.of(UnseiPrefix.SHOU, UnseiFortune.KYOU);
        }
        if (unsei < 36) {
            return Pair.of(UnseiPrefix.CHUU, UnseiFortune.KYOU);
        }
        return Pair.of(UnseiPrefix.DAI, UnseiFortune.KYOU);

        // 1   2     4         8                      10                              6                   3          2       1
        // (0) (1 2) (3 4 5 6) (7 8 9 10 11 12 13 14) (15 16 17 18 19 20 21 22 23 24) (25 26 27 28 29 30) (31 32 33) (34 35) (36)
    }

}
