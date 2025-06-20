package net.fneifnox.mobtalisman.config;


import blue.endless.jankson.Comment;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = "mob-talisman")
@Config(name = "mob-talisman", wrapperName = "MobT")
public class ConfigModel {
    @RestartRequired
    @SectionHeader("Dropchances")
    @Comment("Allay Talisman - Default: 1%")
    public float dropchanceForAllayTalisman = 1f;
    @RestartRequired
    @Comment("Armadillo Talisman - Default: 1%")
    public float dropchanceForArmadilloTalisman = 1f;
    @RestartRequired
    @Comment("Axolotl Talisman - Default: 1%")
    public float dropchanceForAxolotlTalisman = 1f;
    @RestartRequired
    @Comment("Bat Talisman - Default: 2%")
    public float dropchanceForBatTalisman = 2f;
    @RestartRequired
    @Comment("Blaze Talisman - Default: 1%")
    public float dropchanceForBlazeTalisman = 1f;
    @RestartRequired
    @Comment("Breeze Talisman - Default: 1%")
    public float dropchanceForBreezeTalisman = 1f;
    @RestartRequired
    @Comment("Cat Talisman - Default: 1%")
    public float dropchanceForCatTalisman = 1f;
    @RestartRequired
    @Comment("Chicken Talisman - Default: 0.5%")
    public float dropchanceForChickenTalisman = 0.5f;
    @RestartRequired
    @Comment("Cow Talisman - Default: 0.5%")
    public float dropchanceForCowTalisman = 0.5f;
    @RestartRequired
    @Comment("Dolphin Talisman - Default: 1.5%")
    public float dropchanceForDolphinTalisman = 1.5f;
    @RestartRequired
    @Comment("Drowned Talisman - Default: 0.5%")
    public float dropchanceForDrownedTalisman = 0.5f;
    @RestartRequired
    @Comment("Elder Guardian Talisman - Default: 1%")
    public float dropchanceForElderGuardianTalisman = 1f;
    @RestartRequired
    @Comment("Ender Dragon Talisman - Default: 5%")
    public float dropchanceForEnderDragonTalisman = 5f;
    @RestartRequired
    @Comment("Evoker Talisman - Default: 0.5%")
    public float dropchanceForEvokerTalisman = 0.5f;
    @RestartRequired
    @Comment("Glow Squid Talisman - Default: 1%")
    public float dropchanceForGlowSquidTalisman = 1f;
    @RestartRequired
    @Comment("Goat Talisman - Default: 1%")
    public float dropchanceForGoatTalisman = 1f;
    @RestartRequired
    @Comment("Guardian Talisman - Default: 1%")
    public float dropchanceForGuardianTalisman = 1f;
    @RestartRequired
    @Comment("Horse Talisman - Default: 0.5%")
    public float dropchanceForHorseTalisman = 0.5f;
    @RestartRequired
    @Comment("Iron Golem Talisman - Default: 1%")
    public float dropchanceForIronGolemTalisman = 1f;
    @RestartRequired
    @Comment("Parrot Talisman - Default: 1.5%")
    public float dropchanceForParrotTalisman = 1.5f;
    @RestartRequired
    @Comment("Phantom Talisman - Default: 1%")
    public float dropchanceForPhantomTalisman = 1f;
    @RestartRequired
    @Comment("Piglin Talisman - Default: 0.5%")
    public float dropchanceForPiglinTalisman = 0.5f;
    @RestartRequired
    @Comment("Pig Talisman - Default: 0.5%")
    public float dropchanceForPigTalisman = 0.5f;
    @RestartRequired
    @Comment("Pufferfish Talisman - Default: 1.5%")
    public float dropchanceForPufferfishTalisman = 1.5f;
    @RestartRequired
    @Comment("Rabbit Talisman - Default: 1%")
    public float dropchanceForRabbitTalisman = 1f;
    @RestartRequired
    @Comment("Ravager Talisman - Default: 1%")
    public float dropchanceForRavagerTalisman = 1f;
    @RestartRequired
    @Comment("Sheep Talisman - Default: 0.5%")
    public float dropchanceForSheepTalisman = 0.5f;
    @RestartRequired
    @Comment("Shulker Talisman - Default: 1%")
    public float dropchanceForShulkerTalisman = 1f;
    @RestartRequired
    @Comment("Silverfish Talisman - Default: 0.5%")
    public float dropchanceForSilverfishTalisman = 0.5f;
    @RestartRequired
    @Comment("Skeleton Talisman - Default: 0.5%")
    public float dropchanceForSkeletonTalisman = 0.5f;
    @RestartRequired
    @Comment("Spider Talisman - Default: 0.5%")
    public float dropchanceForSpiderTalisman = 0.5f;
    @RestartRequired
    @Comment("Squid Talisman - Default: 1%")
    public float dropchanceForSquidTalisman = 1f;
    @RestartRequired
    @Comment("Strider Talisman - Default: 2%")
    public float dropchanceForStriderTalisman = 2f;
    @RestartRequired
    @Comment("Tadpole Talisman - Default: 2%")
    public float dropchanceForTadpoleTalisman = 2f;
    @RestartRequired
    @Comment("Turtle Talisman - Default: 1.5%")
    public float dropchanceForTurtleTalisman = 1.5f;
    @RestartRequired
    @Comment("Villager Talisman - Default: 1%")
    public float dropchanceForVillagerTalisman = 1f;
    @RestartRequired
    @Comment("Warden Talisman - Default: 2.5%")
    public float dropchanceForWardenTalisman = 2.5f;
    @RestartRequired
    @Comment("Witch Talisman - Default: 1%")
    public float dropchanceForWitchTalisman = 1f;
    @RestartRequired
    @Comment("Wither Talisman - Default: 2%")
    public float dropchanceForWitherTalisman = 2f;
    @RestartRequired
    @Comment("Zombie Talisman - Default: 0.5%")
    public float dropchanceForZombieTalisman = 0.5f;
    @SectionHeader("Other Configurations")
    @Comment("Chance for a Goat Horn Drop from Goat Talisman - Default: 5%")
    public float dropchanceForGoatHorn = 5f;
    @Comment("Chance for a Rotten Flesh to be created instead of a Porkchop from Pig Talisman - Default: 50%")
    public float dropchanceForRottenFlesh = 50f;
    @Comment("Increased Chance that a Talisman is dropped from Ender Dragon Talisman - Default: 50%")
    public float dropchanceForTalisman = 50f;
    @Comment("The increased amount of collection range from Allay Talisman - Default: 200%")
    public float increasedCollectionRangeForAllayTalisman = 200f;
    @Comment("The increased amount of movement speed from Cat Talisman - Default: 50%")
    public float increasedMovementSpeedForCatTalisman = 50f;
    @Comment("The increased amount of underwater breathing from Drowned Talisman - Default: 150%")
    public float increasedUnderwaterBreathingForDrownedTalisman = 150f;
    @Comment("The increased amount of Step Height from Horse Talisman - Default: 1")
    public float increasedStepHeightForHorseTalisman = 1f;
    @Comment("The increased amount of hearts from Iron Golem Talisman - Default: 10 (Half hearts)")
    public int increasedHeartsForIronGolemTalisman = 10;
    @Comment("The increased amount of Jump Height from Rabbit Talisman - Default: 125%")
    public float increasedJumpHeightForRabbitTalisman = 125f;
    @Comment("The increased amount of mining speed from Silverfish Talisman - Default: 50%")
    public float increasedMiningSpeedForSilverfishTalisman = 50f;
    @Comment("The decreased amount of scale from Tadpole Talisman - Default: 50%")
    public float decreasedScaleForTadpoleTalisman = 50f;
    @Comment("The increased amount of hearts from Warden Talisman - Default: 20 (Half hearts)")
    public int increasedHeartsForWardenTalisman = 20;
    @Comment("The increased amount of experience from Zombie Talisman - Default: 100%")
    public float increasedXPForZombieTalisman = 100f;
    @Comment("The min. durability that gets repaired from Phantom Talisman - Default: 1")
    public int minDurabilityForPhantom = 1;
    @Comment("The max. durability that gets repaired from Phantom Talisman - Default: 4")
    public int maxDurabilityForPhantom = 4;
    @Comment("The min. cooldown for repairing an item from Phantom Talisman - Default: 31s")
    public int minCooldownForPhantom = 31;
    @Comment("The max. cooldown for repairing an item from Phantom Talisman - Default: 58s")
    public int maxCooldownForPhantom = 58;
    @Comment("Only climb on walls when jumped using Spider Talisman - Default: false")
    public boolean jumpForWallClimbing = false;
    @Comment("Invincibility duration for Armadillo Talisman - Default: 5s")
    public int durationForArmadilloTalisman = 5;
    @Comment("Poison duration for Pufferfish Talisman - Default: 10s")
    public int durationForPufferfishTalisman = 10;
    @Comment("Blindness duration for Squid Talisman - Default: 10s")
    public int durationForSquidTalisman = 10;
    @Comment("Cooldown for Armadillo Talisman - Default: 180s")
    public int cooldownForArmadilloTalisman = 180;
    @Comment("Cooldown for Breeze Talisman - Default: 60s")
    public int cooldownForBreezeTalisman = 60;
    @Comment("Cooldown for Elder Guardian Talisman - Default: 600s")
    public int cooldownForElderGuardianTalisman = 600;
    @Comment("Cooldown for Guardian Talisman - Default: 60s")
    public int cooldownForGuardianTalisman = 60;
    @Comment("Cooldown for Parrot Talisman - Default: 60s")
    public int cooldownForParrotTalisman = 60;
    @Comment("Cooldown for Pig Talisman - Default: 60s")
    public int cooldownForPigTalisman = 60;
    @Comment("Cooldown for Sheep Talisman - Default: 60s")
    public int cooldownForSheepTalisman = 60;
    @Comment("Cooldown for Witch Talisman - Default: 180s")
    public int cooldownForWitchTalisman = 180;
    @Comment("Cooldown for Wither Talisman - Default: 1800s / 30min")
    public int cooldownForWitherTalisman = 1800;
}
