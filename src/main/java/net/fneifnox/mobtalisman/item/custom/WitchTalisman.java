package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.fneifnox.mobtalisman.component.cca.BooleanComponent;
import net.fneifnox.mobtalisman.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.fneifnox.mobtalisman.component.cca.MyComponents.WITCH_FLOATING_ITEM;

public class WitchTalisman extends AccessoryItem {
 
    public WitchTalisman(Settings properties) {
        super(properties);
    }

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        double health = player.getHealth();
        float maxHealth = player.getMaxHealth();

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_WITCH, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_WITCH, ticks);
        boolean usable = stack.getOrDefault(ModDataComponentTypes.ABILITY_USABLE_WITCH, false);

        if (ticks >= (CONFIG.cooldownForWitchTalisman() * 20)) { // 3600 Ticks = 3 Minutes
            usable = true;
            stack.set(ModDataComponentTypes.ABILITY_USABLE_WITCH, usable);
        }

        if (health <= 6 && health >= 0.1 && usable) {
            player.setHealth(maxHealth);
            ticks = 0;
            usable = false;
            setBooleanTrue(player);
            stack.set(ModDataComponentTypes.ABILITY_USABLE_WITCH, usable);
        }

        tickCounter.put(player.getUuid(), ticks);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        setBooleanFalse(player);
    }

    public static boolean useBoolean(Entity provider) {
        return WITCH_FLOATING_ITEM.get(provider).getValue();
    }

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = WITCH_FLOATING_ITEM.get(provider);
        component.setValue(false);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = WITCH_FLOATING_ITEM.get(provider);
        component.setValue(true);
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

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForWitchTalisman();
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
