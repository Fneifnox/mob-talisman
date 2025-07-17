package net.fneifnox.mobtalisman.networking.packet;

import net.fneifnox.mobtalisman.item.custom.CamelTalisman;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public record CamelC2SPacket() implements CustomPayload {

    public static final Id<CamelC2SPacket> ID = new Id<>(Identifier.of("mobtalisman", "camel_id"));
    public static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public static final PacketCodec<RegistryByteBuf, CamelC2SPacket> CODEC =
            PacketCodec.of((buf, value) -> {
            }, buf -> new CamelC2SPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(ServerPlayerEntity player) {

        if (CamelTalisman.useBoolean(player)) {
            float dashstrength = CONFIG.dashStrengthForCamelTalisman();

            // Richtung berechnen
            float yaw = player.getYaw();
            double yawRadiant = Math.toRadians(yaw);
            Vec3d forward = new Vec3d(-Math.sin(yawRadiant), 0, Math.cos(yawRadiant)).normalize();

            // Vertikaler Impuls (wie Sprung)
            double verticalMovement = 1.4285f * 0.375f;

            // Endgültiger Impuls-Vektor
            Vec3d dashVelocity = forward.multiply(dashstrength).add(0.0, verticalMovement, 0.0);

            // Auf den Spieler anwenden
            player.addVelocity(dashVelocity.x, dashVelocity.y, dashVelocity.z);
            player.velocityModified = true;

            CamelTalisman.setBooleanFalse(player);
            CamelTalisman.SetTickCountTrue(player);
        }
    }
}
