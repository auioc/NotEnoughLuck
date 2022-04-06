package org.auioc.mcmod.notenoughluck.api.capability;

import org.apache.commons.lang3.tuple.Pair;
import org.auioc.mcmod.notenoughluck.common.unsei.UnseiFortune;
import org.auioc.mcmod.notenoughluck.common.unsei.UnseiPrefix;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IUnseiCapability extends INBTSerializable<CompoundTag> {

    Pair<UnseiPrefix, UnseiFortune> getUnseiPair();

}
