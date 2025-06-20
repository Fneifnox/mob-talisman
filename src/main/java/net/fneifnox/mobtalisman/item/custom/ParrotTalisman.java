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

public class ParrotTalisman extends AccessoryItem {

    private static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public ParrotTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        int ticks = tickCounter.getOrDefault(player.getUuid(), 0 + stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_PARROT, 0)) + 1;
        stack.set(ModDataComponentTypes.TICK_COUNTER_PARROT, ticks);

        if (ticks >= (CONFIG.cooldownForParrotTalisman() * 20)) { // 1200 Ticks = 60 Seconds
            ticks = 0;
            giveRandomDye(player);
        }

        tickCounter.put(player.getUuid(), ticks);
    }

    private void giveRandomDye(ServerPlayerEntity player) {
        DyeColor[] colors = DyeColor.values();
        DyeColor randomColor = colors[player.getRandom().nextInt(colors.length)];

        Item dyeItem = switch (randomColor) {
            case WHITE -> Items.WHITE_DYE;
            case ORANGE -> Items.ORANGE_DYE;
            case MAGENTA -> Items.MAGENTA_DYE;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_DYE;
            case YELLOW -> Items.YELLOW_DYE;
            case LIME -> Items.LIME_DYE;
            case PINK -> Items.PINK_DYE;
            case GRAY -> Items.GRAY_DYE;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_DYE;
            case CYAN -> Items.CYAN_DYE;
            case PURPLE -> Items.PURPLE_DYE;
            case BLUE -> Items.BLUE_DYE;
            case BROWN -> Items.BROWN_DYE;
            case GREEN -> Items.GREEN_DYE;
            case RED -> Items.RED_DYE;
            case BLACK -> Items.BLACK_DYE;
        };

        player.giveItemStack(new ItemStack(dyeItem));
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        tickCounter.remove(player);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.parrot_talisman"));
        int ticks = stack.getOrDefault(ModDataComponentTypes.TICK_COUNTER_PARROT, 0);
        int seconds = CONFIG.cooldownForParrotTalisman();
        seconds -= ticks / 20;
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        if (seconds > 0 && minutes <= 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.parrot_talisman.cooldown.sec", seconds));
        }
        else if (minutes > 0 && secondsLeft == 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.parrot_talisman.cooldown.min", minutes));
        }
        else if (minutes > 0 && secondsLeft > 0) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.parrot_talisman.cooldown.min_sec", minutes, secondsLeft));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}