package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.VillagerTalisman;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public class MobLootMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        Object obj = (Object) this;

        if (obj instanceof MobEntity mob) {
            if (source.getAttacker() instanceof PlayerEntity player) {
                if (VillagerTalisman.playerHasVillagerTalismanEquipped(player)) {
                    dropEmeralds(mob, player);
                }
            }
        }
    }

    private void dropEmeralds(MobEntity mob, PlayerEntity player) {
        if (!mob.getWorld().isClient()) {
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Identifier id = Identifier.of("mob-talisman", "custom/villagers_touch");
                AdvancementEntry entry = Objects.requireNonNull(serverPlayer.getServer()).getAdvancementLoader().get(id);
                serverPlayer.getAdvancementTracker().grantCriterion(entry, "killed");
            }
            int randomValue = mob.getRandom().nextInt(3);
            if (randomValue <= 1) {
                mob.dropStack(new ItemStack(Items.EMERALD, 1));
            }
            else {
                mob.dropStack(new ItemStack(Items.EMERALD, 2));
            }
        }
    }
}
