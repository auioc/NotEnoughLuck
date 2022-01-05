package org.auioc.mods.notenoughluck;

import net.minecraft.resources.ResourceLocation;

public final class Reference {

    public static ResourceLocation ResourceId(String path) {
        return new ResourceLocation(NotEnoughLuck.MOD_ID, path);
    }

    public static String I18nKey(String path) {
        return NotEnoughLuck.MOD_ID + "." + path;
    }

}
