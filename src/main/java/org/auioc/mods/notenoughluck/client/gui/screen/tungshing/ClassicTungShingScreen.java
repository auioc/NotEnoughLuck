package org.auioc.mods.notenoughluck.client.gui.screen.tungshing;

import com.mojang.blaze3d.vertex.PoseStack;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mods.arnicalib.client.gui.screen.SimpleScreen;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import org.auioc.mods.arnicalib.utils.java.Validate;
import org.auioc.mods.notenoughluck.Reference;
import org.auioc.mods.notenoughluck.common.unsei.UnseiFortune;
import org.auioc.mods.notenoughluck.common.unsei.UnseiPrefix;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
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
    protected void init() {
        int divX = center(this.width, DIV_WIDTH);
        int divY = center(this.height, DIV_HEIGHT);

        this.editbox = new EditBox(
            this.font,
            divX + EDITBOX_X_OFFSET, divY + EDITBOX_Y_OFFSET,
            EDITBOX_WIDTH, EDITBOX_HEIGHT,
            i18n("editbox")
        );
        this.editbox.setFilter(TungShingScreenUtils.IS_INTEGER_STRING);
        this.addWidget(this.editbox);

        this.button = new Button(
            divX + BUTTON_X_OFFSET, divY + BUTTON_Y_OFFSET,
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

            Pair<UnseiPrefix, UnseiFortune> mark = UnseiUtils.getUnseiPair(unsei);
            UnseiFortune fortune = mark.getRight();

            Component text = TextUtils.EmptyText()
                .append(i18n("unsei.label", this.dayArray[i]))
                .append(mark.getLeft().name)
                .append(fortune.name)
                .append(i18n("unsei.value", unsei))
                .withStyle(fortune == UnseiFortune.KICHI ? ChatFormatting.GREEN : (fortune == UnseiFortune.KYOU ? ChatFormatting.RED : ChatFormatting.GRAY));


            this.font.drawShadow(
                poseStack,
                text,
                this.divX + UNSEI_LINE_X_OFFSET, this.divY + UNSEI_LINE_Y_OFFSET + (i + 1) * UNSEI_LINE_Y_ADDEND,
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
        return TextUtils.I18nText(Reference.I18nKey("gui.tung_shing.classic." + key));
    }

    protected static TranslatableComponent i18n(String key, Object... arguments) {
        return TextUtils.I18nText(Reference.I18nKey("gui.tung_shing.classic." + key), arguments);
    }

}
