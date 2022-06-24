package org.auioc.mcmod.notenoughluck.server.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class NELServerConfig {

    public static final ForgeConfigSpec CONFIG;

    public static final IntValue UnseiEffectInterval;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        {
            b.push("unsei");
            {
                b.push("effect");
                UnseiEffectInterval = b.defineInRange("interval", 120, 1, Integer.MAX_VALUE);
                b.pop();
            }
            b.pop();
        }

        CONFIG = b.build();
    }


}
