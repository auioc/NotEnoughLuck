package org.auioc.mcmod.notenoughluck.server.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class NELServerConfig {

    public static final ForgeConfigSpec CONFIG;

    public static final IntValue UnseiEffectInterval;

    public static final IntValue IndulgenceEmeraldCost;
    public static final IntValue IndulgenceSaleCount;
    public static final IntValue IndulgenceMaxTrades;
    public static final IntValue IndulgenceVillagerXp;
    public static final DoubleValue IndulgencePriceMultiplier;

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

        {
            b.push("trade");
            {
                b.push("indulgence");
                IndulgenceEmeraldCost = b.defineInRange("emerald_cost", 32, 1, 64);
                IndulgenceSaleCount = b.defineInRange("sale_count", 1, 1, 64);
                IndulgenceMaxTrades = b.defineInRange("max_trades", 2, 1, Integer.MAX_VALUE);
                IndulgenceVillagerXp = b.defineInRange("villager_xp", 30, 0, Integer.MAX_VALUE);
                IndulgencePriceMultiplier = b.defineInRange("price_multiplier", 0.25D, 0.0D, Double.MAX_VALUE);
                b.pop();
            }

            b.pop();
        }

        CONFIG = b.build();
    }


}
