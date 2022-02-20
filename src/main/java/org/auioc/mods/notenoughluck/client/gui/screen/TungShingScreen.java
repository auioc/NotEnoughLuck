package org.auioc.mods.notenoughluck.client.gui.screen;

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

    protected TungShingScreen() {
        super(TextUtils.I18nText(Reference.I18nKey("gui.tung_shing.title")));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        // X, Y, U, V, W, H, TW, TH
        blit(poseStack, center(this.width, BG_WIDTH), center(this.height, BG_HEIGHT), 0, 0, BG_WIDTH, BG_HEIGHT, BG_TEXTURE_SIZE, BG_TEXTURE_SIZE);
        drawCenteredString(poseStack, this.font, this.title, center(this.width), center(this.height, BG_HEIGHT) - 16, 0xFFFFFF);
        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    private static int center(int size) {
        return size / 2;
    }

    private static int center(int screen, int b) {
        return (screen - b) / 2;
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new TungShingScreen());
    }

}
