package org.auioc.mcmod.notenoughluck.server.config;

import java.util.function.Consumer;
import org.auioc.mcmod.notenoughluck.common.item.impl.CommonDiceItem;
import org.auioc.mcmod.notenoughluck.common.item.impl.DiceOfTycheItem;
import org.auioc.mcmod.notenoughluck.common.item.impl.TungShingItem;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public final class NELServerConfig {

    public static final ForgeConfigSpec CONFIG;

    public static final IntValue UnseiEffectInterval;

    public static final IntValue EyeOfEnderSurvivableChance;
    public static final IntValue EyeOfEnderSurvivableChanceLuckMultiplier;
    public static final IntValue EyeOfEnderSurvivableChanceUnluckMultiplier;
    public static final IntValue EyeOfEnderSurvivableChanceBonusMultiplier;

    public static final IntValue IndulgenceEmeraldCost;
    public static final IntValue IndulgenceSaleCount;
    public static final IntValue IndulgenceMaxTrades;
    public static final IntValue IndulgenceVillagerXp;
    public static final DoubleValue IndulgencePriceMultiplier;

    public static final IntValue CloverEmeraldCost;
    public static final IntValue CloverSaleCount;
    public static final IntValue CloverMaxTrades;
    public static final IntValue CloverVillagerXp;
    public static final DoubleValue CloverPriceMultiplier;

    static {
        final ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

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
            b.push("end_of_eye_survivable_chance");
            EyeOfEnderSurvivableChance = b.defineInRange("default", 80, 0, 100);
            EyeOfEnderSurvivableChanceLuckMultiplier = b.defineInRange("luck_multiplier", 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            EyeOfEnderSurvivableChanceUnluckMultiplier = b.defineInRange("unluck_multiplier", -4, Integer.MIN_VALUE, Integer.MAX_VALUE);
            EyeOfEnderSurvivableChanceBonusMultiplier = b.defineInRange("bonus_multiplier", 5, Integer.MIN_VALUE, Integer.MAX_VALUE);
            b.pop();
        }

        {
            b.push("trade");
            {
                b.push("clover");
                CloverEmeraldCost = b.defineInRange("emerald_cost", 7, 1, 64);
                CloverSaleCount = b.defineInRange("sale_count", 1, 1, 64);
                CloverMaxTrades = b.defineInRange("max_trades", 1, 1, Integer.MAX_VALUE);
                CloverVillagerXp = b.defineInRange("villager_xp", 1, 0, Integer.MAX_VALUE);
                CloverPriceMultiplier = b.defineInRange("price_multiplier", 0.05D, 0.0D, Double.MAX_VALUE);
                b.pop();
            }
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

        {
            b.push("item");

            {
                b.push("dice");
                {
                    push(b, "common", CommonDiceItem.Config::build);
                    push(b, "tyche", DiceOfTycheItem.Config::build);
                }
                b.pop();
            }

            push(b, "tung_shing", TungShingItem.Config::build);

            b.pop();
        }

        CONFIG = b.build();
    }

    private static void push(ForgeConfigSpec.Builder builder, String path, Consumer<ForgeConfigSpec.Builder> subBuilder) {
        builder.push(path);
        subBuilder.accept(builder);
        builder.pop();
    }

}
