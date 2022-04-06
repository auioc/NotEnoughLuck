package org.auioc.mcmod.notenoughluck.common.itemgroup.impl;

import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class NELItemGroup extends CreativeModeTab {

    public NELItemGroup() {
        super(NotEnoughLuck.MOD_ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(NELItems.ICON_ITEM.get());
    }

}
