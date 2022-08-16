package org.auioc.mcmod.notenoughluck.common.alchemy.impl;

import org.auioc.mcmod.arnicalib.api.game.alchemy.HPotion;
import org.auioc.mcmod.notenoughluck.common.alchemy.NELPotions;
import org.auioc.mcmod.notenoughluck.common.alchemy.base.NELPotion;
import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.RegistryObject;

public class LuckPotion extends NELPotion {

    protected LuckPotion(int duration, int amplifier) {
        super("luck", MobEffects.LUCK, duration, amplifier);
    }

    public static class Common {

        public static boolean registerBrewingRecipe() {
            return HPotion.registerBrewingRecipe(Potions.AWKWARD, NELItems.FOUR_LEAF_CLOVER_ITEM.get(), Potions.LUCK);
        }

    }

    public static class Long extends LuckPotion {
        protected Long() {
            super(16000, 0); // 6000 * (8/3)
        }

        public static RegistryObject<Potion> register() {
            return register("long_luck", Long::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(Potions.LUCK, Items.REDSTONE, NELPotions.LONG_LUCK);
        }
    }

    public static class Strong extends LuckPotion {
        protected Strong() {
            super(3000, 1); // 6000 * (1/2)
        }

        public static RegistryObject<Potion> register() {
            return register("strong_luck", Strong::new);
        }

        public static boolean registerBrewingRecipe() {
            return registerBrewingRecipe(Potions.LUCK, Items.GLOWSTONE_DUST, NELPotions.STRONG_LUCK);
        }
    }

}
