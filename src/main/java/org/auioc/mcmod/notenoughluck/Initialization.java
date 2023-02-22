package org.auioc.mcmod.notenoughluck;

import org.auioc.mcmod.notenoughluck.client.event.NELClientEventHandler;
import org.auioc.mcmod.notenoughluck.client.event.NELClientModEventHandler;
import org.auioc.mcmod.notenoughluck.common.alchemy.NELPotions;
import org.auioc.mcmod.notenoughluck.common.attribute.NELAttributes;
import org.auioc.mcmod.notenoughluck.common.block.NELBlocks;
import org.auioc.mcmod.notenoughluck.common.effect.NELMobEffects;
import org.auioc.mcmod.notenoughluck.common.item.NELItems;
import org.auioc.mcmod.notenoughluck.common.itemgroup.NELItemGroups;
import org.auioc.mcmod.notenoughluck.common.network.NELPacketHandler;
import org.auioc.mcmod.notenoughluck.server.config.NELServerConfig;
import org.auioc.mcmod.notenoughluck.server.event.NELServerEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public final class Initialization {

    private Initialization() {}

    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, NELServerConfig.CONFIG);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        final ClientSideOnlySetup ClientSideOnlySetup = new ClientSideOnlySetup(modEventBus, forgeEventBus);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSideOnlySetup::registerConfig);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSideOnlySetup::modSetup);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSideOnlySetup::forgeSetup);

        final CommonSetup CommonSetup = new CommonSetup(modEventBus, forgeEventBus);
        CommonSetup.registerConfig();
        CommonSetup.modSetup();
        CommonSetup.forgeSetup();
    }


    private final static class CommonSetup {

        private final IEventBus modEventBus;
        private final IEventBus forgeEventBus;

        public CommonSetup(final IEventBus modEventBus, final IEventBus forgeEventBus) {
            this.modEventBus = modEventBus;
            this.forgeEventBus = forgeEventBus;
        }

        public void registerConfig() {}

        private void modSetup() {
            NELPacketHandler.init();
            NELItems.ITEMS.register(modEventBus);
            NELBlocks.BLOCKS.register(modEventBus);
            NELPotions.POTIONS.register(modEventBus);
            NELAttributes.ATTRIBUTES.register(modEventBus);
            NELMobEffects.MOB_EFFECTS.register(modEventBus);
            modEventBus.register(NELPotions.class);
            modEventBus.addListener(NELAttributes::onEntityAttributeModification);
        }

        private void forgeSetup() {
            forgeEventBus.register(NELServerEventHandler.class);
            NELItemGroups.init();
        }

    }


    private final static class ClientSideOnlySetup {

        private final IEventBus modEventBus;
        private final IEventBus forgeEventBus;

        public ClientSideOnlySetup(final IEventBus modEventBus, final IEventBus forgeEventBus) {
            this.modEventBus = modEventBus;
            this.forgeEventBus = forgeEventBus;
        }

        public void registerConfig() {}

        public void modSetup() {
            modEventBus.register(NELClientModEventHandler.class);
        }

        public void forgeSetup() {
            forgeEventBus.register(NELClientEventHandler.class);
        }

    }

}
