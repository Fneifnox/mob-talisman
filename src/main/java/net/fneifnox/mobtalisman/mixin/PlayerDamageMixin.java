package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.ArmadilloTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(LivingEntity.class)
public abstract class PlayerDamageMixin {

    @Inject(method = "damage", at = @At("RETURN"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (!(self instanceof PlayerEntity player)) return;
        if (cir.getReturnValue()) {
            if (ArmadilloTalisman.equippedPlayers.getOrDefault(player.getUuid(), false) == true && ArmadilloTalisman.resistanceGiven.getOrDefault(player.getUuid(), false) == false) {
                int duration = CONFIG.armadilloTalisman.durationForArmadilloTalisman();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, (int) player.getWorld().getTickManager().getTickRate() * duration, 9, false, false, false));
                ArmadilloTalisman.resistanceGiven.put(player.getUuid(), true);
                ArmadilloTalisman.setBooleanTrue(player);
            }
        }
    }
}
