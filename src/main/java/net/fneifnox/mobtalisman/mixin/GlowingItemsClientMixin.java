package net.fneifnox.mobtalisman.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fneifnox.mobtalisman.item.custom.GlowSquidTalisman;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class GlowingItemsClientMixin
{
    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    public void EntitiesShouldGlow(Entity entity, CallbackInfoReturnable<Boolean> cir)
    {
        if (!(entity.getWorld() instanceof ClientWorld)) return;

        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity localPlayer = client.player;

        if (localPlayer == null) return;
        if (CONFIG.glowingEffectForEveryEntity()) {
            if (entity instanceof Entity && GlowSquidTalisman.useBoolean(localPlayer)) {
                cir.setReturnValue(true);
            }
        }
        else if (!CONFIG.glowingEffectForEveryEntity()) {
            if (entity instanceof ItemEntity && GlowSquidTalisman.useBoolean(localPlayer)) {
                cir.setReturnValue(true);
            }
        }
    }
}
