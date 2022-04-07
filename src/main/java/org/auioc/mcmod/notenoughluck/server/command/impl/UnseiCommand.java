package org.auioc.mcmod.notenoughluck.server.command.impl;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.auioc.mcmod.arnicalib.api.mixin.common.IMixinCommandSourceStack;
import org.auioc.mcmod.arnicalib.utils.game.CommandUtils;
import org.auioc.mcmod.arnicalib.utils.game.TextUtils;
import org.auioc.mcmod.notenoughluck.client.network.ClearClientUnseiCachePacket;
import org.auioc.mcmod.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mcmod.notenoughluck.server.unsei.ServerUnseiCache;
import org.auioc.mcmod.notenoughluck.utils.UnseiUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.server.dedicated.DedicatedServer;

public class UnseiCommand {

    public static final CommandNode<CommandSourceStack> NODE =
        literal("unsei")
            .requires(source -> source.hasPermission(3))
            .then(literal("clearClientCache").then(argument("targets", GameProfileArgument.gameProfile()).executes(UnseiCommand::clearClientCache)))
            .then(literal("clearServerCache").executes(UnseiCommand::clearServerCache))
            .then(literal("test").executes(UnseiCommand::test))
            .build();

    private static int clearClientCache(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        var playerList = ctx.getSource().getServer().getPlayerList();

        Collection<GameProfile> targets = GameProfileArgument.getGameProfiles(ctx, "targets");
        for (var gameprofile : targets) {
            NELPacketHandler.sendToClient(playerList.getPlayer(gameprofile.getId()), new ClearClientUnseiCachePacket());
        }

        ctx.getSource().sendSuccess(TextUtils.I18nText("notenoughluck.command.unsei.clear_client_cache", targets.size()), true);

        return Command.SINGLE_SUCCESS;
    }

    private static int clearServerCache(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        var source = ctx.getSource();

        if ((source.getServer() instanceof DedicatedServer) && !(((IMixinCommandSourceStack) source).getSource() instanceof DedicatedServer)) {
            throw CommandUtils.NOT_DEDICATED_SERVER_ERROR.create();
        }

        ServerUnseiCache.clear();

        source.sendSuccess(TextUtils.I18nText("notenoughluck.command.unsei.clear_server_cache"), true);

        return Command.SINGLE_SUCCESS;
    }

    private static int test(CommandContext<CommandSourceStack> ctx) {
        for (int l = 0; l < 100; l++) {
            int[] U = new int[9];
            List<Integer> V = new ArrayList<Integer>();

            long seed = (ctx.getSource().getLevel().getSeed() + RandomUtils.nextLong());

            for (int x = 0; x < 10000; x++) {
                int k = UnseiUtils.calcUnseiValue(seed, x);

                V.add(k);

                if (k < 1) {
                    U[0]++;
                } else if (k < 3) {
                    U[1]++;
                } else if (k < 7) {
                    U[2]++;
                } else if (k < 15) {
                    U[3]++;
                } else if (k < 25) {
                    U[4]++;
                } else if (k < 31) {
                    U[5]++;
                } else if (k < 34) {
                    U[6]++;
                } else if (k < 36) {
                    U[7]++;
                } else {
                    U[8]++;
                }
            }

            System.err.println(ArrayUtils.toString(U));
            // System.err.println(ArrayUtils.toString(V.toArray()));
        }

        return Command.SINGLE_SUCCESS;
    }

}
