package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.PufferfishTalisman;
import net.fneifnox.mobtalisman.item.custom.SquidTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(LivingEntity.class)
public class PlayerAttackEffectMixin {

    @Shadow @Nullable private LivingEntity attacker;

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (self instanceof ServerPlayerEntity attackedPlayer) {
            if (source.getAttacker() instanceof ServerPlayerEntity attacker) {
                if (SquidTalisman.playerHasSquidTalismanEquipped(attacker)) {
                    int duration = CONFIG.durationForSquidTalisman();
                    attackedPlayer.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.BLINDNESS,
                            20 * duration,
                            0,
                            false, true, true

                    ));
                }


                if (PufferfishTalisman.playerHasPufferfishTalismanEquipped(attackedPlayer)) {
                    int duration = CONFIG.durationForPufferfishTalisman();
                    attacker.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.POISON,
                            20 * duration,
                            0,
                            false, true, true

                    ));
                }
            }
        }
    }
}