package org.auioc.mcmod.notenoughluck.client.gui.screen.tungshing;

import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.game.gui.screen.SimpleScreen;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import org.auioc.mcmod.notenoughluck.common.unsei.UnseiFortune;
import org.auioc.mcmod.notenoughluck.utils.UnseiUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.TranslatableComponent;

public class ClassicTungShingScreen extends SimpleScreen implements ITungShingScreen {

    private static final int DIV_WIDTH = 248;
    private static final int DIV_HEIGHT = 166;

    private static final int UNSEI_LINE_X_OFFSET = 10;
    private static final int UNSEI_LINE_Y_OFFSET = 10;
    private static final int UNSEI_LINE_Y_ADDEND = 10;

    private static final int EDITBOX_WIDTH = 140;
    private static final int EDITBOX_HEIGHT = 20;
    private static final int EDITBOX_X_OFFSET = 20;
    private static final int EDITBOX_Y_OFFSET = 130;
    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_X_OFFSET = 168;
    private static final int BUTTON_Y_OFFSET = 130;

    private EditBox editbox;
    private Button button;

    private int[] dayArray;
    private int[] unseiArray;

    public ClassicTungShingScreen() {
        super(i18n("title"), DIV_WIDTH, DIV_HEIGHT);
    }

    @Override
    protected void subInit() {
        this.editbox = new EditBox(
            this.font,
            boxX1 + EDITBOX_X_OFFSET, boxY1 + EDITBOX_Y_OFFSET,
            EDITBOX_WIDTH, EDITBOX_HEIGHT,
            i18n("editbox")
        );
        this.editbox.setFilter(TungShingScreenUtils.IS_INTEGER_STRING);
        this.addWidget(this.editbox);

        this.button = new Button(
            boxX1 + BUTTON_X_OFFSET, boxY1 + BUTTON_Y_OFFSET,
            BUTTON_WIDTH, BUTTON_HEIGHT,
            i18n("button.request"),
            (button) -> {
                String day = this.editbox.getValue();
                if (!day.isEmpty()) {
                    TungShingScreenUtils.requestUpdate(this, Integer.valueOf(day));
                }
            }
        );
        this.addWidget(this.button);

        super.init();
    }

    @Override
    protected void subRender(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (unseiArray == null || dayArray == null) {
            return;
        }

        for (int i = 0, l = this.unseiArray.length; i < l; i++) {
            int unsei = this.unseiArray[i];

            var mark = UnseiUtils.convertToUnseiPair(unsei);
            var fortune = mark.getRight();

            var text = TextUtils.empty()
                .append(i18n("unsei.label", this.dayArray[i]))
                .append(mark.getLeft().name)
                .append(fortune.name)
                .append(i18n("unsei.value", unsei))
                .withStyle(fortune == UnseiFortune.KICHI ? ChatFormatting.GREEN : (fortune == UnseiFortune.KYOU ? ChatFormatting.RED : ChatFormatting.GRAY));


            this.font.drawShadow(
                poseStack,
                text,
                boxX1 + UNSEI_LINE_X_OFFSET, boxY1 + UNSEI_LINE_Y_OFFSET + (i + 1) * UNSEI_LINE_Y_ADDEND,
                0x000000
            );
        }

        this.editbox.render(poseStack, mouseX, mouseY, partialTick);
        this.button.render(poseStack, mouseX, mouseY, partialTick);
    }

    @Override
    public void updateUnsei(int[] dayArray, int[] unseiArray) {
        Validate.isTrue(dayArray.length == 3);
        Validate.isTrue(unseiArray.length == 3);
        this.dayArray = dayArray;
        this.unseiArray = unseiArray;
    }

    @Override
    public boolean isClassic() {
        return true;
    }

    protected static TranslatableComponent i18n(String key) {
        return TextUtils.translatable(NotEnoughLuck.i18n("gui.tung_shing.classic." + key));
    }

    protected static TranslatableComponent i18n(String key, Object... arguments) {
        return TextUtils.translatable(NotEnoughLuck.i18n("gui.tung_shing.classic." + key), arguments);
    }

}
