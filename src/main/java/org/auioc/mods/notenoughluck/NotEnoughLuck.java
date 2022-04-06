package org.auioc.mods.notenoughluck;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.utils.LogUtil;
import org.auioc.mcmod.arnicalib.utils.java.VersionUtils;
import org.auioc.mods.notenoughluck.client.event.NELClientEventHandler;
import org.auioc.mods.notenoughluck.client.event.NELClientModEventHandler;
import org.auioc.mods.notenoughluck.common.alchemy.NELPotions;
import org.auioc.mods.notenoughluck.common.attribute.NELAttributes;
import org.auioc.mods.notenoughluck.common.block.NELBlocks;
import org.auioc.mods.notenoughluck.common.item.NELItems;
import org.auioc.mods.notenoughluck.common.itemgroup.NELItemGroups;
import org.auioc.mods.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mods.notenoughluck.server.event.NELServerEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NotEnoughLuck.MOD_ID)
public final class NotEnoughLuck {

    public static final String MOD_ID = "notenoughluck";
    public static final String MOD_NAME = "NotEnoughLuck";
    public static final String MAIN_VERSION;
    public static final String FULL_VERSION;

    public static final Logger LOGGER = LogUtil.getLogger(MOD_NAME);
    private static final Marker CORE = LogUtil.getMarker("CORE");

    public NotEnoughLuck() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        modSetup(modEventBus);
        forgeSetup(forgeEventBus);

        final ClientSideOnlySetup ClientSideOnlySetup = new ClientSideOnlySetup(modEventBus, forgeEventBus);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSideOnlySetup::modSetup);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSideOnlySetup::forgeSetup);
    }

    static {
        Pair<String, String> version = VersionUtils.getModVersion(NotEnoughLuck.class);
        MAIN_VERSION = version.getLeft();
        FULL_VERSION = version.getRight();
        LOGGER.info(CORE, "Version: " + MAIN_VERSION + " (" + FULL_VERSION + ")");
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String i18n(String path) {
        return MOD_ID + "." + path;
    }

    private void modSetup(final IEventBus modEventBus) {
        NELPacketHandler.init();
        NELItems.ITEMS.register(modEventBus);
        NELBlocks.BLOCKS.register(modEventBus);
        NELPotions.POTIONS.register(modEventBus);
        NELAttributes.ATTRIBUTES.register(modEventBus);
        modEventBus.register(NELPotions.class);
    }

    private void forgeSetup(final IEventBus forgeEventBus) {
        forgeEventBus.register(NELServerEventHandler.class);
        NELItemGroups.init();
    }

    private class ClientSideOnlySetup {
        private final IEventBus modEventBus;
        private final IEventBus forgeEventBus;

        public ClientSideOnlySetup(final IEventBus modEventBus, final IEventBus forgeEventBus) {
            this.modEventBus = modEventBus;
            this.forgeEventBus = forgeEventBus;
        }

        public void modSetup() {
            modEventBus.register(NELClientModEventHandler.class);
        }

        public void forgeSetup() {
            forgeEventBus.register(NELClientEventHandler.class);
        }
    }

}
