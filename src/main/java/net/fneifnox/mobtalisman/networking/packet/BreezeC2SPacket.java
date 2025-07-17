package net.fneifnox.mobtalisman.networking.packet;

import net.fneifnox.mobtalisman.item.custom.BreezeTalisman;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public record BreezeC2SPacket() implements CustomPayload {

    public static final Id<BreezeC2SPacket> ID = new Id<>(Identifier.of("mobtalisman", "breeze_id"));
    public static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public static final PacketCodec<RegistryByteBuf, BreezeC2SPacket> CODEC =
            PacketCodec.of((buf, value) -> {
            }, buf -> new BreezeC2SPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(ServerPlayerEntity player) {

        if (player.isOnGround() && BreezeTalisman.useBoolean(player)) {
            World world = player.getServerWorld();
            WindChargeEntity windCharge = new WindChargeEntity(EntityType.WIND_CHARGE, world);

            windCharge.setOwner(player);

            windCharge.refreshPositionAndAngles(player.getX(), player.getEyeY(), player.getZ(), player.getYaw(), player.getPitch());

            float velocity = 1.5F;
            windCharge.setVelocity(player, 90f, 0f, 0.0F, velocity, 1.0F);

            world.spawnEntity(windCharge);

            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_WIND_CHARGE_THROW, player.getSoundCategory(), 1.0F, 1.0F);

            BreezeTalisman.setBooleanFalse(player);
            BreezeTalisman.SetTickCountTrue(player);
        }
        else if (!player.isOnGround() && BreezeTalisman.useBoolean(player) && CONFIG.windchargeInAir()) {
            player.addVelocity(0, 1, 0);
            player.velocityModified = true;

            World world = player.getServerWorld();
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_WIND_CHARGE_THROW, player.getSoundCategory(), 1.0F, 1.0F);

            BreezeTalisman.setBooleanFalse(player);
            BreezeTalisman.SetTickCountTrue(player);
        }
    }
}


