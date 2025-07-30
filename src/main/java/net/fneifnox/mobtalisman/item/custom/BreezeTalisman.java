package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.fneifnox.mobtalisman.component.cca.BooleanComponent;
import net.fneifnox.mobtalisman.event.KeyInputHandler;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.fneifnox.mobtalisman.component.cca.MyComponents.BREEZE_TALISMAN_ABILITY_READY;

public class BreezeTalisman extends AccessoryItem {

    public static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();
    public static final Map<UUID, Boolean> ResetTickCount = new WeakHashMap<>();
    public static final Map<UUID, Boolean> abilityReady = new WeakHashMap<>();
    public static final Map<UUID, Boolean> abilityReadyCooldownNowRuns = new WeakHashMap<>();

    public BreezeTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_BREEZE, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_BREEZE, ticks);

        if (ticks >= (CONFIG.breezeTalisman.cooldownForBreezeTalisman() * 20)) {
            setBooleanTrue(player);
        }

        if (ticks >= (CONFIG.breezeTalisman.cooldownForBreezeTalisman() * 20) && abilityReadyCooldownNowRuns.getOrDefault(player.getUuid(), true)) {
            abilityReadyCooldownNowRuns.put(player.getUuid(), false);
            abilityReady.put(player.getUuid(), true);
        }

        if (abilityReady.getOrDefault(player.getUuid(), false) && CONFIG.breezeTalisman.cooldownForBreezeTalisman() > 3) {
            abilityReady.put(player.getUuid(), false);
            player.sendMessage(Text.translatable("message.mob-talisman.breeze_talisman.ability_ready"));
        }

        if (ResetTickCount.getOrDefault(player.getUuid(), false)) {
            ticks = 0;
            abilityReadyCooldownNowRuns.put(player.getUuid(), true);
            ResetTickCount.put(player.getUuid(), false);
            setBooleanFalse(player);
        }
        tickCounter.put(player.getUuid(), ticks);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        setBooleanFalse(player);
    }

    public static boolean useBoolean(Entity provider) {
        return BREEZE_TALISMAN_ABILITY_READY.get(provider).getValue();
    }

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = BREEZE_TALISMAN_ABILITY_READY.get(provider);
        component.setValue(false);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = BREEZE_TALISMAN_ABILITY_READY.get(provider);
        component.setValue(true);
    }

    public static void SetTickCountTrue(ServerPlayerEntity player) {
        ResetTickCount.put(player.getUuid(), true);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        String key = KeyInputHandler.breezeAbilityKey.getBoundKeyLocalizedText().getString();
        tooltip.add(Text.translatable("tooltip.mob-talisman.breeze_talisman.prefix")
                .append(Text.literal("" + key).formatted(Formatting.GOLD))
                .append(Text.translatable("tooltip.mob-talisman.breeze_talisman.suffix")));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_BREEZE, 0);
        int seconds = CONFIG.breezeTalisman.cooldownForBreezeTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.breeze_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.breeze_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.breeze_talisman.cooldown.min_sec", minutes, secondsLeft));
        }
        else if (seconds <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.breeze_talisman.ready"));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForBreezeTalisman();
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
