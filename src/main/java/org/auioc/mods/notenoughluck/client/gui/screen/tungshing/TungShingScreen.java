package org.auioc.mods.notenoughluck.client.gui.screen.tungshing;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mods.arnicalib.utils.java.Validate;
import org.auioc.mods.notenoughluck.Reference;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungShingScreen extends Screen {

    private static final int DIV_SIZE = 180;

    protected static final ResourceLocation TEXTURE = Reference.ResourceId("textures/gui/tung_shing.png");
    protected static final int TEXTURE_SIZE = 256;

    private static final int EDITBOX_WIDTH = 20;
    private static final int EDITBOX_HEIGHT = 8;
    private static final int EDITBOX_X_OFFSET = 80;
    private static final int EDITBOX_Y_OFFSET = 92;

    private static final int BUTTON_X_OFFSET = 71;
    private static final int BUTTON_Y_OFFSET = 104;

    private static final int BIG_UNSEI_TEXTURE_SIZE = 40;
    private static final int SMALL_UNSEI_TEXTURE_SIZE = 20;
    private static final int SIDE_UNSEI_Y_OFFSET = 125;
    private static final int RIGHT_UNSEI_X_OFFSET = 150;
    private static final int LEFT_UNSEI_X_OFFSET = 10;
    private static final int CENTER_UNSEI_X_OFFSET = 50;
    private static final int CENTER_UNSEI_Y_OFFSET = 120;

    private EditBox editbox;
    private TungShingRequestButton button;

    private int[] dayArray;
    private int[] unseiArray;

    protected TungShingScreen() {
        super(TungShingScreenUtils.i18n("title"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        int divX = center(this.width, DIV_SIZE);
        int divY = center(this.height, DIV_SIZE);

        this.editbox = new EditBox(
            this.font,
            divX + EDITBOX_X_OFFSET, divY + EDITBOX_Y_OFFSET,
            EDITBOX_WIDTH, EDITBOX_HEIGHT,
            TungShingScreenUtils.i18n("editbox")
        );
        this.editbox.setFilter(TungShingScreenUtils.IS_INTEGER_STRING);
        this.addWidget(this.editbox);

        this.button = new TungShingRequestButton(
            divX + BUTTON_X_OFFSET, divY + BUTTON_Y_OFFSET,
            (button) -> {
                String day = this.editbox.getValue();
                if (day.isEmpty()) {
                    return;
                }
                TungShingScreenUtils.requestUpdate(this, Integer.valueOf(day));
            }
        );
        this.addWidget(this.button);

        TungShingScreenUtils.requestUpdate(this, UnseiUtils.getDay(this.minecraft.level.getDayTime()));

        super.init();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        int divX = center(this.width, DIV_SIZE);
        int divY = center(this.height, DIV_SIZE);

        this.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        blit(poseStack, divX, divY, DIV_SIZE);

        if (unseiArray == null || dayArray == null) {
            super.render(poseStack, mouseX, mouseY, partialTicks);
            return;
        }

        for (int i = 0, l = this.unseiArray.length; i < l; i++) {
            boolean small = i != 1;

            //        PrefixU  PrefixV        FortuneU FortuneV
            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> uv = TungShingScreenUtils.getUnseiTextureUV(this.unseiArray[i], small);

            int size = small ? SMALL_UNSEI_TEXTURE_SIZE : BIG_UNSEI_TEXTURE_SIZE;
            int offsetX = i == 0 ? LEFT_UNSEI_X_OFFSET : (i == 1 ? CENTER_UNSEI_X_OFFSET : RIGHT_UNSEI_X_OFFSET);
            int offsetY = small ? SIDE_UNSEI_Y_OFFSET : CENTER_UNSEI_Y_OFFSET;

            blit(
                poseStack,
                divX + offsetX,
                divY + offsetY,
                uv.getLeft().getLeft(), uv.getLeft().getRight(),
                size
            );

            blit(
                poseStack,
                divX + offsetX + (small ? 0 : BIG_UNSEI_TEXTURE_SIZE),
                divY + offsetY + (small ? SMALL_UNSEI_TEXTURE_SIZE : 0),
                uv.getRight().getLeft(), uv.getRight().getRight(),
                size
            );
        }

        if (TungShingScreenUtils.isOnCooldown()) {
            this.button.active = false;
            this.editbox.setEditable(false);
        } else {
            this.button.active = true;
            this.editbox.setEditable(true);
        }

        this.editbox.render(poseStack, mouseX, mouseY, partialTicks);
        this.button.render(poseStack, mouseX, mouseY, partialTicks);

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }


    public void updateUnsei(int[] dayArray, int[] unseiArray) {
        Validate.isTrue(dayArray.length == 3);
        Validate.isTrue(unseiArray.length == 3);
        this.dayArray = dayArray;
        this.unseiArray = unseiArray;
        this.editbox.setValue("" + this.dayArray[1]);
    }

    private static int center(int screen, int b) {
        return (screen - b) / 2;
    }

    private static void blit(PoseStack poseStack, int x, int y, int size) {
        blit(poseStack, x, y, 0, 0, size);
    }

    private static void blit(PoseStack poseStack, int x, int y, int u, int v, int size) {
        // X, Y, U, V, W, H, TW, TH
        blit(poseStack, x, y, u, v, size, size, TEXTURE_SIZE, TEXTURE_SIZE);
    }


}
