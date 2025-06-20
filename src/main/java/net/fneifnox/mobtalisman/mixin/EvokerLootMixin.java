package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.EvokerTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class EvokerLootMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        Object obj = (Object) this;

        if (obj instanceof EvokerEntity evoker) {
            if (source.getAttacker() instanceof PlayerEntity player) {
                if (EvokerTalisman.playerHasEvokerTalismanEquipped(player)) {
                    dropTwoTotems(evoker);
                }
            }
        }
    }

    private void dropTwoTotems(EvokerEntity evoker) {
        if (!evoker.getWorld().isClient()) {
            evoker.dropStack(new ItemStack(Items.TOTEM_OF_UNDYING, 1));
        }
    }
}






