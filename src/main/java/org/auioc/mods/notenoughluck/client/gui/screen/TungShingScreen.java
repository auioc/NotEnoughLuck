package org.auioc.mods.notenoughluck.client.gui.screen;

import java.util.HashMap;
import java.util.Map;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.notenoughluck.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungShingScreen extends Screen {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("minecraft:textures/gui/demo_background.png");
    private static final int BG_WIDTH = 248;
    private static final int BG_HEIGHT = 166;
    private static final int BG_TEXTURE_SIZE = 256;
    private static final int TITLE_Y_OFFSET = -16;
    private static final int TITLE_COLOR = 0xFFFFFF;
    private static final int UNSEI_LINE_X_OFFSET = 16;
    private static final int UNSEI_LINE_Y_ADDEND = 16;
    private static final int UNSEI_LINE_COLOR = 0xFFFFFF;

    private final Map<Integer, Integer> unseiMap = new HashMap<Integer, Integer>();

    protected TungShingScreen() {
        super(TextUtils.I18nText(Reference.I18nKey("gui.tung_shing.title")));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        // X, Y, U, V, W, H, TW, TH
        blit(poseStack, center(this.width, BG_WIDTH), center(this.height, BG_HEIGHT), 0, 0, BG_WIDTH, BG_HEIGHT, BG_TEXTURE_SIZE, BG_TEXTURE_SIZE);

        drawCenteredString(poseStack, this.font, this.title, center(this.width), center(this.height, BG_HEIGHT) + TITLE_Y_OFFSET, TITLE_COLOR);


        int unseiLineX = center(this.width, BG_WIDTH) + UNSEI_LINE_X_OFFSET;
        int unseiLineY = center(this.height, BG_HEIGHT);
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : this.unseiMap.entrySet()) {
            int day = entry.getKey();
            drawString(poseStack, this.font, "第 " + day + " 天: " + entry.getValue(), unseiLineX, unseiLineY + UNSEI_LINE_Y_ADDEND * (i + 1), UNSEI_LINE_COLOR);
            i++;
        }

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    public void updateUnsei(Map<Integer, Integer> newUnseiMap) {
        this.unseiMap.clear();
        this.unseiMap.putAll(newUnseiMap);
    }

    private static int center(int size) {
        return size / 2;
    }

    private static int center(int screen, int b) {
        return (screen - b) / 2;
    }


    public static TungShingScreen open(boolean reuse) {
        Minecraft mc = Minecraft.getInstance();
        if (reuse && mc.screen instanceof TungShingScreen) {
            return (TungShingScreen) mc.screen;
        }
        mc.setScreen(new TungShingScreen());
        return (TungShingScreen) mc.screen;
    }

}
