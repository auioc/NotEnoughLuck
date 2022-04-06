package org.auioc.mcmod.notenoughluck.common.item.impl;

import java.time.LocalDate;
import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.utils.game.ItemUtils;
import org.auioc.mcmod.arnicalib.utils.game.PlayerUtils;
import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import org.auioc.mcmod.notenoughluck.common.itemgroup.NELItemGroups;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class FourLeafCloverItem extends Item {

    private static final Predicate<ItemStack> IS_PAPER = (stack) -> stack.is(Items.PAPER);
    private static final int USE_DURATION = 3 * 20;

    public FourLeafCloverItem() {
        super(
            new Item.Properties()
                .tab(NELItemGroups.NELItemGroup)
                .stacksTo(16)
        );
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (canUse(player)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }

        return InteractionResultHolder.pass(stack);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!(living instanceof ServerPlayer)) {
            return stack;
        }
        var player = (ServerPlayer) living;

        if (!canUse(player)) {
            return stack;
        }

        if (!player.getAbilities().instabuild) {
            int i = lengthOfYear();
            if (ItemUtils.clearItem(player.getInventory(), IS_PAPER, i, true) != i) {
                return stack;
            }

            stack.shrink(1);
        }

        PlayerUtils.giveItem(player, NELItems.TUNG_SHING_ITEM.get());

        return stack;
    }

    private static int lengthOfYear() {
        return LocalDate.now().lengthOfYear();
    }

    private static boolean canUse(Player player) {
        return player.getAbilities().instabuild || ItemUtils.countItem(player.getInventory(), IS_PAPER) >= lengthOfYear();
    }

}
