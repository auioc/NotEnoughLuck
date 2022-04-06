package org.auioc.mods.notenoughluck.common.unsei;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.auioc.mcmod.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import net.minecraft.network.chat.Component;

public enum UnseiPrefix {

    DAI(4, "dai"), CHUU(3, "chuu"), SHOU(2, "shou"), SUE(1, "sue"), HEI(0, "hei");

    public final int id;
    public final Component name;

    private UnseiPrefix(int id, String name) {
        this.id = id;
        this.name = TextUtils.I18nText(NotEnoughLuck.i18n("unsei.prefix." + name));
    }

    private static final Map<Integer, UnseiPrefix> ID_MAP = Map.copyOf(new HashMap<Integer, UnseiPrefix>() {
        {
            for (var e : EnumSet.allOf(UnseiPrefix.class)) {
                put(e.id, e);
            }
        }
    });

    public static UnseiPrefix valueOf(int index) {
        return ID_MAP.get(index);
    }

}
