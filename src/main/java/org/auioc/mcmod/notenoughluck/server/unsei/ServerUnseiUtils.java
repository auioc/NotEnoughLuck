package org.auioc.mcmod.notenoughluck.server.unsei;

import java.util.function.IntSupplier;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mcmod.arnicalib.game.world.MCTimeUtils;
import org.auioc.mcmod.notenoughluck.common.item.impl.TungShingItem;
import org.auioc.mcmod.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mcmod.notenoughluck.common.network.packet.client.UpdateTungShingPacket;
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

    public static int getUnseiValue(int day) {
        return ServerUnseiCache.get(day);
    }

    public static int getUnseiValue() {
        return getUnseiValue(TODAY.getAsInt());
    }

    public static Pair<UnseiPrefix, UnseiFortune> getUnseiPair() {
        return UnseiUtils.convertToUnseiPair(getUnseiValue());
    }

    public static Pair<int[], int[]> getThreeDaysUnsei(int today) {
        return Pair.of(
            new int[] {
                today - 1,
                today,
                today + 1
            },
            new int[] {
                getUnseiValue(today - 1),
                getUnseiValue(today),
                getUnseiValue(today + 1)
            }
        );
    }

    public static void sendUpdateTungShingPacket(ServerPlayer player, int today, int cooldown, boolean classic) {
        if (cooldown > 0) {
            TungShingItem.addCooldown(player, cooldown);
        } else {
            TungShingItem.removeCooldown(player);
        }

        Pair<int[], int[]> unsei = getThreeDaysUnsei(today);
        NELPacketHandler.sendToClient(
            ((ServerPlayer) player),
            new UpdateTungShingPacket(unsei.getLeft(), unsei.getRight(), classic)
        );
    }

    public static void sendUpdateTungShingPacket(ServerPlayer player, int today, boolean classic) {
        sendUpdateTungShingPacket(player, today, classic ? 0 : TungShingItem.getCooldown(), classic);
    }

}
