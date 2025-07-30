package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.EnderDragonTalisman;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(RandomChanceLootCondition.class)
public abstract class TalismanDropMixin {

    @Shadow
    public abstract LootNumberProvider chance();

    @Inject(method = "test", at = @At("HEAD"), cancellable = true)
    private void modifyChance(LootContext lootContext, CallbackInfoReturnable<Boolean> cir) {
        var killer = lootContext.get(LootContextParameters.LAST_DAMAGE_PLAYER);
        if (!(killer instanceof ServerPlayerEntity player)) return;
        if (EnderDragonTalisman.playerHasEnderDragonTalismanEquipped(player) == true) {
            float originalChance = this.chance().nextFloat(lootContext);
            float increasedChance = Math.min(originalChance * ((CONFIG.enderDragonTalisman.dropchanceForTalisman() + 100) / 100), 1.0f);
            boolean result = lootContext.getRandom().nextFloat() < increasedChance;
            cir.setReturnValue(result);
            cir.cancel();
        }
    }
}
