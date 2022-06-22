package org.auioc.mcmod.notenoughluck.server.event;

import org.auioc.mcmod.arnicalib.server.event.impl.LivingEatAddEffectEvent;
import org.auioc.mcmod.arnicalib.server.event.impl.SetEyeOfEnderSurvivableEvent;
import org.auioc.mcmod.notenoughluck.server.command.NELServerCommands;
import org.auioc.mcmod.notenoughluck.server.event.handler.EatAddEffectHandler;
import org.auioc.mcmod.notenoughluck.server.event.handler.PlayerLoginHandler;
import org.auioc.mcmod.notenoughluck.server.event.handler.PotionAddedHandler;
import org.auioc.mcmod.notenoughluck.server.event.handler.ServerTickHandler;
import org.auioc.mcmod.notenoughluck.server.event.handler.SetEyeOfEnderSurvivableHandler;
import org.auioc.mcmod.notenoughluck.server.event.handler.VillagerTradesHandler;
import org.auioc.mcmod.notenoughluck.server.event.handler.WandererTradesHandler;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;
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

    @SubscribeEvent
    public static void onPotionAdded(final PotionAddedEvent event) {
        PotionAddedHandler.handle(event);
    }

    public static void onServerTick(final ServerTickEvent event) {
        if (event.phase == Phase.START) {
            ServerTickHandler.handleStartPhase();
        } else {
            ServerTickHandler.handleEndPhase();
        }
    }

}
