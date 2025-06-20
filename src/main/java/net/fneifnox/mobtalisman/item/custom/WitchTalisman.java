package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class WitchTalisman extends AccessoryItem {
 
    public WitchTalisman(Settings properties) {
        super(properties);
    }

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();
    private static final Map<UUID, Boolean> abilityUsable = new WeakHashMap<>();

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        double health = player.getHealth();

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_WITCH, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_WITCH, ticks);
        boolean usable = abilityUsable.getOrDefault(player.getUuid(), false);

        System.out.println("Ticks: " + ticks);

        if (ticks >= (CONFIG.cooldownForWitchTalisman() * 20)) { // 3600 Ticks = 3 Minutes
            usable = true;
            stack.set(ModDataComponentTypes.ABILITY_USABLE_WITCH, usable);
        }

        if (health <= 6 && usable == true) {
            player.setHealth(20);
            ticks = 0;
            usable = false;
            stack.set(ModDataComponentTypes.ABILITY_USABLE_WITCH, usable);
        }

        tickCounter.put(player.getUuid(), ticks);
        abilityUsable.put(player.getUuid(), usable);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.witch_talisman"));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_WITCH, 0);
        boolean abilityUsable = stack.getOrDefault(ModDataComponentTypes.ABILITY_USABLE_WITCH, false);
        int seconds = CONFIG.cooldownForWitchTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.witch_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.witch_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.witch_talisman.cooldown.min_sec", minutes, secondsLeft));
        }
        else if (seconds <= 0 && abilityUsable == true) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.witch_talisman.ready"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
