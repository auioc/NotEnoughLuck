package org.auioc.mods.notenoughluck.server.command;

import static net.minecraft.commands.Commands.literal;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import org.auioc.mods.arnicalib.common.command.impl.VersionCommand;
import org.auioc.mods.arnicalib.server.command.AHServerCommands;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.server.command.impl.TungShingCommand;
import org.auioc.mods.notenoughluck.server.command.impl.UnseiCommand;
import net.minecraft.commands.CommandSourceStack;

public class NELServerCommands {

    public static final CommandNode<CommandSourceStack> NODE = literal(NotEnoughLuck.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VersionCommand.addVersionNode(NODE, NotEnoughLuck.class);

        NODE.addChild(UnseiCommand.NODE);
        NODE.addChild(TungShingCommand.NODE);

        AHServerCommands.getAHNode(dispatcher).addChild(NODE);
    }

}
