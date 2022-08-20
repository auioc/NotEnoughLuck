package org.auioc.mcmod.notenoughluck.server.unsei;

import java.util.function.IntSupplier;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mcmod.arnicalib.utils.game.MCTimeUtils;
import org.auioc.mcmod.notenoughluck.client.network.UpdateTungShingPacket;
import org.auioc.mcmod.notenoughluck.common.item.impl.TungShingItem;
import org.auioc.mcmod.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mcmod.notenoughluck.common.unsei.UnseiFortune;
import org.auioc.mcmod.notenoughluck.common.unsei.UnseiPrefix;
import org.auioc.mcmod.notenoughluck.utils.UnseiUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.ServerLifecycleHooks;

public class ServerUnseiUtils {

    public static final ServerLevel OVERWORLD = ServerLifecycleHooks.getCurrentServer().overworld();
    public static final long SEED = OVERWORLD.getSeed();
    public static final IntSupplier TODAY = () -> MCTimeUtils.getDay(OVERWORLD.dayTime());

    public static int getUnseiValue(long seed, int day) {
        Integer cachedUnsei = ServerUnseiCache.get(day);
        if (cachedUnsei != null) {
            return cachedUnsei;
        } else {
            int newUnsei = UnseiUtils.requantifyUnseiValue(UnseiUtils.calcUnseiValue(seed, day));
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
        return UnseiUtils.convertToUnseiPair(getUnseiValue());
    }

    public static Pair<int[], int[]> getThreeDaysUnsei(long seed, int today) {
        return Pair.of(
            new int[] {
                today - 1,
                today,
                today + 1
            },
            new int[] {
                getUnseiValue(seed, today - 1),
                getUnseiValue(seed, today),
                getUnseiValue(seed, today + 1)
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

        Pair<int[], int[]> unsei = getThreeDaysUnsei(seed, today);
        NELPacketHandler.sendToClient(
            ((ServerPlayer) player),
            new UpdateTungShingPacket(unsei.getLeft(), unsei.getRight(), classic)
        );
    }

    public static void sendUpdateTungShingPacket(ServerPlayer player, int today, boolean classic) {
        sendUpdateTungShingPacket(player, SEED, today, classic ? 0 : TungShingItem.getCooldown(), classic);
    }

}
