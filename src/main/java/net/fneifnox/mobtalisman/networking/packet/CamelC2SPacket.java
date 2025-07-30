package net.fneifnox.mobtalisman.networking.packet;

import net.fneifnox.mobtalisman.item.custom.CamelTalisman;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
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
            float dashStrength;
            if (player.isFallFlying()) {
                dashStrength = CONFIG.camelTalisman.dashStrengthForCamelTalisman() * (1 - (CONFIG.camelTalisman.reducedDashStrengthWithElytraForCamelTalisman() / 100));
            }
            else {
                dashStrength = CONFIG.camelTalisman.dashStrengthForCamelTalisman();
            }

            float yaw = player.getYaw();
            double yawRadiant = Math.toRadians(yaw);
            Vec3d forward = new Vec3d(-Math.sin(yawRadiant), 0, Math.cos(yawRadiant)).normalize();

            double verticalMovement = 1.4285f * 0.375f;

            Vec3d dashVelocity = forward.multiply(dashStrength).add(0.0, verticalMovement, 0.0);

            player.addVelocity(dashVelocity.x, dashVelocity.y, dashVelocity.z);
            player.velocityModified = true;

            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_CAMEL_DASH, player.getSoundCategory(), 1.0F, 1.0F);

            CamelTalisman.setBooleanFalse(player);
            CamelTalisman.SetTickCountTrue(player);
        }
    }
}
