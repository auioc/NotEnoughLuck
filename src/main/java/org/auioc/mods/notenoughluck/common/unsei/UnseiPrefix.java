package org.auioc.mods.notenoughluck.common.unsei;

import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.Reference;
import net.minecraft.network.chat.Component;

public enum UnseiPrefix {

    DAI("dai"), CHUU("chuu"), SHOU("shou"), SUE("sue"), HEI("hei");

    public final String id;
    public final Component name;

    private UnseiPrefix(String id) {
        this.id = id;
        this.name = TextUtils.I18nText(Reference.I18nKey("unsei.prefix." + id));
    }

}
