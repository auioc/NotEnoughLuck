package org.auioc.mods.notenoughluck.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mods.arnicalib.utils.java.Validate;
import org.auioc.mods.notenoughluck.client.network.UpdateTungShingPacket;
import org.auioc.mods.notenoughluck.common.item.impl.TungShingItem;
import org.auioc.mods.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mods.notenoughluck.common.unsei.UnseiFortune;
import org.auioc.mods.notenoughluck.common.unsei.UnseiPrefix;
import org.auioc.mods.notenoughluck.server.unsei.ServerUnseiCache;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class UnseiUtils {

    /**
     * @return k, {@code ⌊|seed|^(0.2 + |sin(day)|)⌉ ≡ k (mod 37)}
     */
    public static int calcUnseiValue(long seed, int day) {
        long n = Math.round(Math.pow(Math.abs((double) seed), 0.2D + Math.abs(Math.sin((double) day)))) % 37L;
        for (int k = 0; k < 37; k++) {
            if (k % 37 == n) {
                return k;
            }
        }
        throw new RuntimeException();
    }

    public static int getUnseiValue(long seed, int day) {
        int cachedUnsei = ServerUnseiCache.get(day);
        if (cachedUnsei >= 0) {
            return cachedUnsei;
        } else {
            int newUnsei = calcUnseiValue(seed, day);
            ServerUnseiCache.set(day, newUnsei);
            return newUnsei;
        }
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

    public static void sendUpdateTungShingPacket(ServerPlayer player, long seed, int today, int cooldown, boolean classic) {
        if (cooldown > 0) {
            TungShingItem.addCooldown(player, cooldown);
        } else {
            TungShingItem.removeCooldown(player);
        }

        Pair<int[], int[]> unsei = UnseiUtils.getThreeDaysUnsei(seed, today);
        NELPacketHandler.sendToClient(
            ((ServerPlayer) player),
            new UpdateTungShingPacket(unsei.getLeft(), unsei.getRight(), classic)
        );
    }

    public static void sendUpdateTungShingPacket(ServerPlayer player, long seed, int today) {
        sendUpdateTungShingPacket(player, seed, today, TungShingItem.COOLDOWN, false);
    }

    public static void sendUpdateTungShingPacket(ServerPlayer player, long seed, int today, boolean classic) {
        sendUpdateTungShingPacket(player, seed, today, classic ? 0 : TungShingItem.COOLDOWN, classic);
    }

    public static Pair<UnseiPrefix, UnseiFortune> convertToUnseiPair(int unsei) {
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

    public static CompoundTag serializeNBT(Pair<UnseiPrefix, UnseiFortune> unseiPair) {
        var nbt = new CompoundTag();
        nbt.put(
            "Unsei",
            unseiPair.getLeft().serializeNBT().merge(
                unseiPair.getRight().serializeNBT()
            )
        );
        return nbt;
    }

    public static Pair<UnseiPrefix, UnseiFortune> deserializeNBT(CompoundTag nbt) {
        var pairNBT = nbt.getCompound("Unsei");
        return Pair.of(UnseiPrefix.deserializeNBT(pairNBT), UnseiFortune.deserializeNBT(pairNBT));
    }

}
