package org.auioc.mcmod.notenoughluck.server.command;

import static net.minecraft.commands.Commands.literal;
import org.auioc.mcmod.arnicalib.common.command.impl.VersionCommand;
import org.auioc.mcmod.arnicalib.server.command.AHServerCommands;
import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import org.auioc.mcmod.notenoughluck.server.command.impl.TungShingCommand;
import org.auioc.mcmod.notenoughluck.server.command.impl.UnseiCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;

public class NELServerCommands {

    public static final CommandNode<CommandSourceStack> NODE = literal(NotEnoughLuck.MOD_ID).build();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        VersionCommand.addVersionNode(NODE, NotEnoughLuck.class);

        NODE.addChild(UnseiCommand.NODE);
        NODE.addChild(TungShingCommand.NODE);

        AHServerCommands.getAHNode(dispatcher).addChild(NODE);
        dispatcher.register(literal("nel").redirect(NODE));
    }

}
