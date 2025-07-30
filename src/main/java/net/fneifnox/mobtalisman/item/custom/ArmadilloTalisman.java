package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.fneifnox.mobtalisman.component.cca.BooleanComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.fneifnox.mobtalisman.component.cca.MyComponents.ARMADILLO_FLOATING_ITEM;

public class ArmadilloTalisman extends AccessoryItem {

    public ArmadilloTalisman(Settings properties) {
        super(properties);
    }

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();
    public static final Map<UUID, Boolean> equippedPlayers = new WeakHashMap<>();
    public static final Map<UUID, Boolean> resistanceGiven = new WeakHashMap<>();

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_ARMADILLO, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_ARMADILLO, ticks);

        equippedPlayers.put(player.getUuid(), stack.getOrDefault(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, false));
        stack.set(ModDataComponentTypes.RESISTANCE_GIVEN_ARMADILLO, resistanceGiven.getOrDefault(player.getUuid(), false));

        if (ticks >= (CONFIG.armadilloTalisman.cooldownForArmadilloTalisman() * 20)) {
            stack.set(ModDataComponentTypes.ABILITY_USABLE_ARMADILLO, true);
        }

        if (stack.getOrDefault(ModDataComponentTypes.ABILITY_USABLE_ARMADILLO, false) == true) {
            stack.set(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, true);
        }

        if (!player.hasStatusEffect(StatusEffects.RESISTANCE) && stack.getOrDefault(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, false) == true
                && stack.getOrDefault(ModDataComponentTypes.RESISTANCE_GIVEN_ARMADILLO, false) == true) {
            ticks = 0;
            ArmadilloTalisman.PlayerEndArmadilloTalismanAbility(stack, player);
        }

        tickCounter.put(player.getUuid(), ticks);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        setBooleanFalse(player);
    }

    public static void PlayerEndArmadilloTalismanAbility(ItemStack stack, ServerPlayerEntity player) {
        stack.set(ModDataComponentTypes.ABILITY_USABLE_ARMADILLO, false);
        stack.set(ModDataComponentTypes.TICK_COUNTER_ARMADILLO, 0);
        stack.set(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, false);
        stack.set(ModDataComponentTypes.RESISTANCE_GIVEN_ARMADILLO, false);
        resistanceGiven.put(player.getUuid(), false);
    }

    public static boolean useBoolean(Entity provider) {
        return ARMADILLO_FLOATING_ITEM.get(provider).getValue();
    }

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = ARMADILLO_FLOATING_ITEM.get(provider);
        component.setValue(false);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = ARMADILLO_FLOATING_ITEM.get(provider);
        component.setValue(true);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int duration = CONFIG.armadilloTalisman.durationForArmadilloTalisman();
        tooltip.add(Text.translatable("tooltip.mob-talisman.armadillo_talisman.prefix")
                .append(Text.literal("" + duration).formatted(Formatting.YELLOW))
                .append(Text.translatable("tooltip.mob-talisman.armadillo_talisman.suffix")));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_ARMADILLO, 0);
        int seconds = CONFIG.armadilloTalisman.cooldownForArmadilloTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0 && stack.getOrDefault(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, false) == false) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.armadillo_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0 && stack.getOrDefault(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, false) == false) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.armadillo_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0 && stack.getOrDefault(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, false) == false) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.armadillo_talisman.cooldown.min_sec", minutes, secondsLeft));
        }
        else if (seconds <= 0 && stack.getOrDefault(ModDataComponentTypes.RESISTANCE_GIVEN_ARMADILLO, false) == false) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.armadillo_talisman.ready"));
        }
        else if (stack.getOrDefault(ModDataComponentTypes.EQUIPPED_PLAYERS_ARMADILLO, false) == true) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.armadillo_talisman.used"));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForArmadilloTalisman();
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
