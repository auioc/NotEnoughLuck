package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import org.auioc.mcmod.notenoughluck.server.config.NELServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.WandererTradesEvent;

public class WandererTradesHandler {

    public static void handle(final WandererTradesEvent event) {
        event.getRareTrades().add(
            new BasicItemListing(
                NELServerConfig.CloverEmeraldCost.get(),
                new ItemStack(NELItems.FOUR_LEAF_CLOVER_ITEM.get(), NELServerConfig.CloverSaleCount.get()),
                NELServerConfig.CloverMaxTrades.get(),
                NELServerConfig.CloverVillagerXp.get(),
                NELServerConfig.CloverPriceMultiplier.get().floatValue()
            )
        );
    }

}
