package org.auioc.mods.notenoughluck.common.item;

import java.util.function.Supplier;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NotEnoughLuck.MOD_ID);

    private static RegistryObject<Item> register(String id, Supplier<? extends Item> sup) {
        return ITEMS.register(id, sup);
    }

    public static final RegistryObject<Item> ICON_ITEM = register("icon", () -> new Item((new Item.Properties())));

}
