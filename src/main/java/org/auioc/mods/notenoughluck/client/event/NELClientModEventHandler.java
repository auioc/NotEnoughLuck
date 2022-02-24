package org.auioc.mods.notenoughluck.client.event;

import java.util.Map;
import org.auioc.mods.notenoughluck.Reference;
import org.auioc.mods.notenoughluck.client.model.baked.DiceItemBakedModel;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NELClientModEventHandler {

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, BakedModel> modelRegistry = event.getModelRegistry();

        DiceItemBakedModel.register(modelRegistry);
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        ForgeModelBakery.addSpecialModel(Reference.ResourceId("item/dice_unknown"));
    }

}
