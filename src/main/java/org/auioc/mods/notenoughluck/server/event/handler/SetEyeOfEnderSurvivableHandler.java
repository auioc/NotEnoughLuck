package org.auioc.mods.notenoughluck.server.event.handler;

import org.auioc.mods.arnicalib.server.event.impl.SetEyeOfEnderSurvivableEvent;
import org.auioc.mods.arnicalib.utils.java.RandomUtils;
import org.auioc.mods.notenoughluck.utils.LuckUtils;

public class SetEyeOfEnderSurvivableHandler {

    private static final int DEFAULT_CHANCE = 80;
    private static final int LUCK_MULTIPLIER = 1;
    private static final int UNLUCK_MULTIPLIER = -4;
    private static final int BONUS_MULTIPLIER = 5;

    public static void handle(final SetEyeOfEnderSurvivableEvent event) {
        int chance = LuckUtils.getChance(event.getPlayer(), DEFAULT_CHANCE, LUCK_MULTIPLIER, UNLUCK_MULTIPLIER, BONUS_MULTIPLIER);
        event.setSurvivable((random) -> RandomUtils.percentageChance(chance, random));
    }

}
