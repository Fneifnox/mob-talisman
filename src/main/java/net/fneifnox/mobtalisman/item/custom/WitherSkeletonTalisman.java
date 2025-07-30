package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class WitherSkeletonTalisman extends AccessoryItem {

    private static final Map<UUID, Boolean> equippedPlayers = new HashMap<>();

    public WitherSkeletonTalisman(Settings properties) {
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

    public static boolean playerHasWitherSkeletonTalismanEquipped(PlayerEntity player) {
        return equippedPlayers.getOrDefault(player.getUuid(), false);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.witherSkeletonTalisman.increasedWitherSkeletonSkullChanceForWitherSkeletonTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.wither_skeleton_talisman.prefix")
                    .append(Text.literal("" + (int) chance).formatted(Formatting.YELLOW))
                    .append(Text.translatable("tooltip.mob-talisman.wither_skeleton_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.wither_skeleton_talisman.prefix")
                    .append(Text.literal("" + chance).formatted(Formatting.YELLOW))
                    .append(Text.translatable("tooltip.mob-talisman.wither_skeleton_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForWitherSkeletonTalisman();
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
