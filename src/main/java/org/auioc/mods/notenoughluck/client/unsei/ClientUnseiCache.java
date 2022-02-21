package org.auioc.mods.notenoughluck.client.unsei;

import static org.auioc.mods.notenoughluck.NotEnoughLuck.LOGGER;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;

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

    public static int get(int day) {
        Integer r = CACHE.get(day);
        if (r == null) {
            return -1;
        }
        return r;
    }

}
