package org.auioc.mcmod.notenoughluck.common.unsei;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.auioc.mcmod.arnicalib.utils.game.TextUtils;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import net.minecraft.network.chat.Component;

public enum UnseiFortune {

    HEI(0, "hei"), KICHI(1, "kichi"), KYOU(-1, "kyou");

    public final int id;
    public final Component name;

    private UnseiFortune(int id, String name) {
        this.id = id;
        this.name = TextUtils.I18nText(NotEnoughLuck.i18n("unsei.fortune." + name));
    }

    private static final Map<Integer, UnseiFortune> ID_MAP = Map.copyOf(new HashMap<Integer, UnseiFortune>() {
        {
            for (var e : EnumSet.allOf(UnseiFortune.class)) {
                put(e.id, e);
            }
        }
    });

    public static UnseiFortune valueOf(int index) {
        return ID_MAP.get(index);
    }

}
