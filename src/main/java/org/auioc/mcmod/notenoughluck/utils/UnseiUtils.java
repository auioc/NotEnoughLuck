package org.auioc.mcmod.notenoughluck.utils;

import java.util.function.IntSupplier;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mcmod.arnicalib.utils.game.MCTimeUtils;
import org.auioc.mcmod.arnicalib.utils.java.Validate;
import org.auioc.mcmod.notenoughluck.client.network.UpdateTungShingPacket;
import org.auioc.mcmod.notenoughluck.common.item.impl.TungShingItem;
import org.auioc.mcmod.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mcmod.notenoughluck.common.unsei.UnseiFortune;
import org.auioc.mcmod.notenoughluck.common.unsei.UnseiPrefix;
import org.auioc.mcmod.notenoughluck.server.unsei.ServerUnseiCache;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.ServerLifecycleHooks;

public class UnseiUtils {

    public static final ServerLevel OVERWORLD = ServerLifecycleHooks.getCurrentServer().overworld();
    public static final long SEED = OVERWORLD.getSeed();
    public static final IntSupplier TODAY = () -> MCTimeUtils.getDay(OVERWORLD.dayTime());

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
        Integer cachedUnsei = ServerUnseiCache.get(day);
        if (cachedUnsei != null) {
            return cachedUnsei;
        } else {
            int newUnsei = requantifyUnseiValue(calcUnseiValue(seed, day));
            ServerUnseiCache.set(day, newUnsei);
            return newUnsei;
        }
    }

    public static int getUnseiValue(int day) {
        return getUnseiValue(SEED, day);
    }

    public static int getUnseiValue() {
        return getUnseiValue(SEED, TODAY.getAsInt());
    }

    public static Pair<UnseiPrefix, UnseiFortune> getUnseiPair() {
        return convertToUnseiPair(getUnseiValue());
    }

    public static int requantifyUnseiValue(int unsei) {
        Validate.isInCloseInterval(0, 36, unsei);
        if (unsei < 1) {
            return 4;
        }
        if (unsei < 3) {
            return 3;
        }
        if (unsei < 7) {
            return 2;
        }
        if (unsei < 15) {
            return 1;
        }
        if (unsei < 25) {
            return 0;
        }
        if (unsei < 31) {
            return -1;
        }
        if (unsei < 34) {
            return -2;
        }
        if (unsei < 36) {
            return -3;
        }
        return -4;

        //  1    2       4                8                        10                          6              3         2      1
        // (0) (1 2) (3 4 5 6) (7 8 9 10 11 12 13 14) (15 16 17 18 19 20 21 22 23 24) (25 26 27 28 29 30) (31 32 33) (34 35) (36)
        //  4    3       2                1                         0                         -1              -2       -2     -4
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

    public static Pair<int[], int[]> getThreeDaysUnsei(int today) {
        return getThreeDaysUnsei(SEED, today);
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

    public static void sendUpdateTungShingPacket(ServerPlayer player, int today, boolean classic) {
        sendUpdateTungShingPacket(player, SEED, today, classic ? 0 : TungShingItem.getCooldown(), classic);
    }

    public static Pair<UnseiPrefix, UnseiFortune> convertToUnseiPair(int unsei) {
        Validate.isInCloseInterval(-4, 4, unsei);
        return Pair.of(
            UnseiPrefix.valueOf(Math.abs(unsei)),
            UnseiFortune.valueOf(unsei == 0 ? 0 : (unsei > 0 ? 1 : -1))
        );
    }

    public static void serializeNBT(Pair<UnseiPrefix, UnseiFortune> unseiPair, CompoundTag nbt) {
        nbt.putInt("Unsei", unseiPair.getLeft().id * unseiPair.getRight().id);
    }

    public static CompoundTag serializeNBT(Pair<UnseiPrefix, UnseiFortune> unseiPair) {
        var nbt = new CompoundTag();
        serializeNBT(unseiPair, nbt);
        return nbt;
    }

    public static Pair<UnseiPrefix, UnseiFortune> deserializeNBT(CompoundTag nbt) {
        return convertToUnseiPair(nbt.getInt("Unsei"));
    }

}
