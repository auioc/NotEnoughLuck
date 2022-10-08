package org.auioc.mcmod.notenoughluck.server.event.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.hulsealib.game.event.server.LivingEatAddEffectEvent;
import org.auioc.mcmod.notenoughluck.utils.LuckUtils;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class EatAddEffectHandler {

    private static final Map<FoodProperties, List<Data>> MAP = new HashMap<FoodProperties, List<Data>>();

    @SuppressWarnings("deprecation")
    public static void handle(final LivingEatAddEffectEvent event) {
        var living = event.getEntityLiving();
        var item = event.getFood().getItem();
        var effects = event.getEffects();

        if (!item.isEdible()) return;

        FoodProperties food = item.getFoodProperties();
        if (!MAP.containsKey(food)) return;

        effects.clear();
        List<Data> dataList = MAP.get(food);
        List<Pair<MobEffectInstance, Float>> foodEffects = food.getEffects();
        for (int i = 0, l = dataList.size(); i < l; i++) {
            if (dataList.get(i).shouldAddEffect(living)) {
                effects.add(foodEffects.get(i).getFirst());
            }
        }
    }


    static {
        putItem(Items.ROTTEN_FLESH, List.of(new Data(80, -4, 1, 5)));
    }

    @SuppressWarnings("deprecation")
    private static void putItem(Item item, List<Data> dataList) {
        Validate.isTrue(item.isEdible(), "Item %s is not a food item", item);
        Validate.isTrue(item.getFoodProperties().getEffects().size() == dataList.size());
        MAP.put(item.getFoodProperties(), dataList);
    }

    private static record Data(int defaultChance, int luckMultiplier, int unluckMultiplier, int bonusMultiplier) {

        public boolean shouldAddEffect(LivingEntity living) {
            return LuckUtils.getRandomBoolean(living, this.defaultChance, this.luckMultiplier, this.unluckMultiplier, this.bonusMultiplier);
        }

    }

}
