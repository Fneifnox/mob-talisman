package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.RavagerTalisman;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(LivingEntity.class)
public class MobDeathMixin {

    int amplifier = 0;

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void setCombo(DamageSource damageSource, CallbackInfo ci) {
        Entity entity = damageSource.getAttacker();
        LivingEntity killedEntity = (LivingEntity)(Object)(this);

        if (killedEntity instanceof MobEntity) {
            if (entity instanceof PlayerEntity player) {
                if (RavagerTalisman.playerHasRavagerTalismanEquipped(player)) {
                    if (!player.hasStatusEffect(StatusEffects.STRENGTH)) {
                        amplifier = 0;
                    }
                    else {
                        amplifier = player.getStatusEffect(StatusEffects.STRENGTH).getAmplifier();
                        amplifier += 1;
                    }
                    if (amplifier == 9) {
                        if (player instanceof ServerPlayerEntity serverPlayer) {
                            Identifier id = Identifier.of("mob-talisman", "custom/infinite_power");
                            AdvancementEntry entry = Objects.requireNonNull(serverPlayer.getServer()).getAdvancementLoader().get(id);
                            serverPlayer.getAdvancementTracker().grantCriterion(entry, "combo_10");
                        }
                    }
                    // To prevent the strength amplifier go back to Strength I
                    if (amplifier >= 10) {
                        amplifier = 9;
                    }
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, CONFIG.ravagerTalisman.comboDurationForRavagerTalisman() * (int) player.getWorld().getTickManager().getTickRate(), amplifier, true, false, true));
                }
            }
        }
    }
}
