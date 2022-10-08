package org.auioc.mcmod.notenoughluck.common.effect;

import java.util.function.Supplier;
import org.auioc.mcmod.hulsealib.game.effect.HMobEffect;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class NELMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, NotEnoughLuck.MOD_ID);

    private static RegistryObject<MobEffect> register(String id, Supplier<? extends MobEffect> sup) {
        return MOB_EFFECTS.register(id, sup);
    }

    public static final RegistryObject<MobEffect> REDEMPTION = register("redemption", () -> new HMobEffect(MobEffectCategory.BENEFICIAL, 3381504));

}
