package org.auioc.mods.notenoughluck.server.event.handler;

import java.util.List;
import org.auioc.mods.arnicalib.server.event.impl.LivingEatAddEffectEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class EatAddEffectHandler {

    public static void handle(final LivingEatAddEffectEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Item food = event.getFood().getItem();
        List<MobEffectInstance> effects = event.getEffects();

        if (food == Items.ROTTEN_FLESH || food == Items.POISONOUS_POTATO) {
            if (effects.size() == 0) {
                effects.add(new MobEffectInstance(MobEffects.LUCK, 200, 0));
            } else if (entity.getRandom().nextFloat() < 0.25F) {
                effects.add(new MobEffectInstance(MobEffects.UNLUCK, 200, 0));
            }
            return;
        }
    }

}
