package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.ModDataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class BreezeTalisman extends AccessoryItem {

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public BreezeTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_BREEZE, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_BREEZE, ticks);

        if (ticks >= (CONFIG.cooldownForBreezeTalisman() * 20)) { // 1200 Ticks = 60 Seconds
            ticks = 0;
            giveBreezeRod(player);
        }

        tickCounter.put(player.getUuid(), ticks);
    }

    private void giveBreezeRod(ServerPlayerEntity player) {
        player.giveItemStack(new ItemStack(Items.BREEZE_ROD));
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        tickCounter.remove(player);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.breeze_talisman"));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_BREEZE, 0);
        int seconds = CONFIG.cooldownForBreezeTalisman();
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
        super.appendTooltip(stack, context, tooltip, type);
    }
}
