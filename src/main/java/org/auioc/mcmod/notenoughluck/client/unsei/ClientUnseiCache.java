package org.auioc.mcmod.notenoughluck.client.unsei;

import static org.auioc.mcmod.notenoughluck.NotEnoughLuck.LOGGER;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;

public class ClientUnseiCache {

    private static final Marker MARKER = LogUtil.getMarker(ClientUnseiCache.class);

    private static final Map<Integer, Integer> CACHE = new HashMap<Integer, Integer>();

    public static void clear() {
        CACHE.clear();
        LOGGER.info(MARKER, "Client unsei cache cleared");
    }

    public static void set(int day, int unsei) {
        CACHE.put(day, unsei);
    }

    public static Integer get(int day) {
        return CACHE.get(day);
    }

}
