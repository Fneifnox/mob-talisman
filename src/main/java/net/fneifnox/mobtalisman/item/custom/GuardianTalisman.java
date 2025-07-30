package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class GuardianTalisman extends AccessoryItem {

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public GuardianTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_GUARDIAN, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_GUARDIAN, ticks);

        if (ticks >= (CONFIG.guardianTalisman.cooldownForGuardianTalisman() * 20)) { // 1200 Ticks = 60 Seconds
            ticks = 0;
            givePrismarine(player);
        }

        tickCounter.put(player.getUuid(), ticks);
    }

    private void givePrismarine(ServerPlayerEntity player) {
        int randomValue = player.getRandom().nextInt(4);

        Item prismarineItem = switch (randomValue) {
            case 0 -> Items.PRISMARINE;
            case 1 -> Items.DARK_PRISMARINE;
            case 2 -> Items.PRISMARINE_BRICKS;
            case 3 -> Items.SEA_LANTERN;
            default -> throw new IllegalStateException("Unexpected value: " + randomValue);
        };

        player.giveItemStack(new ItemStack(prismarineItem));
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        tickCounter.remove(player);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.guardian_talisman"));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_GUARDIAN, 0);
        int seconds = CONFIG.guardianTalisman.cooldownForGuardianTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.guardian_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.guardian_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.guardian_talisman.cooldown.min_sec", minutes, secondsLeft));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForGuardianTalisman();
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
