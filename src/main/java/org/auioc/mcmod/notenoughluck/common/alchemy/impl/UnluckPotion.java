package org.auioc.mcmod.notenoughluck.common.alchemy.impl;

import org.auioc.mcmod.notenoughluck.common.alchemy.NELPotions;
import org.auioc.mcmod.notenoughluck.common.alchemy.base.NELPotion;
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
            super(6000, 0); // 5 min
        }

        public static RegistryObject<Potion> register() {
            return register("unluck", Common::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, NELPotions.UNLUCK);
        }
    }

    public static class Long extends UnluckPotion {
        protected Long() {
            super(16000, 0); // 6000 * (8/3)
        }

        public static RegistryObject<Potion> register() {
            return register("long_unluck", Long::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(NELPotions.UNLUCK, Items.REDSTONE, NELPotions.LONG_UNLUCK);
        }
    }

    public static class Strong extends UnluckPotion {
        protected Strong() {
            super(3000, 1); // 6000 * (1/2)
        }

        public static RegistryObject<Potion> register() {
            return register("strong_unluck", Strong::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(NELPotions.UNLUCK, Items.GLOWSTONE_DUST, NELPotions.STRONG_UNLUCK);
        }
    }

}
