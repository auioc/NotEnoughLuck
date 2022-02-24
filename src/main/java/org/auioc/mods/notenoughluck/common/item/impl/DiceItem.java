package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DiceItem extends Item {

    public DiceItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        stack.getOrCreateTag().putInt("pips", player.getRandom().nextInt(1, 7));
        return true;
    }

}
