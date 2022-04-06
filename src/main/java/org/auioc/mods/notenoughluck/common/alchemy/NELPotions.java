package org.auioc.mods.notenoughluck.common.alchemy;

import org.auioc.mcmod.arnicalib.api.game.registry.IHRegistry;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.common.alchemy.impl.IncurableUnluckPotion;
import org.auioc.mods.notenoughluck.common.alchemy.impl.LuckPotion;
import org.auioc.mods.notenoughluck.common.alchemy.impl.UnluckPotion;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NELPotions implements IHRegistry {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, NotEnoughLuck.MOD_ID);

    @SubscribeEvent
    public static void onSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(NELPotions::registerBrewingRecipes);
    }


    public static final RegistryObject<Potion> LONG_LUCK = LuckPotion.Long.register();
    public static final RegistryObject<Potion> STRONG_LUCK = LuckPotion.Strong.register();
    public static final RegistryObject<Potion> UNLUCK = UnluckPotion.Common.register();
    public static final RegistryObject<Potion> LONG_UNLUCK = UnluckPotion.Long.register();
    public static final RegistryObject<Potion> STRONG_UNLUCK = UnluckPotion.Strong.register();
    public static final RegistryObject<Potion> INCURABLE_UNLUCK = IncurableUnluckPotion.Common.register();
    public static final RegistryObject<Potion> INCURABLE_LONG_UNLUCK = IncurableUnluckPotion.Long.register();
    public static final RegistryObject<Potion> INCURABLE_STRONG_UNLUCK = IncurableUnluckPotion.Strong.register();

    private static void registerBrewingRecipes() {
        LuckPotion.Long.registerBrewingRecipe();
        LuckPotion.Strong.registerBrewingRecipe();
        UnluckPotion.Common.registerBrewingRecipe();
        UnluckPotion.Long.registerBrewingRecipe();
        UnluckPotion.Strong.registerBrewingRecipe();
    }

}
