package org.auioc.mods.notenoughluck.common.item.impl;

import java.util.function.Consumer;
import org.auioc.mods.notenoughluck.client.renderer.DiceItemRenderer;
import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

public class DiceItem extends Item {

    public DiceItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        stack.getOrCreateTag().putInt("pips", player.getRandom().nextInt(1, 7));
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return DiceItemRenderer.INSTANCE;
            }
        });
    }

}
