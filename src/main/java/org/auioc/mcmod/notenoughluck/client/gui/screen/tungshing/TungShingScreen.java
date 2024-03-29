package org.auioc.mcmod.notenoughluck.client.gui.screen.tungshing;

import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.game.gui.screen.HScreen;
import org.auioc.mcmod.arnicalib.game.world.MCTimeUtils;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungShingScreen extends HScreen implements ITungShingScreen {

    private static final int DIV_SIZE = 180;

    protected static final ResourceLocation TEXTURE = NotEnoughLuck.id("textures/gui/tung_shing.png");
    protected static final int TEXTURE_SIZE = 256;

    private static final int EDITBOX_WIDTH = 20;
    private static final int EDITBOX_HEIGHT = 8;
    private static final int EDITBOX_X_OFFSET = 80;
    private static final int EDITBOX_Y_OFFSET = 92;

    private static final int BUTTON_X_OFFSET = 71;
    private static final int BUTTON_Y_OFFSET = 104;

    private static final int COOLDOWN_MESSAGE_X_OFFSET = DIV_SIZE / 2;
    private static final int COOLDOWN_MESSAGE_Y_OFFSET = 190;
    private static final int COOLDOWN_MESSAGE_DISPLAY_DURATION = 2 * 20;

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

    private int cooldownMessageTimer;

    protected TungShingScreen() {
        super(i18n("title"));
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
            i18n("editbox")
        );
        this.editbox.setFilter(TungShingScreenUtils.IS_INTEGER_STRING);
        this.addWidget(this.editbox);

        this.button = new TungShingRequestButton(
            divX + BUTTON_X_OFFSET, divY + BUTTON_Y_OFFSET,
            (button) -> {
                if (TungShingScreenUtils.isOnCooldown()) {
                    this.cooldownMessageTimer = COOLDOWN_MESSAGE_DISPLAY_DURATION;
                    return;
                }
                String day = this.editbox.getValue();
                if (!day.isEmpty()) {
                    TungShingScreenUtils.requestUpdate(this, Integer.valueOf(day));
                }
            }
        );
        this.addWidget(this.button);

        TungShingScreenUtils.requestUpdate(this, MCTimeUtils.getDay(this.minecraft.level.getDayTime()));

        super.init();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        int divX = center(this.width, DIV_SIZE);
        int divY = center(this.height, DIV_SIZE);

        this.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        blitSquare(poseStack, divX, divY, DIV_SIZE, TEXTURE_SIZE);

        if (unseiArray == null || dayArray == null) {
            super.render(poseStack, mouseX, mouseY, partialTicks);
            return;
        }

        for (int i = 0, l = this.unseiArray.length; i < l; i++) {
            boolean small = i != 1;

            //        PrefixU  PrefixV        FortuneU FortuneV
            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> uv = TungShingScreenUtils.getUnseiMarkUV(this.unseiArray[i], small);

            int size = small ? SMALL_UNSEI_TEXTURE_SIZE : BIG_UNSEI_TEXTURE_SIZE;
            int offsetX = i == 0 ? LEFT_UNSEI_X_OFFSET : (i == 1 ? CENTER_UNSEI_X_OFFSET : RIGHT_UNSEI_X_OFFSET);
            int offsetY = small ? SIDE_UNSEI_Y_OFFSET : CENTER_UNSEI_Y_OFFSET;

            blitSquare(
                poseStack,
                divX + offsetX, divY + offsetY,
                uv.getLeft().getLeft(), uv.getLeft().getRight(),
                size, TEXTURE_SIZE
            );

            blitSquare(
                poseStack,
                divX + offsetX + (small ? 0 : BIG_UNSEI_TEXTURE_SIZE), divY + offsetY + (small ? SMALL_UNSEI_TEXTURE_SIZE : 0),
                uv.getRight().getLeft(), uv.getRight().getRight(),
                size, TEXTURE_SIZE
            );
        }

        if (TungShingScreenUtils.isOnCooldown()) {
            if (this.cooldownMessageTimer > 0) {
                this.cooldownMessageTimer--;
                this.button.active = false;
                drawCenteredString(
                    poseStack, this.font,
                    i18n("cooldown").withStyle(ChatFormatting.RED, ChatFormatting.BOLD),
                    divX + COOLDOWN_MESSAGE_X_OFFSET, divY + COOLDOWN_MESSAGE_Y_OFFSET,
                    0x000000
                );
            } else {
                this.button.active = true;
            }
            this.editbox.setEditable(false);
        } else {
            this.button.active = true;
            this.editbox.setEditable(true);
        }

        this.editbox.render(poseStack, mouseX, mouseY, partialTicks);
        this.button.render(poseStack, mouseX, mouseY, partialTicks);

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }


    @Override
    public void updateUnsei(int[] dayArray, int[] unseiArray) {
        Validate.isTrue(dayArray.length == 3);
        Validate.isTrue(unseiArray.length == 3);
        this.dayArray = dayArray;
        this.unseiArray = unseiArray;
        this.editbox.setValue("" + this.dayArray[1]);
    }

    @Override
    public boolean isClassic() {
        return false;
    }

    protected static TranslatableComponent i18n(String key) {
        return TextUtils.translatable(NotEnoughLuck.i18n("gui.tung_shing." + key));
    }

}
