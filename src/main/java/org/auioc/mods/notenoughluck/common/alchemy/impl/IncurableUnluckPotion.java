package org.auioc.mods.notenoughluck.common.alchemy.impl;

import org.auioc.mods.notenoughluck.common.alchemy.base.NELPotion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.RegistryObject;

public class IncurableUnluckPotion extends NELPotion {

    protected IncurableUnluckPotion(int duration, int amplifier) {
        super("incurable_unluck", MobEffects.UNLUCK, duration, amplifier, true);
    }

    public static class Common extends IncurableUnluckPotion {
        protected Common() {
            super(6000, 0);
        }

        public static RegistryObject<Potion> register() {
            return register("incurable_unluck", Common::new);
        }
    }

    public static class Long extends IncurableUnluckPotion {
        protected Long() {
            super(16000, 0);
        }

        public static RegistryObject<Potion> register() {
            return register("incurable_long_unluck", Long::new);
        }
    }

    public static class Strong extends IncurableUnluckPotion {
        protected Strong() {
            super(3000, 1);
        }

        public static RegistryObject<Potion> register() {
            return register("incurable_strong_unluck", Strong::new);
        }
    }

}
