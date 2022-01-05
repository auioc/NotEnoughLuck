package org.auioc.mods.notenoughluck.common.alchemy;

import org.auioc.mods.notenoughluck.NotEnoughLuck;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionRegistry {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, NotEnoughLuck.MOD_ID);

    @SubscribeEvent
    public static void onSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(PotionRegistry::registerBrewingRecipes);
    }

    private static void registerBrewingRecipes() {}

}
