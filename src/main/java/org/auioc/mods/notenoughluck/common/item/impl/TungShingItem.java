package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.common.itemgroup.ItemGroupRegistry;
import net.minecraft.world.item.Item;

public class TungShingItem extends Item {

    public TungShingItem() {
        super(
            new Item.Properties()
                .tab(ItemGroupRegistry.NELItemGroup)
                .stacksTo(1)
        );
    }

}
