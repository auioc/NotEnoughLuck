package org.auioc.mods.notenoughluck.server.event;

import org.auioc.mods.arnicalib.server.event.impl.LivingEatAddEffectEvent;
import org.auioc.mods.arnicalib.server.event.impl.SetEyeOfEnderSurvivableEvent;
import org.auioc.mods.notenoughluck.server.command.NELServerCommands;
import org.auioc.mods.notenoughluck.server.event.handler.EatAddEffectHandler;
import org.auioc.mods.notenoughluck.server.event.handler.PlayerLoginHandler;
import org.auioc.mods.notenoughluck.server.event.handler.SetEyeOfEnderSurvivableHandler;
import org.auioc.mods.notenoughluck.server.event.handler.VillagerTradesHandler;
import org.auioc.mods.notenoughluck.server.event.handler.WandererTradesHandler;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NELServerEventHandler {

    @SubscribeEvent
    public static void registerCommands(final RegisterCommandsEvent event) {
        NELServerCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onRegisterVillagerTrades(final VillagerTradesEvent event) {
        VillagerTradesHandler.handle(event);
    }

    @SubscribeEvent
    public static void onRegisterWandererTrades(final WandererTradesEvent event) {
        WandererTradesHandler.handle(event);
    }

    @SubscribeEvent
    public static void onLivingEatAddEffect(final LivingEatAddEffectEvent event) {
        EatAddEffectHandler.handle(event);
    }

    @SubscribeEvent
    public static void onSetEyeOfEnderSurvivable(final SetEyeOfEnderSurvivableEvent event) {
        SetEyeOfEnderSurvivableHandler.handle(event);
    }

    @SubscribeEvent
    public static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent event) {
        PlayerLoginHandler.handle(event);
    }

}
