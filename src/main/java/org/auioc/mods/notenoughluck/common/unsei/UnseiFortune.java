package org.auioc.mods.notenoughluck.common.unsei;

import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.Reference;
import net.minecraft.network.chat.Component;

public enum UnseiFortune {

    HEI("hei"), KICHI("kichi"), KYOU("kyou");

    public final Component name;

    private UnseiFortune(String name) {
        this.name = TextUtils.I18nText(Reference.I18nKey("unsei.mark." + name));
    }

}
