package net.fneifnox.mobtalisman;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.fneifnox.mobtalisman.config.MobT;
import net.fneifnox.mobtalisman.item.ModItemGroups;
import net.fneifnox.mobtalisman.item.ModItems;
import net.fneifnox.mobtalisman.networking.ModMessages;
import net.fneifnox.mobtalisman.networking.packet.*;
import net.fneifnox.mobtalisman.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobTalisman implements ModInitializer {
	public static final String MOD_ID = "mob-talisman";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModLootTableModifiers.modifyLootTables();
		ModDataComponentTypes.registerDataComponentTypes();
		PayloadTypeRegistry.playC2S().register(BreezeC2SPacket.ID, BreezeC2SPacket.CODEC);
		PayloadTypeRegistry.playC2S().register(GhastC2SPacket.ID, GhastC2SPacket.CODEC);
		PayloadTypeRegistry.playC2S().register(CamelC2SPacket.ID, CamelC2SPacket.CODEC);
		PayloadTypeRegistry.playC2S().register(IronGolemC2SPacket.ID, IronGolemC2SPacket.CODEC);
		PayloadTypeRegistry.playC2S().register(DonkeyC2SPacket.ID, DonkeyC2SPacket.CODEC);
		ModMessages.registerC2SPackets();

		CONFIG.load();
		CONFIG.save();
	}

	public static final MobT CONFIG = MobT.createAndLoad();
}