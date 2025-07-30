package net.fneifnox.mobtalisman.mixin;

import net.fneifnox.mobtalisman.item.custom.WitherSkeletonTalisman;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

@Mixin(WitherSkeletonEntity.class)
public class WitherSkeletonSkullMixin {

    @Inject(method = "dropEquipment", at = @At("HEAD"), cancellable = true)
    public void modifyWitherSkeletonDrops(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci) {

        Entity killer = source.getAttacker();
        Entity witherSkeleton = (WitherSkeletonEntity)(Object)(this);

        if (killer instanceof PlayerEntity playerKiller) {
            if (WitherSkeletonTalisman.playerHasWitherSkeletonTalismanEquipped(playerKiller)) {
                int lootingLevel = 0;
                if (killer instanceof LivingEntity lKiller) {
                    ItemStack mainHand = lKiller.getMainHandStack();

                    RegistryWrapper.WrapperLookup registries = world.getRegistryManager();
                    RegistryWrapper<Enchantment> enchantmentRegistry = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

                    RegistryKey<Enchantment> lootingKey = Enchantments.LOOTING;
                    RegistryEntry<Enchantment> lootingEntry = enchantmentRegistry.getOrThrow(lootingKey);

                    lootingLevel = EnchantmentHelper.getLevel(lootingEntry, mainHand);
                }

                float chance = ((lootingLevel / 100f) + 0.025f) * ((CONFIG.witherSkeletonTalisman.increasedWitherSkeletonSkullChanceForWitherSkeletonTalisman() / 100) + 1);

                if (killer.getRandom().nextFloat() < chance) {
                    witherSkeleton.dropStack(new ItemStack(Items.WITHER_SKELETON_SKULL, 1));
                }
                ci.cancel();
            }
        }
    }
}
