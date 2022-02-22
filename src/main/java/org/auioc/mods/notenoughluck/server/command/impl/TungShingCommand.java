package org.auioc.mods.notenoughluck.server.command.impl;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import org.auioc.mods.notenoughluck.utils.UnseiUtils;
import net.minecraft.commands.CommandSourceStack;

public class TungShingCommand {

    public static final CommandNode<CommandSourceStack> NODE =
        literal("tungShing")
            .requires(source -> source.hasPermission(3))
            .then(
                literal("open")
                    .executes((ctx) -> open(ctx, getDay(ctx), false))
                    .then(
                        argument("day", IntegerArgumentType.integer(0))
                            .executes((ctx) -> open(ctx, IntegerArgumentType.getInteger(ctx, "day"), false))
                    )
                    .then(
                        literal("classic")
                            .executes((ctx) -> open(ctx, getDay(ctx), true))
                            .then(
                                argument("day", IntegerArgumentType.integer(0))
                                    .executes((ctx) -> open(ctx, IntegerArgumentType.getInteger(ctx, "day"), true))
                            )
                    )
            )
            .build();

    private static int open(CommandContext<CommandSourceStack> ctx, int day, boolean classic) throws CommandSyntaxException {
        UnseiUtils.sendUpdateTungShingPacket(ctx.getSource().getPlayerOrException(), ctx.getSource().getLevel().getSeed(), day, 0, classic);

        return Command.SINGLE_SUCCESS;
    }

    private static int getDay(CommandContext<CommandSourceStack> ctx) {
        return UnseiUtils.getDay(ctx.getSource().getLevel().getDayTime());
    }

}
