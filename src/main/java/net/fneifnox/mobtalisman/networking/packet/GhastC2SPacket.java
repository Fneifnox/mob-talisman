package net.fneifnox.mobtalisman.networking.packet;

import net.fneifnox.mobtalisman.item.custom.GhastTalisman;
import net.fneifnox.mobtalisman.util.NonDestructiveFireballEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
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

public record GhastC2SPacket() implements CustomPayload {

    public static final Id<GhastC2SPacket> ID = new Id<>(Identifier.of("mobtalisman", "ghast_id"));
    public static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public static final PacketCodec<RegistryByteBuf, GhastC2SPacket> CODEC =
            PacketCodec.of((buf, value) -> {
            }, buf -> new GhastC2SPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(ServerPlayerEntity player) {

        if (!CONFIG.nonDestructiveFireballs() && GhastTalisman.useBoolean(player)) {
            World world = player.getServerWorld();
            FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);

            fireball.setOwner(player);

            fireball.refreshPositionAndAngles(player.getX(), player.getEyeY(), player.getZ(), player.getYaw(), player.getPitch());

            float velocity = 1.5F;
            fireball.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, velocity, 1.0F);

            world.spawnEntity(fireball);

            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_GHAST_SHOOT, player.getSoundCategory(), 1.0F, 1.0F);

            GhastTalisman.setBooleanFalse(player);
            GhastTalisman.SetTickCountTrue(player);
        }
        else if (CONFIG.nonDestructiveFireballs() && GhastTalisman.useBoolean(player)) {
            World world = player.getServerWorld();
            NonDestructiveFireballEntity fireball = new NonDestructiveFireballEntity(EntityType.FIREBALL, world);

            fireball.setOwner(player);

            fireball.refreshPositionAndAngles(player.getX(), player.getEyeY(), player.getZ(), player.getYaw(), player.getPitch());

            float velocity = 1.5F;
            fireball.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, velocity, 1.0F);

            world.spawnEntity(fireball);

            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_GHAST_SHOOT, player.getSoundCategory(), 1.0F, 1.0F);

            GhastTalisman.setBooleanFalse(player);
            GhastTalisman.SetTickCountTrue(player);
        }
    }
}


