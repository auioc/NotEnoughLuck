package org.auioc.mods.notenoughluck;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.api.java.data.Tuple;
import org.auioc.mods.arnicalib.utils.LogUtil;
import org.auioc.mods.arnicalib.utils.java.JarUtils;
import org.auioc.mods.notenoughluck.common.alchemy.PotionRegistry;
import org.auioc.mods.notenoughluck.common.itemgroup.ItemGroupRegistry;
import org.auioc.mods.notenoughluck.server.event.ServerEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NotEnoughLuck.MOD_ID)
public final class NotEnoughLuck {

    public static final String MOD_ID = "notenoughluck";
    public static final String MOD_NAME = "NotEnoughLuck";
    public static String MAIN_VERSION;
    public static String FULL_VERSION;

    public static final Logger LOGGER = LogUtil.getLogger(MOD_NAME);
    private static final Marker CORE = LogUtil.getMarker("CORE");

    public NotEnoughLuck() {
        Tuple<String, String> version = JarUtils.getModVersion(getClass());
        MAIN_VERSION = version.getA();
        FULL_VERSION = version.getB();
        LOGGER.info(CORE, "Version: " + MAIN_VERSION + " (" + FULL_VERSION + ")");

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        modSetup(modEventBus);
        forgeSetup(forgeEventBus);
    }

    private void modSetup(final IEventBus modEventBus) {
        PotionRegistry.POTIONS.register(modEventBus);
        modEventBus.register(PotionRegistry.class);
    }

    private void forgeSetup(final IEventBus forgeEventBus) {
        forgeEventBus.register(ServerEventHandler.class);
        ItemGroupRegistry.init();
    }

}
