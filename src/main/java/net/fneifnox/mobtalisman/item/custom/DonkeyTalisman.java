package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fneifnox.mobtalisman.component.cca.BooleanComponent;
import net.fneifnox.mobtalisman.event.KeyInputHandler;
import net.fneifnox.mobtalisman.util.EnderChestScreenHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.TypedActionResult;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.fneifnox.mobtalisman.component.cca.MyComponents.DONKEY_TALISMAN_ABILITY_READY;

public class DonkeyTalisman extends AccessoryItem {

    private static final Map<UUID, Boolean> equippedPlayers = new HashMap<>();

    public DonkeyTalisman(Item.Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            UUID playerId = reference.entity().getUuid();
            equippedPlayers.put(playerId, true);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            UUID playerId = reference.entity().getUuid();
            equippedPlayers.put(playerId, false);
        }
    }

    public static boolean playerHasDonkeyTalismanEquipped(PlayerEntity player) {
        return equippedPlayers.getOrDefault(player.getUuid(), false);
    }

    public static boolean useBoolean(Entity provider) {
        return DONKEY_TALISMAN_ABILITY_READY.get(provider).getValue();
    }

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = DONKEY_TALISMAN_ABILITY_READY.get(provider);
        component.setValue(false);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = DONKEY_TALISMAN_ABILITY_READY.get(provider);
        component.setValue(true);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        String key = KeyInputHandler.donkeyAbilityKey.getBoundKeyLocalizedText().getString();
        tooltip.add(Text.translatable("tooltip.mob-talisman.donkey_talisman.prefix")
                .append(Text.literal("" + key).formatted(Formatting.GOLD))
                .append(Text.translatable("tooltip.mob-talisman.donkey_talisman.suffix")));

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForDonkeyTalisman();
            if (dropchance == (int) dropchance) {
                tooltip.add(Text.translatable("tooltip.mob-talisman.dropchance")
                        .append(Text.literal("" + (int) dropchance + "%").formatted(Formatting.GRAY)));
            }
            else {
                tooltip.add(Text.translatable("tooltip.mob-talisman.dropchance")
                        .append(Text.literal("" + dropchance + "%").formatted(Formatting.GRAY)));
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}