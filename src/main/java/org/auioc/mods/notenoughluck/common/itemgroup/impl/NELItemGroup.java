package org.auioc.mods.notenoughluck.common.itemgroup.impl;

import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.common.item.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class NELItemGroup extends CreativeModeTab {

    public NELItemGroup() {
        super(NotEnoughLuck.MOD_ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.ICON_ITEM.get());
    }

}
