package org.auioc.mods.notenoughluck.common.itemgroup.impl;

import static org.auioc.mods.notenoughluck.NotEnoughLuck.LOGGER;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.utils.LogUtil;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.common.alchemy.PotionRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.RegistryObject;

public class NELItemGroup extends CreativeModeTab {

    private static final Marker MARKER = LogUtil.getMarker(NELItemGroup.class);

    private static final List<Item> potionBasedItems = List.of(
        Items.POTION,
        Items.SPLASH_POTION,
        Items.LINGERING_POTION,
        Items.TIPPED_ARROW
    );

    public NELItemGroup() {
        super(NotEnoughLuck.MOD_ID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.POTION);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void fillItemList(NonNullList<ItemStack> list) {
        Field[] fields = PotionRegistry.class.getDeclaredFields();
        // int index = 0;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if ((Modifier.isFinal(field.getModifiers()))) {
                try {
                    Object value = field.get(null);
                    if (!(value instanceof RegistryObject)) {
                        continue;
                    }
                    for (Item item : potionBasedItems) {
                        ItemStack potion = PotionUtils.setPotion(
                            new ItemStack(item),
                            ((RegistryObject<Potion>) value).get()
                        );
                        list.add(potion);
                    }
                    // if (index % 2 == 0) {
                    //     list.add(ItemStack.EMPTY);
                    // }
                    // index++;
                } catch (Exception e) {
                    LOGGER.error(MARKER, "Failed to add item to group.", e);
                }
            }
        }
    }

}
