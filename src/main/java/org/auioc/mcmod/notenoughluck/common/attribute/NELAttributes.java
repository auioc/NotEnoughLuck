package org.auioc.mcmod.notenoughluck.common.attribute;

import org.auioc.mcmod.notenoughluck.NotEnoughLuck;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class NELAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, NotEnoughLuck.MOD_ID);

    public static final RegistryObject<Attribute> UNSEI = ATTRIBUTES.register(
        "unsei",
        () -> new RangedAttribute("notenoughluck.unsei", 0.0D, -4.0D, 4.0D).setSyncable(true)
    );

    // ====================================================================== //

    /**
     * @see org.auioc.mcmod.notenoughluck.Initialization.CommonSetup#modSetup
     */
    public static void onEntityAttributeModification(final EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, UNSEI.get());
        event.add(EntityType.PIGLIN, Attributes.LUCK);
    }

}
