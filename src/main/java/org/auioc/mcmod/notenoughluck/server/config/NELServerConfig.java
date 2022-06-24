package org.auioc.mcmod.notenoughluck.server.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class NELServerConfig {

    public static final ForgeConfigSpec CONFIG;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        CONFIG = b.build();
    }

}
