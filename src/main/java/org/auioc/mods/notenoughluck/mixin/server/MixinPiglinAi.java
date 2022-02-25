package org.auioc.mods.notenoughluck.mixin.server;

import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;


@Mixin(value = PiglinAi.class)
public abstract class MixinPiglinAi {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Overwrite()
    private static List<ItemStack> getBarterResponseItems(Piglin p_34997_) {
        LootTable loottable = p_34997_.level.getServer().getLootTables().get(BuiltInLootTables.PIGLIN_BARTERING);
        return loottable
            .getRandomItems(
                (new LootContext.Builder((ServerLevel) p_34997_.level))
                    .withParameter(LootContextParams.THIS_ENTITY, p_34997_)
                    .withRandom(p_34997_.level.random)
                    .withLuck((float) p_34997_.getAttributeValue(Attributes.LUCK)) // Add luck value to loot context
                    .create(LootContextParamSets.PIGLIN_BARTER)
            );
    }

}
