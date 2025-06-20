package net.fneifnox.mobtalisman;

import net.fabricmc.api.ModInitializer;

import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.fneifnox.mobtalisman.config.MobT;
import net.fneifnox.mobtalisman.item.ModItemGroups;
import net.fneifnox.mobtalisman.item.ModItems;
import net.fneifnox.mobtalisman.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobTalisman implements ModInitializer {
	public static final String MOD_ID = "mob-talisman";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModLootTableModifiers.modifyLootTables();
		ModDataComponentTypes.registerDataComponentTypes();

		CONFIG.load();
		CONFIG.save();
	}

	public static final MobT CONFIG = MobT.createAndLoad();
}