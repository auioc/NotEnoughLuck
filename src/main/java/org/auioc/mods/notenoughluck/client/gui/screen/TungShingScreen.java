package org.auioc.mods.notenoughluck.client.gui.screen;

import java.util.HashMap;
import java.util.Map;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.auioc.mods.notenoughluck.common.network.PacketHandler;
import org.auioc.mods.notenoughluck.server.network.RequestUpdateTungShingPacket;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungShingScreen extends Screen {

    private static final ResourceLocation BACKGROUND_TEXTURE = TungShingScreenUtils.texture("background");
    private static final int BG_WIDTH = 180;
    private static final int BG_HEIGHT = 180;
    private static final int BG_TEXTURE_SIZE = 180;
    private static final int TITLE_Y_OFFSET = -16;
    private static final int TITLE_COLOR = 0xFFFFFF;
    private static final int UNSEI_LINE_X_OFFSET = 16;
    private static final int UNSEI_LINE_Y_ADDEND = 16;
    private static final int UNSEI_LINE_COLOR = 0xFFFFFF;
    private static final int EDITBOX_WIDTH = 140;
    private static final int EDITBOX_HEIGHT = 20;
    private static final int EDITBOX_X_OFFSET = 20;
    private static final int EDITBOX_Y_OFFSET = 130;
    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_X_OFFSET = 170;
    private static final int BUTTON_Y_OFFSET = 130;


    private EditBox editbox;
    private Button button;

    private final Map<Integer, Integer> unseiMap = new HashMap<Integer, Integer>();

    protected TungShingScreen() {
        super(TungShingScreenUtils.i18n("title"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        int divX = center(this.width, BG_WIDTH);
        int divY = center(this.height, BG_HEIGHT);

        this.editbox = new EditBox(
            this.font,
            divX + EDITBOX_X_OFFSET, divY + EDITBOX_Y_OFFSET,
            EDITBOX_WIDTH, EDITBOX_HEIGHT,
            TungShingScreenUtils.i18n("editbox")
        );
        this.editbox.setFilter(TungShingScreenUtils.IS_INTEGER_STRING);
        this.addWidget(this.editbox);

        this.button = new Button(
            divX + BUTTON_X_OFFSET, divY + BUTTON_Y_OFFSET,
            BUTTON_WIDTH, BUTTON_HEIGHT,
            TungShingScreenUtils.i18n("button"),
            (button) -> {
                String day = this.editbox.getValue();
                if (!day.isEmpty()) {
                    PacketHandler.sendToServer(new RequestUpdateTungShingPacket(this.minecraft.player.getUUID(), Integer.valueOf(day)));
                }
            }
        );
        this.addWidget(this.button);

        super.init();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        int divX = center(this.width, BG_WIDTH);
        int divY = center(this.height, BG_HEIGHT);

        this.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        // X, Y, U, V, W, H, TW, TH
        blit(poseStack, divX, divY, 0, 0, BG_WIDTH, BG_HEIGHT, BG_TEXTURE_SIZE, BG_TEXTURE_SIZE);

        drawCenteredString(poseStack, this.font, this.title, center(this.width), divY + TITLE_Y_OFFSET, TITLE_COLOR);

        int unseiLineX = divX + UNSEI_LINE_X_OFFSET;
        int unseiLineY = divY;
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : this.unseiMap.entrySet()) {
            int day = entry.getKey();
            drawString(poseStack, this.font, "第 " + day + " 天: " + entry.getValue(), unseiLineX, unseiLineY + UNSEI_LINE_Y_ADDEND * (i + 1), UNSEI_LINE_COLOR);
            i++;
        }

        this.editbox.render(poseStack, mouseX, mouseY, partialTicks);
        this.button.render(poseStack, mouseX, mouseY, partialTicks);

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

}
