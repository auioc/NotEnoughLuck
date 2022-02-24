package org.auioc.mods.notenoughluck.client.renderer;

import java.util.ArrayList;
import java.util.function.Consumer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mods.notenoughluck.Reference;
import org.auioc.mods.notenoughluck.common.item.NELItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("deprecation")
public class DiceItemRenderer extends BlockEntityWithoutLevelRenderer {

    public static final DiceItemRenderer INSTANCE = new DiceItemRenderer();

    private static final ArrayList<Pair<Consumer<PoseStack>, Quaternion>> GROUND_POSE_MAP = new ArrayList<Pair<Consumer<PoseStack>, Quaternion>>() {
        {
            add(Pair.of((poseStack) -> poseStack.translate(0.375D, 0.625D, 0.375D), new Quaternion(0.0F, 0.0F, 0.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.375D, 0.875D, 0.375D), new Quaternion(0.0F, 0.0F, 270.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.375D, 0.875D, 0.375D), new Quaternion(90.0F, 0.0F, 0.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.375D, 0.625D, 0.625D), new Quaternion(270.0F, 0.0F, 0.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.625D, 0.625D, 0.375D), new Quaternion(0.0F, 0.0F, 90.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.625D, 0.875D, 0.375D), new Quaternion(0.0F, 0.0F, 180.0F, true)));
        }
    };

    private static final ArrayList<Pair<Consumer<PoseStack>, Quaternion>> GUI_POSE_MAP = new ArrayList<Pair<Consumer<PoseStack>, Quaternion>>() {
        {
            add(Pair.of((poseStack) -> poseStack.translate(0.05D, 0.13D, 0.0D), new Quaternion(45.0F, 45.0F, 0.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.05D, 0.305D, 0.0D), new Quaternion(45.0F, 45.0F, -90.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.4D, 0.305D, 0.0D), new Quaternion(-45.0F, 180.0F, -45.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.225D, 0.005D, 0.0D), new Quaternion(-45.0F, 0.0F, 45.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.225D, 0.255D, 0.0D), new Quaternion(45.0F, 45.0F, 90.0F, true)));
            add(Pair.of((poseStack) -> poseStack.translate(0.225D, 0.43D, 0.0D), new Quaternion(45.0F, 45.0F, 180.0F, true)));
        }
    };

    public DiceItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack itemStack, TransformType transformType, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        int pips = 0;
        if (itemStack.getTag() != null) {
            pips = Mth.clamp(itemStack.getTag().getInt("pips"), 0, 6);
        }

        BakedModel model;
        if (pips > 0) {
            model = Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(NELItems.DICE_ITEM.getId(), "inventory"));
        } else {
            model = Minecraft.getInstance().getModelManager().getModel(Reference.ResourceId("item/dice_unknown"));
        }

        poseStack.pushPose();

        if (pips > 0) {
            int i = pips - 1;
            if (transformType == TransformType.GROUND) {
                applyPose(poseStack, GROUND_POSE_MAP.get(i));

            } else if (transformType == TransformType.GUI) {
                poseStack.scale(2.25F, 2.25F, 2.25F);
                applyPose(poseStack, GUI_POSE_MAP.get(i));
            }
        } else {
            // ItemTransform.translation * 16  = JSON translation
            ItemTransform itr = model.getTransforms().getTransform(transformType);
            itr.apply(false, poseStack);
        }

        Minecraft.getInstance().getItemRenderer().renderModelLists(model, itemStack, combinedLight, combinedOverlay, poseStack, buffer.getBuffer(RenderType.cutout()));

        poseStack.popPose();
    }

    private static void applyPose(PoseStack poseStack, Pair<Consumer<PoseStack>, Quaternion> pose) {
        pose.getLeft().accept(poseStack);
        poseStack.mulPose(pose.getRight());
    }

}
