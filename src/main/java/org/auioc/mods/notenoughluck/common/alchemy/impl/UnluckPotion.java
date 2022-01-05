package org.auioc.mods.notenoughluck.common.alchemy.impl;

import org.auioc.mods.notenoughluck.common.alchemy.PotionRegistry;
import org.auioc.mods.notenoughluck.common.alchemy.base.NELPotion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.RegistryObject;

public class UnluckPotion extends NELPotion {

    protected UnluckPotion(int duration, int amplifier) {
        super("unluck", MobEffects.UNLUCK, duration, amplifier);
    }

    public static class Common extends UnluckPotion {
        protected Common() {
            super(5 * 60 * 20, 0);
        }

        public static RegistryObject<Potion> register() {
            return register("unluck", Common::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, PotionRegistry.UNLUCK);
        }
    }

    public static class Long extends UnluckPotion {
        protected Long() {
            super(10 * 60 * 20, 0);
        }

        public static RegistryObject<Potion> register() {
            return register("long_unluck", Long::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(PotionRegistry.UNLUCK, Items.REDSTONE, PotionRegistry.LONG_UNLUCK);
        }
    }

    public static class Strong extends UnluckPotion {
        protected Strong() {
            super((int) 2.5 * 60 * 20, 1);
        }

        public static RegistryObject<Potion> register() {
            return register("strong_unluck", Strong::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(PotionRegistry.UNLUCK, Items.GLOWSTONE_DUST, PotionRegistry.STRONG_UNLUCK);
        }
    }

}
