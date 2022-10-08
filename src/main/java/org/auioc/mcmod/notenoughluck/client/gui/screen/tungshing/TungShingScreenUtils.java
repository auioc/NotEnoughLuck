package org.auioc.mcmod.notenoughluck.client.gui.screen.tungshing;

import java.util.function.Predicate;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.notenoughluck.client.unsei.ClientUnseiCache;
import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import org.auioc.mcmod.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mcmod.notenoughluck.server.network.RequestUpdateTungShingPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungShingScreenUtils {

    public static void open() {
        Minecraft.getInstance().setScreen(new TungShingScreen());
    }

    public static void openClassic() {
        Minecraft.getInstance().setScreen(new ClassicTungShingScreen());
    }

    public static ITungShingScreen open(boolean reuse, boolean classic) {
        var mc = Minecraft.getInstance();
        if (reuse) {
            if (classic && mc.screen instanceof ClassicTungShingScreen) {
                return (ClassicTungShingScreen) mc.screen;
            }
            if (mc.screen instanceof TungShingScreen) {
                return (TungShingScreen) mc.screen;
            }
        }

        if (classic) {
            openClassic();
        } else {
            open();
        }

        return (ITungShingScreen) mc.screen;
    }

    public static ClassicTungShingScreen openClassic(boolean reuse) {
        var mc = Minecraft.getInstance();
        if (reuse && mc.screen instanceof ClassicTungShingScreen) {
            return (ClassicTungShingScreen) mc.screen;
        }
        mc.setScreen(new TungShingScreen());
        return (ClassicTungShingScreen) mc.screen;
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


    protected static boolean isOnCooldown() {
        var mc = Minecraft.getInstance();
        return mc.player.getCooldowns().isOnCooldown(NELItems.TUNG_SHING_ITEM.get());
    }


    protected static void requestUpdate(ITungShingScreen screen, int day) {
        if (screen.isClassic()) {
            NELPacketHandler.sendToServer(new RequestUpdateTungShingPacket(day, true));
            return;
        }

        if (isOnCooldown()) {
            return;
        }

        int[] dayArray = new int[] {day - 1, day, day + 1};
        int[] unseiArray = new int[3];

        boolean hasCached = true;
        for (int i = 0; i < 3; i++) {
            Integer cachedUnsei = ClientUnseiCache.get(dayArray[i]);
            if (cachedUnsei == null) {
                hasCached = false;
                break;
            }
            unseiArray[i] = cachedUnsei;
        }

        if (hasCached) {
            screen.updateUnsei(dayArray, unseiArray);
        } else {
            NELPacketHandler.sendToServer(new RequestUpdateTungShingPacket(day, false));
        }
    }


    public static void showCooldownMessage() {
        var mc = Minecraft.getInstance();
        mc.gui.setOverlayMessage(TungShingScreen.i18n("cooldown").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
    }


    protected static enum UnseiMarkTexture {
        DAI(180, 0, 220, 0), CHUU(180, 40, 220, 20), SHOU(180, 80, 220, 40), SUE(180, 120, 220, 60), //
        HEI_A(0, 180, 160, 180), HEI_B(40, 180, 160, 200), //
        KICHI(80, 180, 180, 160), KYOU(120, 180, 200, 160);

        public final Pair<Integer, Integer> bigUV;
        public final Pair<Integer, Integer> smallUV;

        private UnseiMarkTexture(int bigU, int bigV, int smallU, int smallV) {
            this.bigUV = Pair.of(bigU, bigV);
            this.smallUV = Pair.of(smallU, smallV);
        }
    }

    private static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> getUnseiMarkUV(UnseiMarkTexture unseiPrefix, UnseiMarkTexture unseiFortune, boolean small) {
        return Pair.of(
            small ? unseiPrefix.smallUV : unseiPrefix.bigUV,
            small ? unseiFortune.smallUV : unseiFortune.bigUV
        );
    }


    /**
     * @return {@code <PrefixU, PrefixV>, <FortuneU, FortuneV>}
     * @see org.auioc.mcmod.notenoughluck.utils.UnseiUtils#convertToUnseiPair(int)
     */
    protected static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> getUnseiMarkUV(int unsei, boolean small) {
        Validate.isInCloseInterval(-4, 4, unsei);
        if (unsei == 4) {
            return getUnseiMarkUV(UnseiMarkTexture.DAI, UnseiMarkTexture.KICHI, small);
        }
        if (unsei == 3) {
            return getUnseiMarkUV(UnseiMarkTexture.CHUU, UnseiMarkTexture.KICHI, small);
        }
        if (unsei == 2) {
            return getUnseiMarkUV(UnseiMarkTexture.SHOU, UnseiMarkTexture.KICHI, small);
        }
        if (unsei == 1) {
            return getUnseiMarkUV(UnseiMarkTexture.SUE, UnseiMarkTexture.KICHI, small);
        }
        if (unsei == 0) {
            return getUnseiMarkUV(UnseiMarkTexture.HEI_A, UnseiMarkTexture.HEI_B, small);
        }
        if (unsei == -1) {
            return getUnseiMarkUV(UnseiMarkTexture.SUE, UnseiMarkTexture.KYOU, small);
        }
        if (unsei == -2) {
            return getUnseiMarkUV(UnseiMarkTexture.SHOU, UnseiMarkTexture.KYOU, small);
        }
        if (unsei == -3) {
            return getUnseiMarkUV(UnseiMarkTexture.CHUU, UnseiMarkTexture.KYOU, small);
        }
        if (unsei == -4) {
            return getUnseiMarkUV(UnseiMarkTexture.DAI, UnseiMarkTexture.KYOU, small);
        }
        return getUnseiMarkUV(UnseiMarkTexture.HEI_A, UnseiMarkTexture.HEI_B, small);
    }

}
