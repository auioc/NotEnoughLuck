package org.auioc.mods.notenoughluck.common.alchemy.base;

import java.util.function.Supplier;
import org.auioc.mcmod.arnicalib.api.game.alchemy.HPotion;
import org.auioc.mods.notenoughluck.common.alchemy.NELPotions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.RegistryObject;

public class NELPotion extends HPotion {

    public NELPotion(String name, MobEffect effect, int duration, int amplifier, boolean incurable) {
        super(name, effect, duration, amplifier, incurable);
    }

    public NELPotion(String name, MobEffect effect, int duration, int amplifier) {
        super(name, effect, duration, amplifier);
    }

    public static RegistryObject<Potion> register(String id, Supplier<? extends Potion> sup) {
        return NELPotions.POTIONS.register(id, sup);
    }

}

