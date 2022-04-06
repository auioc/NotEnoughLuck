package org.auioc.mods.notenoughluck.client.network;

import org.auioc.mcmod.arnicalib.api.game.network.IHPacket;
import org.auioc.mods.notenoughluck.client.gui.screen.tungshing.TungShingScreenUtils;
import org.auioc.mods.notenoughluck.client.unsei.ClientUnseiCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;

public class UpdateTungShingPacket implements IHPacket {

    private final int[] day;
    private final int[] unsei;
    private final boolean classic;

    public UpdateTungShingPacket(int[] day, int[] unsei, boolean classic) {
        this.day = day;
        this.unsei = unsei;
        this.classic = classic;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handle(Context ctx) {
        for (int i = 0; i < 3; i++) {
            ClientUnseiCache.set(this.day[i], this.unsei[i]);
        }
        TungShingScreenUtils.open(true, classic).updateUnsei(this.day, this.unsei);
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeVarIntArray(this.day);
        buffer.writeVarIntArray(this.unsei);
        buffer.writeBoolean(this.classic);
    }

    public static UpdateTungShingPacket decode(FriendlyByteBuf buffer) {
        return new UpdateTungShingPacket(buffer.readVarIntArray(), buffer.readVarIntArray(), buffer.readBoolean());
    }

}
