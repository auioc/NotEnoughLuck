package org.auioc.mods.notenoughluck.common.block;

import java.util.function.Supplier;
import org.auioc.mods.notenoughluck.NotEnoughLuck;
import org.auioc.mods.notenoughluck.common.block.impl.TungShingBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NELBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NotEnoughLuck.MOD_ID);

    private static RegistryObject<Block> register(String id, Supplier<? extends Block> sup) {
        return BLOCKS.register(id, sup);
    }

    public static final RegistryObject<Block> TUNG_SHING_BLOCK = register("tung_shing", TungShingBlock::new);

}
