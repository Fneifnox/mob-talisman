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

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.fneifnox.mobtalisman.component.cca.MyComponents.IRON_GOLEM_TALISMAN_ABILITY_READY;

public class IronGolemTalisman extends AccessoryItem {

    public IronGolemTalisman(Settings properties) {
        super(properties);
    }

    public static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();
    public static final Map<UUID, Boolean> ResetTickCount = new WeakHashMap<>();
    public static final Map<UUID, Boolean> abilityReady = new WeakHashMap<>();
    public static final Map<UUID, Boolean> abilityReadyCooldownNowRuns = new WeakHashMap<>();

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_IRON_GOLEM, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_IRON_GOLEM, ticks);

        if (ticks >= (CONFIG.ironGolemTalisman.cooldownForIronGolemTalisman() * 20)) {
            setBooleanTrue(player);
        }

        if (ticks >= (CONFIG.ironGolemTalisman.cooldownForIronGolemTalisman() * 20) && abilityReadyCooldownNowRuns.getOrDefault(player.getUuid(), true)) {
            abilityReadyCooldownNowRuns.put(player.getUuid(), false);
            abilityReady.put(player.getUuid(), true);
        }

        if (abilityReady.getOrDefault(player.getUuid(), false) && CONFIG.ironGolemTalisman.cooldownForIronGolemTalisman() > 3) {
            abilityReady.put(player.getUuid(), false);
            player.sendMessage(Text.translatable("message.mob-talisman.iron_golem_talisman.ability_ready"));
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
        return IRON_GOLEM_TALISMAN_ABILITY_READY.get(provider).getValue();
    }

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = IRON_GOLEM_TALISMAN_ABILITY_READY.get(provider);
        component.setValue(false);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = IRON_GOLEM_TALISMAN_ABILITY_READY.get(provider);
        component.setValue(true);
    }

    public static void SetTickCountTrue(ServerPlayerEntity player) {
        ResetTickCount.put(player.getUuid(), true);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        String key = KeyInputHandler.ironGolemAbilityKey.getBoundKeyLocalizedText().getString();
        tooltip.add(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.prefix")
                .append(Text.literal("" + key).formatted(Formatting.GOLD))
                .append(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.suffix")
                .append(Text.literal("" + CONFIG.ironGolemTalisman.absorptionHeartsForIronGolemTalisman() * 2).formatted(Formatting.RED))
                .append(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.suffix2"))));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_IRON_GOLEM, 0);
        int seconds = CONFIG.ironGolemTalisman.cooldownForIronGolemTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.cooldown.min_sec", minutes, secondsLeft));
        }
        else if (seconds <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.ready"));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForIronGolemTalisman();
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






