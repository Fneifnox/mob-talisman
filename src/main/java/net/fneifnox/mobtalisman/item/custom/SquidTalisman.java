package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;

import java.util.List;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class SquidTalisman extends AccessoryItem {

    private static final Map<UUID, Boolean> equippedPlayers = new HashMap<>();

    public SquidTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
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

    public static boolean playerHasSquidTalismanEquipped(PlayerEntity player) {
        return equippedPlayers.getOrDefault(player.getUuid(), false);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int duration = CONFIG.durationForSquidTalisman();
        tooltip.add(Text.translatable("tooltip.mob-talisman.squid_talisman.prefix")
                .append(Text.literal("" + duration).formatted(Formatting.YELLOW))
                .append(Text.translatable("tooltip.mob-talisman.squid_talisman.suffix")));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
