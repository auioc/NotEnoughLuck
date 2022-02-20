package org.auioc.mods.notenoughluck.utils;

import java.util.Random;
import org.auioc.mods.arnicalib.utils.game.MCTimeUtils;
import net.minecraft.server.level.ServerLevel;

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

}
