package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class PhantomTalisman extends AccessoryItem {

    public PhantomTalisman(Settings properties) {
        super(properties);
    }

    private static final Map<UUID, Integer> tickCounter = new HashMap<>();

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        int ticks = tickCounter.getOrDefault(player.getUuid(), 0) + 1;
        for (ItemStack item : getAllRepairableItems(player)) {
            if (item.isDamaged()) {
                Random random = new Random();
                int requiredTicks = random.nextInt(20 * CONFIG.minCooldownForPhantom(), 20 * (CONFIG.maxCooldownForPhantom() + 1)); // Between 24 and 100 seconds
                if (ticks >= requiredTicks) {
                    int repairedDamage = random.nextInt(0 + CONFIG.minDurabilityForPhantom(), 1 + CONFIG.maxDurabilityForPhantom()); // Between 1 and 10 durability
                    item.setDamage(item.getDamage() - repairedDamage);
                    ticks = 0;
                }
            }
        }
        tickCounter.put(player.getUuid(), ticks);
    }

    private List<ItemStack> getAllRepairableItems(ServerPlayerEntity player) {
        List<ItemStack> items = new ArrayList<>();

        items.addAll(player.getInventory().main);
        items.addAll(player.getInventory().armor);
        items.addAll(player.getInventory().offHand);

        return items.stream()
                .filter(stack -> stack.isDamageable() && stack.getDamage() > 0)
                .toList();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.phantom_talisman"));

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForPhantomTalisman();
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
