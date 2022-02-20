package org.auioc.mods.notenoughluck.client.network;

import java.util.Map;
import org.auioc.mods.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.notenoughluck.client.gui.screen.TungShingScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;

public class UpdateTungShingScreenPacket implements IHPacket {

    private final Map<Integer, Integer> unseiMap;

    public UpdateTungShingScreenPacket(Map<Integer, Integer> unseiMap) {
        this.unseiMap = unseiMap;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handle(Context ctx) {
        TungShingScreen screen = TungShingScreen.open(true);
        screen.updateUnsei(this.unseiMap);
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeMap(this.unseiMap, FriendlyByteBuf::writeInt, FriendlyByteBuf::writeInt);
    }

    public static UpdateTungShingScreenPacket decode(FriendlyByteBuf buffer) {
        return new UpdateTungShingScreenPacket(buffer.readMap(FriendlyByteBuf::readInt, FriendlyByteBuf::readInt));
    }

}
