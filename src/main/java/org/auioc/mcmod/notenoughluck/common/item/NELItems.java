package org.auioc.mcmod.notenoughluck.common.item;

import java.util.function.Supplier;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import org.auioc.mcmod.notenoughluck.common.item.impl.CommonDiceItem;
import org.auioc.mcmod.notenoughluck.common.item.impl.DiceOfTycheItem;
import org.auioc.mcmod.notenoughluck.common.item.impl.FourLeafCloverItem;
import org.auioc.mcmod.notenoughluck.common.item.impl.IndulgenceItem;
import org.auioc.mcmod.notenoughluck.common.item.impl.TungShingItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class NELItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NotEnoughLuck.MOD_ID);

    private static RegistryObject<Item> register(String id, Supplier<? extends Item> sup) {
        return ITEMS.register(id, sup);
    }

    public static final RegistryObject<Item> ICON_ITEM = register("icon", () -> new Item((new Item.Properties())));

    public static final RegistryObject<Item> FOUR_LEAF_CLOVER_ITEM = register("four_leaf_clover", FourLeafCloverItem::new);
    public static final RegistryObject<Item> TUNG_SHING_ITEM = register("tung_shing", TungShingItem::new);
    public static final RegistryObject<Item> COMMON_DICE_ITEM = register("dice", CommonDiceItem::new);
    public static final RegistryObject<Item> DICE_OF_TYCHE_ITEM = register("dice_of_tyche", DiceOfTycheItem::new);
    public static final RegistryObject<Item> INDULGENCE_ITEM = register("indulgence", IndulgenceItem::new);

}
