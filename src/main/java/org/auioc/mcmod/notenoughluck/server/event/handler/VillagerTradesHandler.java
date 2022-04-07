package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class VillagerTradesHandler {

    private static final int EMERALD_COST = 32;
    private static final int SALE_COUNT = 1;
    private static final int MAX_TRADES = 2;
    private static final int VILLAGER_XP = 30;
    private static final float PRICE_MULTIPLIER = 0.25F;

    public static void handle(final VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CLERIC) {
            event.getTrades().get(5).add(
                new BasicItemListing(
                    new ItemStack(Items.EMERALD, EMERALD_COST),
                    new ItemStack(NELItems.INDULGENCE_ITEM.get(), SALE_COUNT),
                    MAX_TRADES,
                    VILLAGER_XP,
                    PRICE_MULTIPLIER
                )
            );
        }
    }

}
