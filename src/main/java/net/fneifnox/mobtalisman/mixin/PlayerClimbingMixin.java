package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.SpiderTalisman;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(LivingEntity.class)
public class PlayerClimbingMixin {


    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void SpiderClimbing(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!(self instanceof PlayerEntity player)) return;
        System.out.println("Spider Talisman Mixin 2");
        if (!SpiderTalisman.useBoolean(player)) return;
        System.out.println("Spider Talisman Mixin 3");

        if (!CONFIG.jumpForWallClimbing()) {
            System.out.println("Spider Talisman Mixin 4");
            if (self.horizontalCollision) {
                System.out.println("Spider Talisman Mixin 5");
                cir.setReturnValue(true);
            }
        }

        else if (CONFIG.jumpForWallClimbing()) {
            System.out.println("Spider Talisman Mixin 6");
            if (self.horizontalCollision && !self.isOnGround()) {
                System.out.println("Spider Talisman Mixin 7");
                cir.setReturnValue(true);
            }
        }
    }
}
