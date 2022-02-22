package org.auioc.mods.notenoughluck.common.unsei;

import javax.annotation.Nullable;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.Reference;
import net.minecraft.network.chat.Component;

public enum UnseiPrefix {

    DAI("dai"), CHUU("chuu"), SHOU("shou"), SUE("sue"), HEI(null);

    public final Component name;

    private UnseiPrefix(@Nullable String name) {
        if (name != null) {
            this.name = TextUtils.I18nText(Reference.I18nKey("unsei.mark." + name));
        } else {
            this.name = TextUtils.EmptyText();
        }
    }

}
