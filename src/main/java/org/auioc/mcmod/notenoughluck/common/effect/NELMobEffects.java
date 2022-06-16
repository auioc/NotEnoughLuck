package org.auioc.mcmod.notenoughluck.common.effect;

import java.util.function.Supplier;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import org.auioc.mcmod.notenoughluck.common.effect.impl.RedemptionEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class NELMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, NotEnoughLuck.MOD_ID);

    private static RegistryObject<MobEffect> register(String id, Supplier<? extends MobEffect> sup) {
        return MOB_EFFECTS.register(id, sup);
    }

    public static final RegistryObject<MobEffect> ICON_ITEM = register("redemption", RedemptionEffect.REGISTRY_ENTRY_SUPPLIER);

}
