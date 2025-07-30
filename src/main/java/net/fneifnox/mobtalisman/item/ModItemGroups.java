package net.fneifnox.mobtalisman.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fneifnox.mobtalisman.MobTalisman;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MOB_TALISMAN_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MobTalisman.MOD_ID, "mob-talisman_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.ZOMBIE_TALISMAN))
                    .displayName(Text.translatable("itemgroup.mob_talisman.mob_talisman_items"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.ALLAY_TALISMAN);
                        entries.add(ModItems.ARMADILLO_TALISMAN);
                        entries.add(ModItems.AXOLOTL_TALISMAN);
                        entries.add(ModItems.BAT_TALISMAN);
                        entries.add(ModItems.BLAZE_TALISMAN);
                        entries.add(ModItems.BREEZE_TALISMAN);
                        entries.add(ModItems.CAMEL_TALISMAN);
                        entries.add(ModItems.CAT_TALISMAN);
                        entries.add(ModItems.CHICKEN_TALISMAN);
                        entries.add(ModItems.COW_TALISMAN);
                        entries.add(ModItems.CREEPER_TALISMAN);
                        entries.add(ModItems.DOLPHIN_TALISMAN);
                        entries.add(ModItems.DONKEY_TALISMAN);
                        entries.add(ModItems.DROWNED_TALISMAN);
                        entries.add(ModItems.ELDER_GUARDIAN_TALISMAN);
                        entries.add(ModItems.ENDER_DRAGON_TALISMAN);
                        entries.add(ModItems.ENDERMAN_TALISMAN);
                        entries.add(ModItems.ENDERMITE_TALISMAN);
                        entries.add(ModItems.EVOKER_TALISMAN);
                        entries.add(ModItems.GHAST_TALISMAN);
                        entries.add(ModItems.GLOW_SQUID_TALISMAN);
                        entries.add(ModItems.GUARDIAN_TALISMAN);
                        entries.add(ModItems.HORSE_TALISMAN);
                        entries.add(ModItems.IRON_GOLEM_TALISMAN);
                        entries.add(ModItems.PARROT_TALISMAN);
                        entries.add(ModItems.PHANTOM_TALISMAN);
                        entries.add(ModItems.PIGLIN_BRUTE_TALISMAN);
                        entries.add(ModItems.PIGLIN_TALISMAN);
                        entries.add(ModItems.PIG_TALISMAN);
                        entries.add(ModItems.PUFFERFISH_TALISMAN);
                        entries.add(ModItems.RABBIT_TALISMAN);
                        entries.add(ModItems.RAVAGER_TALISMAN);
                        entries.add(ModItems.SHEEP_TALISMAN);
                        entries.add(ModItems.SHULKER_TALISMAN);
                        entries.add(ModItems.SILVERFISH_TALISMAN);
                        entries.add(ModItems.SKELETON_TALISMAN);
                        entries.add(ModItems.SPIDER_TALISMAN);
                        entries.add(ModItems.SQUID_TALISMAN);
                        entries.add(ModItems.STRIDER_TALISMAN);
                        entries.add(ModItems.TADPOLE_TALISMAN);
                        entries.add(ModItems.TURTLE_TALISMAN);
                        entries.add(ModItems.VILLAGER_TALISMAN);
                        entries.add(ModItems.WARDEN_TALISMAN);
                        entries.add(ModItems.WITCH_TALISMAN);
                        entries.add(ModItems.WITHER_SKELETON_TALISMAN);
                        entries.add(ModItems.WITHER_TALISMAN);
                        entries.add(ModItems.ZOMBIE_TALISMAN);
                        entries.add(ModItems.ZOMBIE_VILLAGER_TALISMAN);
                    }).build());

    public static void registerItemGroups() {
        MobTalisman.LOGGER.info("Registering Item Groups for " + MobTalisman.MOD_ID);
    }
}
