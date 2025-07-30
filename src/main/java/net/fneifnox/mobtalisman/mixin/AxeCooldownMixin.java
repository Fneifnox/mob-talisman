package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.PiglinBruteTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class AxeCooldownMixin {

    @Inject(method = "getAttributeValue", at = @At("HEAD"), cancellable = true)
    private void modifyAttackSpeed(RegistryEntry<EntityAttribute> attribute, CallbackInfoReturnable<Double> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            if (attribute.equals(EntityAttributes.GENERIC_ATTACK_SPEED)) {
                ItemStack stack = player.getMainHandStack();

                if (stack.getItem() instanceof AxeItem && PiglinBruteTalisman.playerHasPiglinBruteTalismanEquipped(player)) {
                    cir.setReturnValue(1.6);
                }
            }
        }
    }
}
