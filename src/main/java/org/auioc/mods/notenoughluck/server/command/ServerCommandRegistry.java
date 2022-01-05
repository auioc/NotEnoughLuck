package org.auioc.mods.notenoughluck.server.command;

import static net.minecraft.commands.Commands.literal;
import com.mojang.brigadier.CommandDispatcher;
import org.auioc.mods.arnicalib.server.command.impl.VersionCommand;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import net.minecraft.commands.CommandSourceStack;

public class ServerCommandRegistry {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        org.auioc.mods.arnicalib.server.command.ServerCommandRegistry.getRootNode(dispatcher).addChild(
            literal(NotEnoughLuck.MOD_ID).executes((ctx) -> VersionCommand.getModVersion(ctx, NotEnoughLuck.MAIN_VERSION, NotEnoughLuck.FULL_VERSION, NotEnoughLuck.MOD_NAME))
                .build()
        );
    }

}
