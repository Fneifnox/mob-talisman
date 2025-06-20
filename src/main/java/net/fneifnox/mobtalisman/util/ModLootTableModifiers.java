package net.fneifnox.mobtalisman.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fneifnox.mobtalisman.item.ModItems;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class ModLootTableModifiers {

    private static final Identifier ALLAY_ID = Identifier.of("minecraft", "entities/allay");
    private static final Identifier ARMADILLO_ID = Identifier.of("minecraft", "entities/armadillo");
    private static final Identifier AXOLOTL_ID = Identifier.of("minecraft", "entities/axolotl");
    private static final Identifier BAT_ID = Identifier.of("minecraft", "entities/bat");
    private static final Identifier BLAZE_ID = Identifier.of("minecraft", "entities/blaze");
    private static final Identifier BREEZE_ID = Identifier.of("minecraft", "entities/breeze");
    private static final Identifier CAT_ID = Identifier.of("minecraft", "entities/cat");
    private static final Identifier CHICKEN_ID = Identifier.of("minecraft", "entities/chicken");
    private static final Identifier COW_ID = Identifier.of("minecraft", "entities/cow");
    private static final Identifier DOLPHIN_ID = Identifier.of("minecraft", "entities/dolphin");
    private static final Identifier DROWNED_ID = Identifier.of("minecraft", "entities/drowned");
    private static final Identifier ELDER_GUARDIAN_ID = Identifier.of("minecraft", "entities/elder_guardian");
    private static final Identifier ENDER_DRAGON_ID = Identifier.of("minecraft", "entities/ender_dragon");
    private static final Identifier ENDERMAN_ID = Identifier.of("minecraft", "entities/enderman");
    private static final Identifier EVOKER_ID = Identifier.of("minecraft", "entities/evoker");
    private static final Identifier GLOW_SQUID_ID = Identifier.of("minecraft", "entities/glow_squid");
    private static final Identifier GOAT_ID = Identifier.of("minecraft", "entities/goat");
    private static final Identifier GUARDIAN_ID = Identifier.of("minecraft", "entities/guardian");
    private static final Identifier HORSE_ID = Identifier.of("minecraft", "entities/horse");
    private static final Identifier IRON_GOLEM_ID = Identifier.of("minecraft", "entities/iron_golem");
    private static final Identifier PARROT_ID = Identifier.of("minecraft", "entities/parrot");
    private static final Identifier PHANTOM_ID = Identifier.of("minecraft", "entities/phantom");
    private static final Identifier PIGLIN_ID = Identifier.of("minecraft", "entities/piglin");
    private static final Identifier PIG_ID = Identifier.of("minecraft", "entities/pig");
    private static final Identifier PUFFERFISH_ID = Identifier.of("minecraft", "entities/pufferfish");
    private static final Identifier RABBIT_ID = Identifier.of("minecraft", "entities/rabbit");
    private static final Identifier RAVAGER_ID = Identifier.of("minecraft", "entities/ravager");
    private static final Identifier SHEEP_ID = Identifier.of("minecraft", "entities/sheep");
    private static final Identifier SHULKER_ID = Identifier.of("minecraft", "entities/shulker");
    private static final Identifier SILVERFISH_ID = Identifier.of("minecraft", "entities/silverfish");
    private static final Identifier SKELETON_ID = Identifier.of("minecraft", "entities/skeleton");
    private static final Identifier SPIDER_ID = Identifier.of("minecraft", "entities/spider");
    private static final Identifier SQUID_ID = Identifier.of("minecraft", "entities/squid");
    private static final Identifier STRIDER_ID = Identifier.of("minecraft", "entities/strider");
    private static final Identifier TADPOLE_ID = Identifier.of("minecraft", "entities/tadpole");
    private static final Identifier TURTLE_ID = Identifier.of("minecraft", "entities/turtle");
    private static final Identifier VILLAGER_ID = Identifier.of("minecraft", "entities/villager");
    private static final Identifier WARDEN_ID = Identifier.of("minecraft", "entities/warden");
    private static final Identifier WITCH_ID = Identifier.of("minecraft", "entities/witch");
    private static final Identifier WITHER_ID = Identifier.of("minecraft", "entities/wither");
    private static final Identifier ZOMBIE_ID = Identifier.of("minecraft", "entities/zombie");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {

            if (ALLAY_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForAllayTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.ALLAY_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (ARMADILLO_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForArmadilloTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.ARMADILLO_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (AXOLOTL_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForAxolotlTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.AXOLOTL_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (BAT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForBatTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.BAT_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (BLAZE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForBlazeTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.BLAZE_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (BREEZE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForBreezeTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.BREEZE_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (CAT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForCatTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.CAT_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (CHICKEN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForChickenTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.CHICKEN_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (COW_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForCowTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.COW_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (DOLPHIN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForDolphinTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.DOLPHIN_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (DROWNED_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForDrownedTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.DROWNED_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (ELDER_GUARDIAN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForElderGuardianTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.ELDER_GUARDIAN_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (ENDER_DRAGON_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForEnderDragonTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.ENDER_DRAGON_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (ENDERMAN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForEndermanTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.ENDERMAN_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (EVOKER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForEvokerTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.EVOKER_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (GLOW_SQUID_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForGlowSquidTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.GLOW_SQUID_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (GOAT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForGoatTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.GOAT_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (GUARDIAN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForGuardianTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.GUARDIAN_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (HORSE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForHorseTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.HORSE_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (IRON_GOLEM_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForIronGolemTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.IRON_GOLEM_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (PARROT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForParrotTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.PARROT_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (PHANTOM_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForPhantomTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.PHANTOM_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (PIGLIN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForPiglinTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.PIGLIN_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (PIG_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForPigTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.PIG_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (PUFFERFISH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForPufferfishTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.PUFFERFISH_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (RABBIT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForRabbitTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.RABBIT_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (RAVAGER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForRavagerTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.RAVAGER_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (SHEEP_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForSheepTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.SHEEP_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (SHULKER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForShulkerTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.SHULKER_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (SILVERFISH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForSilverfishTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.SILVERFISH_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (SKELETON_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForSkeletonTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.SKELETON_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (SPIDER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForSpiderTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.SPIDER_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (SQUID_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForSquidTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.SQUID_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (STRIDER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForStriderTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.STRIDER_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (TADPOLE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForTadpoleTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.TADPOLE_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (TURTLE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForTurtleTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.TURTLE_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForVillagerTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.VILLAGER_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForWardenTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.WARDEN_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (WITCH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForWitchTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.WITCH_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (WITHER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForWitherTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.WITHER_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (ZOMBIE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder((CONFIG.dropchanceForZombieTalisman() / 100)))
                        .with(ItemEntry.builder(ModItems.ZOMBIE_TALISMAN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
