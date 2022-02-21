package org.auioc.mods.notenoughluck.common.itemgroup.impl;

import java.util.List;
import org.auioc.mods.arnicalib.utils.game.RegistryUtils;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.common.alchemy.PotionRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class NELPotionGroup extends CreativeModeTab {

    private static final List<Item> POTION_ITEMS = List.of(
        Items.POTION,
        Items.SPLASH_POTION,
        Items.LINGERING_POTION,
        Items.TIPPED_ARROW
    );

    public NELPotionGroup() {
        super(NotEnoughLuck.MOD_ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.POTION);
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> list) {
        List<Potion> potions = RegistryUtils.getAllRegistryObjects(PotionRegistry.class, Potion.class);
        for (int i = 0, l = potions.size(); i < l; i++) {
            for (Item potionItem : POTION_ITEMS) {
                list.add(PotionUtils.setPotion(new ItemStack(potionItem), potions.get(i)));
            }
            if (i % 2 == 0) {
                list.add(ItemStack.EMPTY);
            }
        }
    }

}

