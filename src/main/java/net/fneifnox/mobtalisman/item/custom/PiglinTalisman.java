package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.*;

import java.util.List;

public class PiglinTalisman extends AccessoryItem {

    private static final Map<UUID, Boolean> equippedPlayers = new HashMap<>();

    public PiglinTalisman(Settings properties) {
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

    public static boolean playerHasPiglinTalismanEquipped(PlayerEntity player) {
        return equippedPlayers.getOrDefault(player.getUuid(), false);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.piglin_talisman"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
