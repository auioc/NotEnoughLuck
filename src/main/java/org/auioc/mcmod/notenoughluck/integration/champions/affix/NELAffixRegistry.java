package org.auioc.mcmod.notenoughluck.integration.champions.affix;

import static org.auioc.mcmod.notenoughluck.NotEnoughLuck.LOGGER;
import org.auioc.mcmod.notenoughluck.integration.NELIntegration;
import org.auioc.mcmod.notenoughluck.integration.champions.affix.impl.DispelAffix;
import org.auioc.mcmod.notenoughluck.integration.champions.affix.impl.HurricaneAffix;
import org.auioc.mcmod.notenoughluck.integration.champions.affix.impl.JinxAffix;
import org.auioc.mcmod.notenoughluck.integration.champions.affix.impl.RealityAffix;
import top.theillusivec4.champions.Champions;

public class NELAffixRegistry {

    public static void register() {
        LOGGER.info(NELIntegration.MARKER, "Mod Champions is loaded, register NEL affixes");

        Champions.API.registerAffixes(
            new JinxAffix(),
            new DispelAffix(),
            new RealityAffix(),
            new HurricaneAffix()
        );
    }

}
