package net.fneifnox.mobtalisman.item;

import net.fneifnox.mobtalisman.MobTalisman;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item HORSE_TALISMAN = registerItem("horse_talisman",
            new Item(new Item.Settings()));

    public static Item BAT_TALISMAN = registerItem("bat_talisman",
            new Item(new Item.Settings()));

    public static Item IRON_GOLEM_TALISMAN = registerItem("iron_golem_talisman",
            new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MobTalisman.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MobTalisman.LOGGER.info("Registering Mod Items for " + MobTalisman.MOD_ID);
    }
}
