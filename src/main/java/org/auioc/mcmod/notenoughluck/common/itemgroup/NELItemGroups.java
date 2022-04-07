package org.auioc.mcmod.notenoughluck.common.itemgroup;

import org.auioc.mcmod.notenoughluck.common.itemgroup.impl.NELItemGroup;
import org.auioc.mcmod.notenoughluck.common.itemgroup.impl.NELPotionGroup;
import net.minecraft.world.item.CreativeModeTab;

public class NELItemGroups {

    public static void init() {}

    public static CreativeModeTab NELItemGroup = new NELItemGroup();
    public static CreativeModeTab NELPotionGroup = new NELPotionGroup();

}
