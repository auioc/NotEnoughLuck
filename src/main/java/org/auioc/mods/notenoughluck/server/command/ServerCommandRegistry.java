package org.auioc.mods.notenoughluck.server.command;

import static net.minecraft.commands.Commands.literal;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import org.auioc.mods.arnicalib.server.command.impl.VersionCommand;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.server.command.impl.TungShingCommand;
import net.minecraft.commands.CommandSourceStack;

public class ServerCommandRegistry {

    public static final CommandNode<CommandSourceStack> NODE = literal(NotEnoughLuck.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        NODE.addChild(literal("version").executes((ctx) -> VersionCommand.getModVersion(ctx, NotEnoughLuck.MAIN_VERSION, NotEnoughLuck.FULL_VERSION, NotEnoughLuck.MOD_NAME)).build());

        NODE.addChild(TungShingCommand.NODE);

        org.auioc.mods.arnicalib.server.command.ServerCommandRegistry.getRootNode(dispatcher).addChild(NODE);
    }

}
