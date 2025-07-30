package net.fneifnox.mobtalisman.config;


import blue.endless.jankson.Comment;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;
import net.minecraft.util.Identifier;

import java.util.List;

@Sync(Option.SyncMode.OVERRIDE_CLIENT)
@Modmenu(modId = "mob-talisman")
@Config(name = "mob-talisman", wrapperName = "MobT")
public class ConfigModel {
    @SectionHeader("Dropchances")
    @Sync(Option.SyncMode.INFORM_SERVER)
    public boolean showDropchancesAsTooltip = false;
    @Comment("Allay Talisman - Default: 1%")
    public float dropchanceForAllayTalisman = 1f;
    @Comment("Armadillo Talisman - Default: 1%")
    public float dropchanceForArmadilloTalisman = 1f;
    @Comment("Axolotl Talisman - Default: 1%")
    public float dropchanceForAxolotlTalisman = 1f;
    @Comment("Bat Talisman - Default: 1.5%")
    public float dropchanceForBatTalisman = 1.5f;
    @Comment("Blaze Talisman - Default: 1%")
    public float dropchanceForBlazeTalisman = 0.5f;
    @Comment("Breeze Talisman - Default: 1%")
    public float dropchanceForBreezeTalisman = 1f;
    @Comment("Camel Talisman - Default: 1%")
    public float dropchanceForCamelTalisman = 1f;
    @Comment("Cat Talisman - Default: 1%")
    public float dropchanceForCatTalisman = 1f;
    @Comment("Chicken Talisman - Default: 0.5%")
    public float dropchanceForChickenTalisman = 0.5f;
    @Comment("Cow Talisman - Default: 0.5%")
    public float dropchanceForCowTalisman = 0.5f;
    @Comment("Creeper Talisman - Default: 0.25%")
    public float dropchanceForCreeperTalisman = 0.25f;
    @Comment("Dolphin Talisman - Default: 1.5%")
    public float dropchanceForDolphinTalisman = 1.5f;
    @Comment("Donkey Talisman - Default: 0.5%")
    public float dropchanceForDonkeyTalisman = 0.5f;
    @Comment("Drowned Talisman - Default: 0.5%")
    public float dropchanceForDrownedTalisman = 0.5f;
    @Comment("Elder Guardian Talisman - Default: 1%")
    public float dropchanceForElderGuardianTalisman = 1f;
    @Comment("Ender Dragon Talisman - Default: 5%")
    public float dropchanceForEnderDragonTalisman = 5f;
    @Comment("Enderman Talisman - Default: 0.25%")
    public float dropchanceForEndermanTalisman = 0.25f;
    @Comment("Endermite Talisman - Default: 1.5%")
    public float dropchanceForEndermiteTalisman = 1.5f;
    @Comment("Evoker Talisman - Default: 0.5%")
    public float dropchanceForEvokerTalisman = 0.5f;
    @Comment("Ghast Talisman - Default: 1%")
    public float dropchanceForGhastTalisman = 1f;
    @Comment("Glow Squid Talisman - Default: 1%")
    public float dropchanceForGlowSquidTalisman = 1f;
    @Comment("Guardian Talisman - Default: 1%")
    public float dropchanceForGuardianTalisman = 1f;
    @Comment("Horse Talisman - Default: 0.5%")
    public float dropchanceForHorseTalisman = 0.5f;
    @Comment("Iron Golem Talisman - Default: 1%")
    public float dropchanceForIronGolemTalisman = 1f;
    @Comment("Parrot Talisman - Default: 1.5%")
    public float dropchanceForParrotTalisman = 1.5f;
    @Comment("Phantom Talisman - Default: 1%")
    public float dropchanceForPhantomTalisman = 1f;
    @Comment("Piglin Brute Talisman - Default: 1%")
    public float dropchanceForPiglinBruteTalisman = 1f;
    @Comment("Piglin Talisman - Default: 0.5%")
    public float dropchanceForPiglinTalisman = 0.5f;
    @Comment("Pig Talisman - Default: 0.25%")
    public float dropchanceForPigTalisman = 0.25f;
    @Comment("Pufferfish Talisman - Default: 1.5%")
    public float dropchanceForPufferfishTalisman = 1.5f;
    @Comment("Rabbit Talisman - Default: 1%")
    public float dropchanceForRabbitTalisman = 1f;
    @Comment("Ravager Talisman - Default: 1%")
    public float dropchanceForRavagerTalisman = 1f;
    @Comment("Sheep Talisman - Default: 0.25%")
    public float dropchanceForSheepTalisman = 0.25f;
    @Comment("Shulker Talisman - Default: 1%")
    public float dropchanceForShulkerTalisman = 1f;
    @Comment("Silverfish Talisman - Default: 0.5%")
    public float dropchanceForSilverfishTalisman = 0.5f;
    @Comment("Skeleton Talisman - Default: 0.5%")
    public float dropchanceForSkeletonTalisman = 0.5f;
    @Comment("Spider Talisman - Default: 0.5%")
    public float dropchanceForSpiderTalisman = 0.5f;
    @Comment("Squid Talisman - Default: 1%")
    public float dropchanceForSquidTalisman = 1f;
    @Comment("Strider Talisman - Default: 2%")
    public float dropchanceForStriderTalisman = 2f;
    @Comment("Tadpole Talisman - Default: 2%")
    public float dropchanceForTadpoleTalisman = 2f;
    @Comment("Turtle Talisman - Default: 1.5%")
    public float dropchanceForTurtleTalisman = 1.5f;
    @Comment("Villager Talisman - Default: 1%")
    public float dropchanceForVillagerTalisman = 1f;
    @Comment("Warden Talisman - Default: 2.5%")
    public float dropchanceForWardenTalisman = 2.5f;
    @Comment("Witch Talisman - Default: 1%")
    public float dropchanceForWitchTalisman = 1f;
    @Comment("Wither Skeleton Talisman - Default: 0.5%")
    public float dropchanceForWitherSkeletonTalisman = 0.5f;
    @Comment("Wither Talisman - Default: 2%")
    public float dropchanceForWitherTalisman = 2f;
    @Comment("Zombie Talisman - Default: 0.25%")
    public float dropchanceForZombieTalisman = 0.25f;
    @Comment("Zombie Villager Talisman - Default: 1%")
    public float dropchanceForZombieVillagerTalisman = 1f;

