package org.auioc.mods.notenoughluck.common.itemgroup;

import org.auioc.mods.notenoughluck.common.itemgroup.impl.NELItemGroup;
import org.auioc.mods.notenoughluck.common.itemgroup.impl.NELPotionGroup;
import net.minecraft.world.item.CreativeModeTab;

public class ItemGroupRegistry {

    public static void init() {}

    public static CreativeModeTab NELItemGroup = new NELItemGroup();
    public static CreativeModeTab NELPotionGroup = new NELPotionGroup();

}
