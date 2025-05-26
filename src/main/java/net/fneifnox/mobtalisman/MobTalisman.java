package net.fneifnox.mobtalisman;

import net.fabricmc.api.ModInitializer;

import net.fneifnox.mobtalisman.item.ModItemGroups;
import net.fneifnox.mobtalisman.item.ModItems;
import net.fneifnox.mobtalisman.item.custom.HorseTalisman;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.fneifnox.mobtalisman.item.ModItems.IRON_GOLEM_TALISMAN;

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
		Registry.register(Registries.ATTRIBUTE, HorseTalisman.STEP_HEIGHT_ID, HorseTalisman.STEP_HEIGHT);
	}
}