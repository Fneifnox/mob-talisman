package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.ZombieVillagerTalisman;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(VillagerEntity.class)
public class VillagerTradingMixin {

    @Inject(method = "prepareOffersFor(Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At("RETURN"))
    private void onPrepareOffersFor(PlayerEntity player, CallbackInfo ci) {
        // Vanilla Code läuft vorher komplett durch
        // Hier kannst du deinen eigenen Code hinzufügen

        VillagerEntity villager = (VillagerEntity)(Object)this;

        for (TradeOffer finalTradeOffer : villager.getOffers()) {
            if (ZombieVillagerTalisman.playerHasZombieVillagerTalismanEquipped(player)) {
                int price = (int)Math.floor(((double) CONFIG.discountForZombieVillagerTalisman() / 100)
                        * (double)finalTradeOffer.getOriginalFirstBuyItem().getCount());
                finalTradeOffer.increaseSpecialPrice(-price);
            }
        }
    }
}
