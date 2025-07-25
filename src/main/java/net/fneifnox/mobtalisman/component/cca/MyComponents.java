package net.fneifnox.mobtalisman.component.cca;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public final class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<BooleanComponent> HAS_SPIDER_TALISMAN =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "has_spider_talisman"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> HAS_STRIDER_TALISMAN =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "has_strider_talisman"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> HAS_GLOW_SQUID_TALISMAN =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "has_glow_squid_talisman"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> BREEZE_TALISMAN_ABILITY_READY =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "breeze_talisman_ability_ready"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> GHAST_TALISMAN_ABILITY_READY =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "ghast_talisman_ability_ready"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> CAMEL_TALISMAN_ABILITY_READY =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "camel_talisman_ability_ready"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> IRON_GOLEM_TALISMAN_ABILITY_READY =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "iron_golem_talisman_ability_ready"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> DONKEY_TALISMAN_ABILITY_READY =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "donkey_talisman_ability_ready"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> ARMADILLO_FLOATING_ITEM =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "armadillo_floating_item"), BooleanComponent.class);

    public static final ComponentKey<BooleanComponent> WITCH_FLOATING_ITEM =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "witch_floating_item"), BooleanComponent.class);

    public static final ComponentKey<Vec3dComponent> ENDERMAN_POSITION =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "enderman_position"), Vec3dComponent.class);

    public static final ComponentKey<BooleanComponent> ENDERMAN_EQUIPPED =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "enderman_equipped"), BooleanComponent.class);

    public static final ComponentKey<ItemStacksComponent> DONKEY_ITEMSTACKS_INVENTORY =
            ComponentRegistry.getOrCreate(Identifier.of("mob-talisman", "donkey_itemstacks_inventory"), ItemStacksComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(PlayerEntity.class, HAS_SPIDER_TALISMAN, player -> new SpiderBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, ENDERMAN_POSITION, player -> new EndermanVec3dComponent(player));
        registry.registerFor(PlayerEntity.class, ENDERMAN_EQUIPPED, player -> new EndermanBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, HAS_STRIDER_TALISMAN, player -> new StriderBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, BREEZE_TALISMAN_ABILITY_READY, player -> new BreezeBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, GHAST_TALISMAN_ABILITY_READY, player -> new GhastBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, CAMEL_TALISMAN_ABILITY_READY, player -> new CamelBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, IRON_GOLEM_TALISMAN_ABILITY_READY, player -> new IronGolemBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, DONKEY_TALISMAN_ABILITY_READY, player -> new DonkeyBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, HAS_GLOW_SQUID_TALISMAN, player -> new GlowSquidBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, ARMADILLO_FLOATING_ITEM, player -> new ArmadilloBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, WITCH_FLOATING_ITEM, player -> new WitchBooleanComponent(player));
        registry.registerFor(PlayerEntity.class, DONKEY_ITEMSTACKS_INVENTORY, player -> new DonkeyItemStacksComponent(player));
    }
}
