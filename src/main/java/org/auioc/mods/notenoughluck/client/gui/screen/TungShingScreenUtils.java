package org.auioc.mods.notenoughluck.client.gui.screen;

import java.util.function.Predicate;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungShingScreenUtils {

    public static TungShingScreen open(boolean reuse) {
        Minecraft mc = Minecraft.getInstance();
        if (reuse && mc.screen instanceof TungShingScreen) {
            return (TungShingScreen) mc.screen;
        }
        mc.setScreen(new TungShingScreen());
        return (TungShingScreen) mc.screen;
    }

    protected static ResourceLocation texture(String key) {
        return Reference.ResourceId("textures/gui/tung_shing/" + key + ".png");
    }

    protected static Component i18n(String key) {
        return TextUtils.I18nText(Reference.I18nKey("gui.tung_shing." + key));
    }

    protected static final Predicate<String> IS_INTEGER_STRING = (string) -> {
        if (string.isEmpty()) {
            return true;
        }
        try {
            int n = Integer.valueOf(string);
            return n >= 0 ? true : false;
        } catch (
            Exception e
        ) {
            return false;
        }
    };

}
