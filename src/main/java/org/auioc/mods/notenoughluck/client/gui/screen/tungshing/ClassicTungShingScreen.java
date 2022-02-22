package org.auioc.mods.notenoughluck.client.gui.screen.tungshing;

import com.mojang.blaze3d.vertex.PoseStack;
import org.auioc.mods.arnicalib.client.gui.screen.SimpleScreen;
import org.auioc.mods.arnicalib.utils.java.Validate;

public class ClassicTungShingScreen extends SimpleScreen implements ITungShingScreen {

    private int[] dayArray;
    private int[] unseiArray;

    public ClassicTungShingScreen() {
        super(TungShingScreenUtils.i18n("title"));
    }

    @Override
    protected void subRender(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (unseiArray == null || dayArray == null) {
            return;
        }
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

}
