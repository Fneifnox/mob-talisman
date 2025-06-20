package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.PiglinTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Inject(method = "getPreferredTarget", at = @At("HEAD"), cancellable = true)
    private static void cancelTargetingIfAccessoryEquipped(PiglinEntity piglin, CallbackInfoReturnable<Optional<? extends LivingEntity>> cir) {
        ServerPlayerEntity player = piglin.getWorld()
                .getPlayers()
                .stream()
                .filter(p -> piglin.isInRange(p, 16))
                .filter(p -> p instanceof ServerPlayerEntity serverPlayer && PiglinTalisman.playerHasPiglinTalismanEquipped(serverPlayer))
                .map(p -> (ServerPlayerEntity)p)
                .findFirst()
                .orElse(null);

        if (player != null) {
            cir.setReturnValue(Optional.empty());
        }
    }
}
