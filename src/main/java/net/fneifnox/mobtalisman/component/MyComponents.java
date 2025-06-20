package net.fneifnox.mobtalisman.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public final class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<BooleanComponent> HAS_SPIDER_TALISMAN =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "has_spider_talisman"), BooleanComponent.class);

    public static final ComponentKey<Vec3dComponent> ENDERMAN_POSITION =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "enderman_position"), Vec3dComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(PlayerEntity.class, HAS_SPIDER_TALISMAN, player -> new SpiderBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, ENDERMAN_POSITION, player -> new EndermanVec3dComponent(player));
    }
}
