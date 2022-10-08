package org.auioc.mcmod.notenoughluck.server.event.handler;

import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import org.auioc.mcmod.hulsealib.game.event.server.EyeOfEnderSurvivableEvent;
import org.auioc.mcmod.notenoughluck.server.config.NELServerConfig;
import org.auioc.mcmod.notenoughluck.utils.LuckUtils;

public class SetEyeOfEnderSurvivableHandler {

    public static void handle(final EyeOfEnderSurvivableEvent event) {
        int chance = LuckUtils.getChance(
            event.getPlayer(),
            NELServerConfig.EyeOfEnderSurvivableChance.get(),
            NELServerConfig.EyeOfEnderSurvivableChanceLuckMultiplier.get(),
            NELServerConfig.EyeOfEnderSurvivableChanceUnluckMultiplier.get(),
            NELServerConfig.EyeOfEnderSurvivableChanceBonusMultiplier.get()
        );
        event.setSurvivable((random) -> RandomUtils.percentageChance(chance, random));
    }

}
