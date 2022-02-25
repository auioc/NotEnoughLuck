package org.auioc.mods.notenoughluck.client.model.baked;

import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.mojang.blaze3d.vertex.PoseStack;
import org.auioc.mods.notenoughluck.common.item.NELItems;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;

public class DiceItemBakedModel implements BakedModel {

    private BakedModel existingModel;

    public DiceItemBakedModel(BakedModel existingModel) {
        this.existingModel = existingModel;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
        return this.existingModel.getQuads(state, side, rand, extraData);
    }

    @Override
    @SuppressWarnings("deprecation")
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return this.existingModel.getQuads(state, side, rand);
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.existingModel.getOverrides();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.existingModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.existingModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.existingModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public TextureAtlasSprite getParticleIcon() {
        return this.existingModel.getParticleIcon();
    }


    @Override
    @SuppressWarnings("deprecation")
    public ItemTransforms getTransforms() {
        return this.existingModel.getTransforms();
    }

    @Override
    public BakedModel handlePerspective(TransformType cameraTransformType, PoseStack poseStack) {
        if (cameraTransformType == TransformType.GUI || cameraTransformType == TransformType.GROUND) {
            return this;
        }
        return this.existingModel.handlePerspective(cameraTransformType, poseStack);
    }


    private static void register(Map<ResourceLocation, BakedModel> modelRegistry, ResourceLocation id) {
        ModelResourceLocation location = new ModelResourceLocation(id, "inventory");
        BakedModel oldModel = modelRegistry.get(location);
        BakedModel newModel = new DiceItemBakedModel(oldModel);
        modelRegistry.put(location, newModel);
    }

    public static void register(Map<ResourceLocation, BakedModel> modelRegistry) {
        register(modelRegistry, NELItems.COMMON_DICE_ITEM.getId());
        register(modelRegistry, NELItems.DICE_OF_TYCHE_ITEM.getId());

    }

}
