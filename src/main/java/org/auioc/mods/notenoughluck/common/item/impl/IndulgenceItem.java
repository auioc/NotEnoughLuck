package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class IndulgenceItem extends Item {

    public IndulgenceItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .rarity(Rarity.RARE)
                .stacksTo(1)
        );
    }

}
