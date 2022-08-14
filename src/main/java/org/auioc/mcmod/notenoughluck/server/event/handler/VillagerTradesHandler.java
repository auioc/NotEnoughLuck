package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import org.auioc.mcmod.notenoughluck.server.config.NELServerConfig;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class VillagerTradesHandler {

    public static void handle(final VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CLERIC) {
            event.getTrades().get(5).add(
                new BasicItemListing(
                    NELServerConfig.IndulgenceEmeraldCost.get(),
                    new ItemStack(NELItems.INDULGENCE_ITEM.get(), NELServerConfig.IndulgenceSaleCount.get()),
                    NELServerConfig.IndulgenceMaxTrades.get(),
                    NELServerConfig.IndulgenceVillagerXp.get(),
                    NELServerConfig.IndulgencePriceMultiplier.get().floatValue()
                )
            );
        }
    }

}
