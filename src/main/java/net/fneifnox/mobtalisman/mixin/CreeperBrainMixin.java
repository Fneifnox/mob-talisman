package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.CreeperTalisman;
import net.fneifnox.mobtalisman.mixin.accessor.GoalSelectorAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperBrainMixin {

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void dontTargetPlayer(LivingEntity target, CallbackInfo ci) {
        if (target instanceof PlayerEntity player) {
            if (CreeperTalisman.playerHasCreeperTalismanEquipped(player)) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void forgetTargetIfPlayerHasTalisman(CallbackInfo ci) {
        CreeperEntity self = (CreeperEntity)(Object) this;
        LivingEntity target = self.getTarget();

        if (target instanceof PlayerEntity player) {
            if (CreeperTalisman.playerHasCreeperTalismanEquipped(player)) {
                self.setTarget(null);
            }
        }
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    public void fearOfPlayers(CallbackInfo ci) {
        PathAwareEntity creeper = (PathAwareEntity) (Object) this;
        GoalSelector goalSelector = ((GoalSelectorAccessor) this).getGoalSelector();

        goalSelector.add(3, new FleeEntityGoal<>(
                creeper,
                PlayerEntity.class,
                6f,
                1,
                1.2,
                player -> CreeperTalisman.playerHasCreeperTalismanEquipped((PlayerEntity) player)
        ));
    }
}
