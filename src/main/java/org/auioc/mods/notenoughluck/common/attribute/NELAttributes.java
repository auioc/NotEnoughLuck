package org.auioc.mods.notenoughluck.common.attribute;

import org.auioc.mods.notenoughluck.NotEnoughLuck;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class NELAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, NotEnoughLuck.MOD_ID);

    public static final RegistryObject<Attribute> UNSEI = ATTRIBUTES.register(
        "unsei",
        () -> new RangedAttribute("notenoughluck.unsei", 0.0D, -4.0D, 4.0D).setSyncable(true)
    );

}
