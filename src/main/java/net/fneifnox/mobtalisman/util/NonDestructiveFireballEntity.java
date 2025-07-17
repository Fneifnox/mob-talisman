package net.fneifnox.mobtalisman.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NonDestructiveFireballEntity extends AbstractFireballEntity {

    public NonDestructiveFireballEntity(EntityType<? extends net.minecraft.entity.projectile.FireballEntity> entityType, World world) {
        super(entityType, world);
    }

    public NonDestructiveFireballEntity(World world, LivingEntity owner, Vec3d velocity) {
        super(EntityType.FIREBALL, owner, velocity, world);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.discard();
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        World var3 = this.getWorld();
        if (var3 instanceof ServerWorld serverWorld) {
            Entity entity = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            DamageSource damageSource = this.getDamageSources().fireball(this, entity2);
            entity.damage(damageSource, 6.0F);
            EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource);
        }
    }
}
