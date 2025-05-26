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
            Identifier.of(MobTalisman.MOD_ID, "advanced_equipment_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.HORSE_TALISMAN))
                    .displayName(Text.translatable("itemgroup.mob_talisman.mob_talisman_items"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.HORSE_TALISMAN);
                        entries.add(ModItems.BAT_TALISMAN);
                        entries.add(ModItems.IRON_GOLEM_TALISMAN);
                    }).build());

    public static void registerItemGroups() {
        MobTalisman.LOGGER.info("Registering Item Groups for " + MobTalisman.MOD_ID);
    }
}
