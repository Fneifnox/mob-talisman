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
import net.minecraft.util.DyeColor;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class SheepTalisman extends AccessoryItem {

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public SheepTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_SHEEP, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_SHEEP, ticks);

        if (ticks >= (CONFIG.cooldownForSheepTalisman() * 20)) { // 1200 Ticks = 60 Seconds
            ticks = 0;
            giveRandomWool(player);
        }

        tickCounter.put(player.getUuid(), ticks);
    }

    private void giveRandomWool(ServerPlayerEntity player) {
        DyeColor[] colors = DyeColor.values();
        DyeColor randomColor = colors[player.getRandom().nextInt(colors.length)];

        Item woolItem = switch (randomColor) {
            case WHITE -> Items.WHITE_WOOL;
            case ORANGE -> Items.ORANGE_WOOL;
            case MAGENTA -> Items.MAGENTA_WOOL;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_WOOL;
            case YELLOW -> Items.YELLOW_WOOL;
            case LIME -> Items.LIME_WOOL;
            case PINK -> Items.PINK_WOOL;
            case GRAY -> Items.GRAY_WOOL;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_WOOL;
            case CYAN -> Items.CYAN_WOOL;
            case PURPLE -> Items.PURPLE_WOOL;
            case BLUE -> Items.BLUE_WOOL;
            case BROWN -> Items.BROWN_WOOL;
            case GREEN -> Items.GREEN_WOOL;
            case RED -> Items.RED_WOOL;
            case BLACK -> Items.BLACK_WOOL;
        };

        player.giveItemStack(new ItemStack(woolItem));
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        tickCounter.remove(player);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.sheep_talisman"));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_SHEEP, 0);
        int seconds = CONFIG.cooldownForSheepTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.sheep_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.sheep_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.sheep_talisman.cooldown.min_sec", minutes, secondsLeft));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}

