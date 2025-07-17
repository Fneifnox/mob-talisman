package net.fneifnox.mobtalisman.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fneifnox.mobtalisman.networking.packet.BreezeC2SPacket;
import net.fneifnox.mobtalisman.networking.packet.CamelC2SPacket;
import net.fneifnox.mobtalisman.networking.packet.GhastC2SPacket;
import net.fneifnox.mobtalisman.networking.packet.IronGolemC2SPacket;

public class ModMessages {

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(BreezeC2SPacket.ID, (payload, context) -> {
            context.player().getServer().execute(() -> payload.receive(context.player()));
        });
        ServerPlayNetworking.registerGlobalReceiver(GhastC2SPacket.ID, (payload, context) -> {
            context.player().getServer().execute(() -> payload.receive(context.player()));
        });
        ServerPlayNetworking.registerGlobalReceiver(CamelC2SPacket.ID, (payload, context) -> {
            context.player().getServer().execute(() -> payload.receive(context.player()));
        });
        ServerPlayNetworking.registerGlobalReceiver(IronGolemC2SPacket.ID, (payload, context) -> {
            context.player().getServer().execute(() -> payload.receive(context.player()));
        });
    }

    public static void registerS2CPackets() {

    }
}
