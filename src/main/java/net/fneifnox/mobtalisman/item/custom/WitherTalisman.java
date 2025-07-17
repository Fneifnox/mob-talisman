package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class WitherTalisman extends AccessoryItem {

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public WitherTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_WITHER, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_WITHER, ticks);

        if (ticks >= (CONFIG.cooldownForWitherTalisman() * 20)) { // 36000 Ticks = 30 Minutes
            ticks = 0;
            giveRandomItem(player);
        }

        tickCounter.put(player.getUuid(), ticks);
    }

    private void giveRandomItem(ServerPlayerEntity player) {
        List<Item> items = Registries.ITEM.stream().toList();

        Item randomItem = items.get(player.getRandom().nextInt(items.size()));
        player.giveItemStack(new ItemStack(randomItem));
        if (player instanceof ServerPlayerEntity serverPlayer) {
            Identifier id = Identifier.of("mob-talisman", "custom/definitely_skill_based");
            AdvancementEntry entry = Objects.requireNonNull(serverPlayer.getServer()).getAdvancementLoader().get(id);
            serverPlayer.getAdvancementTracker().grantCriterion(entry, "got_item");
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        tickCounter.remove(player);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.wither_talisman"));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_WITHER, 0);
        int seconds = CONFIG.cooldownForWitherTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.wither_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.wither_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.wither_talisman.cooldown.min_sec", minutes, secondsLeft));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForWitherTalisman();
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
