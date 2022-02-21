package org.auioc.mods.notenoughluck.server.command.impl;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import java.util.Collection;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import org.auioc.mods.notenoughluck.client.network.ClearClientUnseiCachePacket;
import org.auioc.mods.notenoughluck.common.network.NELPacketHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.server.players.PlayerList;

public class UnseiCommand {

    public static final CommandNode<CommandSourceStack> NODE =
        literal("unsei")
            .requires(source -> source.hasPermission(3))
            .then(literal("clearClientCache").then(argument("targets", GameProfileArgument.gameProfile()).executes(UnseiCommand::clearClientCache)))
            .build();

    private static int clearClientCache(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        PlayerList playerList = ctx.getSource().getServer().getPlayerList();

        Collection<GameProfile> targets = GameProfileArgument.getGameProfiles(ctx, "targets");
        for (GameProfile gameprofile : targets) {
            NELPacketHandler.sendToClient(playerList.getPlayer(gameprofile.getId()), new ClearClientUnseiCachePacket());
        }

        return Command.SINGLE_SUCCESS;
    }

}