    @SectionHeader("Other Configurations")
    @Nest
    public AllayTalisman allayTalisman = new AllayTalisman();
    public static class AllayTalisman {
        @Comment("The increased amount of collection range from Allay Talisman - Default: 500%")
        public float increasedCollectionRangeForAllayTalisman = 500f;
    }
    @Nest
    public ArmadilloTalisman armadilloTalisman = new ArmadilloTalisman();
    public static class ArmadilloTalisman {
        @Comment("Invincibility duration for Armadillo Talisman - Default: 5s")
        public int durationForArmadilloTalisman = 5;
        @Comment("Cooldown for Armadillo Talisman - Default: 120s")
        public int cooldownForArmadilloTalisman = 120;
    }
    @Nest
    public BatTalisman batTalisman = new BatTalisman();
    public static class BatTalisman {
        @Comment("The fly speed when using Bat Talisman (5% = creative flight) - Default: 2%")
        public float flySpeedForBatTalisman = 2f;
    }
    @Nest
    public BreezeTalisman breezeTalisman = new BreezeTalisman();
    public static class BreezeTalisman {
        @Comment("Windcharge ability can also be used in air using Breeze Talisman (will still give you fall damage when used in air) - Default: true")
        public boolean windchargeInAir = true;
        @Comment("Cooldown for Breeze Talisman - Default: 10s")
        public int cooldownForBreezeTalisman = 10;
    }
    @Nest
    public CamelTalisman camelTalisman = new CamelTalisman();
    public static class CamelTalisman {
        @Comment("The dash strength when using Camel Talisman - Default: 1.5")
        public float dashStrengthForCamelTalisman = 1.5f;
        @Comment("The reduced dash strength while flying with an elytra when using Camel Talisman (100% = disabled) - Default: 95%")
        public float reducedDashStrengthWithElytraForCamelTalisman = 95f;
        @Comment("Cooldown for Camel Talisman - Default: 2s")
        public int cooldownForCamelTalisman = 2;
    }
    @Nest
    public CatTalisman catTalisman = new CatTalisman();
    public static class CatTalisman {
        @Comment("The increased amount of movement speed from Cat Talisman - Default: 50%")
        public float increasedMovementSpeedForCatTalisman = 50f;
    }
    @Nest
    public DonkeyTalisman donkeyTalisman = new DonkeyTalisman();
    public static class DonkeyTalisman {
        @Comment("The rows for the Donkey Talisman backpack (1-6) - Default: 1")
        public int backpackRowsForDonkeyTalisman = 1;
    }
    @Nest
    public DrownedTalisman drownedTalisman = new DrownedTalisman();
    public static class DrownedTalisman {
        @Comment("The increased amount of underwater breathing from Drowned Talisman - Default: 200%")
        public float increasedUnderwaterBreathingForDrownedTalisman = 200f;
    }
    @Nest
    public ElderGuardianTalisman elderGuardianTalisman = new ElderGuardianTalisman();
    public static class ElderGuardianTalisman {
        @Comment("Cooldown for Elder Guardian Talisman - Default: 600s")
        public int cooldownForElderGuardianTalisman = 600;
    }
    @Nest
    public EnderDragonTalisman enderDragonTalisman = new EnderDragonTalisman();
    public static class EnderDragonTalisman {
        @Comment("The increased chance that a talisman is dropped when using Ender Dragon Talisman - Default: 50%")
        public float dropchanceForTalisman = 50f;
    }
    @Nest
    public GhastTalisman ghastTalisman = new GhastTalisman();
    public static class GhastTalisman {
        @Comment("Fireballs don't destroy blocks using Ghast Talisman - Default: true")
        public boolean nonDestructiveFireballs = true;
        @Comment("Cooldown for Ghast Talisman - Default: 15s")
        public int cooldownForGhastTalisman = 15;
    }
    @Nest
    public GlowSquidTalisman glowSquidTalisman = new GlowSquidTalisman();
    public static class GlowSquidTalisman {
        @Comment("Glowing effect applies to every entity using Glow Squid Talisman - Default: false")
        public boolean glowingEffectForEveryEntity = false;
    }
    @Nest
    public GuardianTalisman guardianTalisman = new GuardianTalisman();
    public static class GuardianTalisman {
        @Comment("Cooldown for Guardian Talisman - Default: 60s")
        public int cooldownForGuardianTalisman = 60;
    }
    @Nest
    public HorseTalisman horseTalisman = new HorseTalisman();
    public static class HorseTalisman {
        @Comment("The increased amount of Step Height from Horse Talisman - Default: 1")
        public float increasedStepHeightForHorseTalisman = 1f;
    }
    @Nest
    public IronGolemTalisman ironGolemTalisman = new IronGolemTalisman();
    public static class IronGolemTalisman {
        @Comment("The amount of absorption hearts from Iron Golem Talisman - Default: 10 (4 Half hearts per step)")
        public int absorptionHeartsForIronGolemTalisman = 10;
        @Comment("Cooldown for Iron Golem Talisman - Default: 90s")
        public int cooldownForIronGolemTalisman = 90;
    }
    @Nest
    public ParrotTalisman parrotTalisman = new ParrotTalisman();
    public static class ParrotTalisman {
        @Comment("Cooldown for Parrot Talisman - Default: 60s")
        public int cooldownForParrotTalisman = 60;
    }
    @Nest
    public PhantomTalisman phantomTalisman = new PhantomTalisman();
    public static class PhantomTalisman {
        @Comment("The min. durability that gets repaired from Phantom Talisman - Default: 1")
        public int minDurabilityForPhantom = 1;
        @Comment("The max. durability that gets repaired from Phantom Talisman - Default: 4")
        public int maxDurabilityForPhantom = 4;
        @Comment("The min. cooldown for repairing an item from Phantom Talisman - Default: 49s")
        public int minCooldownForPhantom = 49;
        @Comment("The max. cooldown for repairing an item from Phantom Talisman - Default: 97s")
        public int maxCooldownForPhantom = 97;
    }
    @Nest
    public PiglinTalisman piglinTalisman = new PiglinTalisman();
    public static class PiglinTalisman {
        @Comment("The chance that a block turns into a Gold Block when using Piglin Talisman - Default: 0.25%")
        public float turnsIntoGoldBlockChanceForPiglinTalisman = 0.25f;
    }
    @Nest
    public PigTalisman pigTalisman = new PigTalisman();
    public static class PigTalisman {
        @Comment("The chance for a Rotten Flesh to be created instead of a Porkchop from Pig Talisman - Default: 50%")
        public float dropchanceForRottenFlesh = 50f;
        @Comment("Cooldown for Pig Talisman - Default: 60s")
        public int cooldownForPigTalisman = 60;
    }
    @Nest
    public PufferfishTalisman pufferfishTalisman = new PufferfishTalisman();
    public static class PufferfishTalisman {
        @Comment("Poison duration for Pufferfish Talisman - Default: 5s")
        public int durationForPufferfishTalisman = 5;
    }
    @Nest
    public RabbitTalisman rabbitTalisman = new RabbitTalisman();
    public static class RabbitTalisman {
        @Comment("The increased amount of Jump Height from Rabbit Talisman - Default: 125%")
        public float increasedJumpHeightForRabbitTalisman = 125f;
    }
    @Nest
    public RavagerTalisman ravagerTalisman = new RavagerTalisman();
    public static class RavagerTalisman {
        @Comment("Combo duration for Ravager Talisman - Default: 15s")
        public int comboDurationForRavagerTalisman = 15;
    }
    @Nest
    public SheepTalisman sheepTalisman = new SheepTalisman();
    public static class SheepTalisman {
        @Comment("The chance that the player eats grass when using Sheep Talisman - Default: 6%")
        public float eatingGrassChanceForSheepTalisman = 6f;
    }
    @Nest
    public SilverfishTalisman silverfishTalisman = new SilverfishTalisman();
    public static class SilverfishTalisman {
        @Comment("The increased amount of mining speed from Silverfish Talisman - Default: 50%")
        public float increasedMiningSpeedForSilverfishTalisman = 50f;
    }
    @Nest
    public SpiderTalisman spiderTalisman = new SpiderTalisman();
    public static class SpiderTalisman {
        @Comment("Only climb on walls when jumped using Spider Talisman - Default: false")
        public boolean jumpForWallClimbing = false;
    }
    @Nest
    public SquidTalisman squidTalisman = new SquidTalisman();
    public static class SquidTalisman {
        @Comment("Blindness duration for Squid Talisman - Default: 10s")
        public int durationForSquidTalisman = 10;
    }
    @Nest
    public TadpoleTalisman tadpoleTalisman = new TadpoleTalisman();
    public static class TadpoleTalisman {
        @Comment("The decreased amount of scale from Tadpole Talisman - Default: 50%")
        public float decreasedScaleForTadpoleTalisman = 50f;
    }
    @Nest
    public WardenTalisman wardenTalisman = new WardenTalisman();
    public static class WardenTalisman {
        @Comment("The increased amount of hearts from Warden Talisman - Default: 20 (Half hearts)")
        public int increasedHeartsForWardenTalisman = 20;
    }
    @Nest
    public WitchTalisman witchTalisman = new WitchTalisman();
    public static class WitchTalisman {
        @Comment("Cooldown for Witch Talisman - Default: 120s")
        public int cooldownForWitchTalisman = 120;
    }
    @Nest
    public WitherSkeletonTalisman witherSkeletonTalisman = new WitherSkeletonTalisman();
    public static class WitherSkeletonTalisman {
        @Comment("The increased chance of a wither skeleton skull dropping when using Wither Skeleton Talisman - Default: 40%")
        public float increasedWitherSkeletonSkullChanceForWitherSkeletonTalisman = 40f;
    }
    @Nest
    public WitherTalisman witherTalisman = new WitherTalisman();
    public static class WitherTalisman {
        @Comment("Cooldown for Wither Talisman - Default: 1500s / 25min")
        public int cooldownForWitherTalisman = 1500;
        @Comment("Blacklisted Items that should not appear when using Wither Talisman - Default: every unobtainable Item and the Dragon Egg")
        public List<Identifier> blacklistedItemsForWitherTalisman = List.of(Identifier.of("minecraft:bedrock"), Identifier.of("minecraft:budding_amethyst"),
                Identifier.of("minecraft:chorus_plant"), Identifier.of("minecraft:end_portal_frame"), Identifier.of("minecraft:farmland"), Identifier.of("minecraft:frogspawn"),
                Identifier.of("minecraft:infested_stone"), Identifier.of("minecraft:spawner"), Identifier.of("minecraft:reinforced_deepslate"), Identifier.of("minecraft:trial_spawner"),
                Identifier.of("minecraft:vault"), Identifier.of("minecraft:barrier"), Identifier.of("minecraft:command_block"), Identifier.of("minecraft:repeating_command_block"),
                Identifier.of("minecraft:chain_command_block"), Identifier.of("minecraft:jigsaw"), Identifier.of("minecraft:light"), Identifier.of("minecraft:command_block_minecart"),
                Identifier.of("minecraft:petrified_oak_slab"), Identifier.of("minecraft:player_head"), Identifier.of("minecraft:structure_block"), Identifier.of("minecraft:structure_void"),
                Identifier.of("minecraft:air"), Identifier.of("minecraft:debug_stick"), Identifier.of("minecraft:dragon_egg"));
        @Comment("Spawn eggs appear when using the Wither Talisman - Default: false")
        public boolean enableSpawnEggsForWitherTalisman = false;
    }
    @Nest
    public ZombieTalisman zombieTalisman = new ZombieTalisman();
    public static class ZombieTalisman {
        @Comment("The increased amount of experience from Zombie Talisman - Default: 100%")
        public float increasedXPForZombieTalisman = 100f;
    }
    @Nest
    public ZombieVillagerTalisman zombieVillagerTalisman = new ZombieVillagerTalisman();
    public static class ZombieVillagerTalisman {
        @Comment("The discount from Zombie Villager Talisman - Default: 40%")
        public int discountForZombieVillagerTalisman = 40;
    }
}
