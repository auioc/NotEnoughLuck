package org.auioc.mods.notenoughluck.common.unsei;

import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.Reference;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public enum UnseiFortune {

    HEI("hei"), KICHI("kichi"), KYOU("kyou");

    public final String id;
    public final Component name;

    private UnseiFortune(String id) {
        this.id = id;
        this.name = TextUtils.I18nText(Reference.I18nKey("unsei.fortune." + id));
    }

    public CompoundTag serializeNBT() {
        var nbt = new CompoundTag();
        nbt.putString("Fortune", this.id);
        return nbt;
    }

    public static UnseiFortune deserializeNBT(CompoundTag nbt) {
        return valueOf(nbt.getString("Fortune"));
    }

}
