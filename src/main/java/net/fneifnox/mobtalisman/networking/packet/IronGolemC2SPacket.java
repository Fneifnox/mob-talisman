package net.fneifnox.mobtalisman.networking.packet;

import net.fneifnox.mobtalisman.item.custom.IronGolemTalisman;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public record IronGolemC2SPacket() implements CustomPayload {

    public static final Id<IronGolemC2SPacket> ID = new Id<>(Identifier.of("mobtalisman", "iron_golem_id"));
    public static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public static final PacketCodec<RegistryByteBuf, IronGolemC2SPacket> CODEC =
            PacketCodec.of((buf, value) -> {
            }, buf -> new IronGolemC2SPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(ServerPlayerEntity player) {

        if (IronGolemTalisman.useBoolean(player)) {
            System.out.println("TEST 1");
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, -1, CONFIG.absorptionHeartsForIronGolemTalisman() - 1, false, false, false));

            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_BEACON_POWER_SELECT, player.getSoundCategory(), 1.0F, 1.0F);
            IronGolemTalisman.setBooleanFalse(player);
            IronGolemTalisman.SetTickCountTrue(player);
        }
    }
}
