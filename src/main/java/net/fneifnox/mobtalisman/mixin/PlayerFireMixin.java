package net.fneifnox.mobtalisman.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fneifnox.mobtalisman.item.custom.StriderTalisman;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class PlayerFireMixin {

    @ModifyReturnValue(method = "isFireImmune", at = @At("RETURN"))
    private boolean modifyIsFireImmune(boolean original) {
        if ((Object) this instanceof ServerPlayerEntity player) {
            FluidState state = player.getWorld().getFluidState(player.getBlockPos());
            if (StriderTalisman.playerHasStriderTalismanEquipped(player) && state.getFluid() == Fluids.LAVA) {
                return true; // Temporär feuerimmun
            }
        }
        return original;
    }
}
