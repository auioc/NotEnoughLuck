package org.auioc.mcmod.notenoughluck.common.item.impl;

import java.time.LocalDate;
import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.game.entity.player.PlayerUtils;
import org.auioc.mcmod.arnicalib.game.world.ContainerUtils;
import org.auioc.mcmod.hulsealib.game.item.UsableItem;
import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import org.auioc.mcmod.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class FourLeafCloverItem extends UsableItem {

    private static final Predicate<ItemStack> IS_PAPER = (stack) -> stack.is(Items.PAPER);
    private static final int USE_DURATION = 3 * 20;

    public FourLeafCloverItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(16),
            USE_DURATION,
            UseAnim.BOW
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!(living instanceof ServerPlayer)) return stack;

        var player = (ServerPlayer) living;

        if (!canUse(player)) return stack;

        if (!player.getAbilities().instabuild) {
            int i = lengthOfYear();
            if (ContainerUtils.clearItem(player.getInventory(), IS_PAPER, i, true) != i) {
                return stack;
            }

            stack.shrink(1);
        }

        PlayerUtils.giveItem(player, NELItems.TUNG_SHING_ITEM.get());

        return stack;
    }

    @Override
    public boolean canUse(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        return canUse(player);
    }

    private static int lengthOfYear() {
        return LocalDate.now().lengthOfYear();
    }

    private static boolean canUse(Player player) {
        return player.getAbilities().instabuild || ContainerUtils.countItem(player.getInventory(), IS_PAPER) >= lengthOfYear();
    }

}
