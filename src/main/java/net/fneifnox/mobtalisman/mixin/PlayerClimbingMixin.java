package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.SpiderTalisman;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(LivingEntity.class)
public class PlayerClimbingMixin {


    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void SpiderClimbing(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!(self instanceof PlayerEntity player)) return;
        if (!SpiderTalisman.useBoolean(player)) return;

        if (!CONFIG.spiderTalisman.jumpForWallClimbing()) {
            if (self.horizontalCollision) {
                cir.setReturnValue(true);
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    Identifier id = Identifier.of("mob-talisman", "custom/i_think_spider_bit_me");
                    AdvancementEntry entry = Objects.requireNonNull(serverPlayer.getServer()).getAdvancementLoader().get(id);
                    serverPlayer.getAdvancementTracker().grantCriterion(entry, "climbing");
                }
            }
        }

        else if (CONFIG.spiderTalisman.jumpForWallClimbing()) {
            if (self.horizontalCollision && !self.isOnGround()) {
                cir.setReturnValue(true);
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    Identifier id = Identifier.of("mob-talisman", "custom/i_think_spider_bit_me");
                    AdvancementEntry entry = Objects.requireNonNull(serverPlayer.getServer()).getAdvancementLoader().get(id);
                    serverPlayer.getAdvancementTracker().grantCriterion(entry, "climbing");
                }
            }
        }
    }
}
