package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.world.item.Item;

public class DiceItem extends Item {

    public DiceItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(1)
        );
    }

}
