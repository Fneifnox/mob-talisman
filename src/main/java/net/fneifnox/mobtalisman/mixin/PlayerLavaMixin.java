package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.SquidTalisman;
import net.fneifnox.mobtalisman.item.custom.StriderTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PlayerLavaMixin {

    @Inject(method = "canWalkOnFluid", at = @At("HEAD"), cancellable = true)
    private void onCanStandOnFluid(net.minecraft.fluid.FluidState state, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (self instanceof PlayerEntity player &&
                StriderTalisman.playerHasStriderTalismanEquipped(player) &&
                state.getFluid() == Fluids.LAVA) {
            cir.setReturnValue(true);
        }
    }
}







