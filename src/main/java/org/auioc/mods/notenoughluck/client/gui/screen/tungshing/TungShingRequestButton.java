package org.auioc.mods.notenoughluck.client.gui.screen.tungshing;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungShingRequestButton extends Button {

    private static final int WIDTH = 37;
    private static final int HEIGHT = 12;
    private static final int U = 0;
    private static final int V = 220;
    private static final int HOVERED_U = 0;
    private static final int HOVERED_V = 232;

    public TungShingRequestButton(int x, int y, OnPress onPress) {
        super(x, y, WIDTH, HEIGHT, TungShingScreen.i18n("button"), onPress);
    }

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        RenderSystem.setShaderTexture(0, TungShingScreen.TEXTURE);

        boolean flag = this.isActive() && this.isHoveredOrFocused();
        _blit(
            poseStack, this.x, this.y,
            flag ? HOVERED_U : U, flag ? HOVERED_V : V,
            WIDTH, HEIGHT
        );
    }

    private static void _blit(PoseStack poseStack, int x, int y, int u, int v, int w, int h) {
        blit(poseStack, x, y, u, v, w, h, TungShingScreen.TEXTURE_SIZE, TungShingScreen.TEXTURE_SIZE);
    }

}
