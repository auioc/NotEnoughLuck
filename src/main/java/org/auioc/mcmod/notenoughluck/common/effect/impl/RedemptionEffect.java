package org.auioc.mcmod.notenoughluck.common.effect.impl;

import java.util.function.Supplier;
import org.auioc.mcmod.notenoughluck.common.attribute.NELAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class RedemptionEffect extends MobEffect {

    public static final Supplier<? extends MobEffect> REGISTRY_ENTRY_SUPPLIER = () -> new RedemptionEffect()
        .addAttributeModifier(
            NELAttributes.UNSEI.get(),
            "DBBC227F-AC30-AB2D-2D75-6508817E77EE",
            1.0D,
            AttributeModifier.Operation.ADDITION
        );

    public RedemptionEffect() {
        super(MobEffectCategory.BENEFICIAL, 3381504);
    }

}
