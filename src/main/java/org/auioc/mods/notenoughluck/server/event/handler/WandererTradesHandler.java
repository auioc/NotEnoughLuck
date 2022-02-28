package org.auioc.mods.notenoughluck.server.event.handler;

import org.auioc.mods.notenoughluck.common.item.NELItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.WandererTradesEvent;

public class WandererTradesHandler {

    private static final int EMERALD_COST = 7;
    private static final int SALE_COUNT = 1;
    private static final int MAX_TRADES = 1;
    private static final int VILLAGER_XP = 1;
    private static final float PRICE_MULTIPLIER = 0.05F;

    public static void handle(final WandererTradesEvent event) {
        event.getRareTrades().add(
            new BasicItemListing(
                new ItemStack(Items.EMERALD, EMERALD_COST),
                new ItemStack(NELItems.FOUR_LEAF_CLOVER_ITEM.get(), SALE_COUNT),
                MAX_TRADES,
                VILLAGER_XP,
                PRICE_MULTIPLIER
            )
        );
    }

}
