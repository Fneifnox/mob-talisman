package net.fneifnox.mobtalisman.item;

import net.fneifnox.mobtalisman.MobTalisman;
import net.fneifnox.mobtalisman.item.custom.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static Item HORSE_TALISMAN = registerItem("horse_talisman",
            new HorseTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item BAT_TALISMAN = registerItem("bat_talisman",
            new BatTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item IRON_GOLEM_TALISMAN = registerItem("iron_golem_talisman",
            new IronGolemTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item CHICKEN_TALISMAN = registerItem("chicken_talisman",
            new ChickenTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item RABBIT_TALISMAN = registerItem("rabbit_talisman",
            new RabbitTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item WARDEN_TALISMAN = registerItem("warden_talisman",
            new WardenTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item STRIDER_TALISMAN = registerItem("strider_talisman",
            new StriderTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item CAT_TALISMAN = registerItem("cat_talisman",
            new CatTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item DOLPHIN_TALISMAN = registerItem("dolphin_talisman",
            new DolphinTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item DROWNED_TALISMAN = registerItem("drowned_talisman",
            new DrownedTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item GLOW_SQUID_TALISMAN = registerItem("glow_squid_talisman",
            new GlowSquidTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item SHEEP_TALISMAN = registerItem("sheep_talisman",
            new SheepTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item BREEZE_TALISMAN = registerItem("breeze_talisman",
            new BreezeTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item WITCH_TALISMAN = registerItem("witch_talisman",
            new WitchTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item SHULKER_TALISMAN = registerItem("shulker_talisman",
            new ShulkerTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item TADPOLE_TALISMAN = registerItem("tadpole_talisman",
            new TadpoleTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item TURTLE_TALISMAN = registerItem("turtle_talisman",
            new TurtleTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item PHANTOM_TALISMAN = registerItem("phantom_talisman",
            new PhantomTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item COW_TALISMAN = registerItem("cow_talisman",
            new CowTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item PARROT_TALISMAN = registerItem("parrot_talisman",
            new ParrotTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item SQUID_TALISMAN = registerItem("squid_talisman",
            new SquidTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item EVOKER_TALISMAN = registerItem("evoker_talisman",
            new EvokerTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item GUARDIAN_TALISMAN = registerItem("guardian_talisman",
            new GuardianTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ELDER_GUARDIAN_TALISMAN = registerItem("elder_guardian_talisman",
            new ElderGuardianTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item RAVAGER_TALISMAN = registerItem("ravager_talisman",
            new RavagerTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item WITHER_TALISMAN = registerItem("wither_talisman",
            new WitherTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ALLAY_TALISMAN = registerItem("allay_talisman",
            new AllayTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item SILVERFISH_TALISMAN = registerItem("silverfish_talisman",
            new SilverfishTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item PIG_TALISMAN = registerItem("pig_talisman",
            new PigTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item VILLAGER_TALISMAN = registerItem("villager_talisman",
            new VillagerTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ENDERMAN_TALISMAN = registerItem("enderman_talisman",
            new EndermanTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item PIGLIN_TALISMAN = registerItem("piglin_talisman",
            new PiglinTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ZOMBIE_TALISMAN = registerItem("zombie_talisman",
            new ZombieTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item SKELETON_TALISMAN = registerItem("skeleton_talisman",
            new SkeletonTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item BLAZE_TALISMAN = registerItem("blaze_talisman",
            new BlazeTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ARMADILLO_TALISMAN = registerItem("armadillo_talisman",
            new ArmadilloTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ENDER_DRAGON_TALISMAN = registerItem("ender_dragon_talisman",
            new EnderDragonTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item PUFFERFISH_TALISMAN = registerItem("pufferfish_talisman",
            new PufferfishTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item SPIDER_TALISMAN = registerItem("spider_talisman",
            new SpiderTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item AXOLOTL_TALISMAN = registerItem("axolotl_talisman",
            new AxolotlTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item GHAST_TALISMAN = registerItem("ghast_talisman",
            new GhastTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item CAMEL_TALISMAN = registerItem("camel_talisman",
            new CamelTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item CREEPER_TALISMAN = registerItem("creeper_talisman",
            new CreeperTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ZOMBIE_VILLAGER_TALISMAN = registerItem("zombie_villager_talisman",
            new ZombieVillagerTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item ENDERMITE_TALISMAN = registerItem("endermite_talisman",
            new EndermiteTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static Item DONKEY_TALISMAN = registerItem("donkey_talisman",
            new DonkeyTalisman(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MobTalisman.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MobTalisman.LOGGER.info("Registering Mod Items for " + MobTalisman.MOD_ID);
    }
}
