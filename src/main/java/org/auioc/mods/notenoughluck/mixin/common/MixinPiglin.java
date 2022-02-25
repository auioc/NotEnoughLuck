package org.auioc.mods.notenoughluck.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.Piglin;

@Mixin(value = Piglin.class)
public abstract class MixinPiglin {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Overwrite()
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.35F).add(Attributes.ATTACK_DAMAGE, 5.0D)
            .add(Attributes.LUCK, 0.0D);
    }

}
