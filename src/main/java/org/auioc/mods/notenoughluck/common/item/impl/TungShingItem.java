package org.auioc.mods.notenoughluck.common.item.impl;

import org.auioc.mods.notenoughluck.client.gui.screen.TungShingScreen;
import org.auioc.mods.notenoughluck.common.itemgroup.ItemGroupRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class TungShingItem extends Item {

    public TungShingItem() {
        super(
            new Item.Properties()
                .tab(ItemGroupRegistry.NELItemGroup)
                .stacksTo(1)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide) {
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TungShingScreen::open);
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

}
