package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.GoatTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(LivingEntity.class)
public class GoatLootMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        Object obj = (Object) this;

        if (obj instanceof GoatEntity goat) {
            if (source.getAttacker() instanceof PlayerEntity player) {
                if (GoatTalisman.playerHasGoatTalismanEquipped(player)) {
                    dropGoatHorn(goat);
                }
            }
        }
    }

    private void dropGoatHorn(GoatEntity goat) {
        if (!goat.getWorld().isClient()) {
            if (goat.getRandom().nextFloat() < (CONFIG.dropchanceForGoatHorn() / 100.0f)) {
                goat.dropStack(goat.getGoatHornStack());
            }
        }
    }
}
