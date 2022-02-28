package org.auioc.mods.notenoughluck.common.unsei;

import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.Reference;
import net.minecraft.network.chat.Component;

public enum UnseiFortune {

    HEI("hei"), KICHI("kichi"), KYOU("kyou");

    public final String id;
    public final Component name;

    private UnseiFortune(String id) {
        this.id = id;
        this.name = TextUtils.I18nText(Reference.I18nKey("unsei.fortune." + id));
    }

}
