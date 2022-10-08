package org.auioc.mcmod.notenoughluck.server.unsei;

import static org.auioc.mcmod.notenoughluck.NotEnoughLuck.LOGGER;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.cache.PlainLoadingCache;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.notenoughluck.utils.UnseiUtils;

public class ServerUnseiCache {

    private static final Marker MARKER = LogUtil.getMarker(ServerUnseiCache.class);

    public static final PlainLoadingCache<Integer, Integer> CACHE =
        new PlainLoadingCache<>((day) -> UnseiUtils.requantifyUnseiValue(UnseiUtils.calcUnseiValue(ServerUnseiUtils.SEED, day)));

    public static void clear() {
        CACHE.clear();
        LOGGER.info(MARKER, "Server unsei cache cleared");
    }

    public static int get(int day) {
        return CACHE.get(day);
    }

}
