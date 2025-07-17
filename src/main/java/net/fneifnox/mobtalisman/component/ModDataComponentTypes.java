package net.fneifnox.mobtalisman.component;

import com.mojang.serialization.Codec;
import net.fneifnox.mobtalisman.MobTalisman;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<Integer> TICK_COUNTER_ARMADILLO =
            register("tick_counter_armadillo", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Boolean> ABILITY_USABLE_ARMADILLO =
            register("ability_usable_armadillo", builder -> builder.codec(Codec.BOOL));

    public static final ComponentType<Boolean> RESISTANCE_GIVEN_ARMADILLO =
            register("resistance_given_armadillo", builder -> builder.codec(Codec.BOOL));

    public static final ComponentType<Boolean> EQUIPPED_PLAYERS_ARMADILLO =
            register("equipped_players_armadillo", builder -> builder.codec(Codec.BOOL));

    public static final ComponentType<Integer> TICK_COUNTER_BREEZE =
            register("tick_counter_breeze", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_CAMEL =
            register("tick_counter_camel", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_ELDER_GUARDIAN =
            register("tick_counter_elder_guardian", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_GHAST =
            register("tick_counter_ghast", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_GUARDIAN =
            register("tick_counter_guardian", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_IRON_GOLEM =
            register("tick_counter_iron_golem", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_PARROT =
            register("tick_counter_parrot", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_PIG =
            register("tick_counter_pig", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_SHEEP =
            register("tick_counter_sheep", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Integer> TICK_COUNTER_WITCH =
            register("tick_counter_witch", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Boolean> ABILITY_USABLE_WITCH =
            register("ability_usable_witch", builder -> builder.codec(Codec.BOOL));

    public static final ComponentType<Integer> TICK_COUNTER_WITHER =
            register("tick_counter_wither", builder -> builder.codec(Codec.INT));


    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MobTalisman.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        MobTalisman.LOGGER.info("Registering Data Component Types for " + MobTalisman.MOD_ID);
    }
}
