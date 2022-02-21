package org.auioc.mods.notenoughluck.client.unsei;

import java.util.HashMap;
import java.util.Map;

public class ClientUnseiCache {

    private static final Map<Integer, Integer> CACHE = new HashMap<Integer, Integer>();

    public static void clear() {
        CACHE.clear();
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
