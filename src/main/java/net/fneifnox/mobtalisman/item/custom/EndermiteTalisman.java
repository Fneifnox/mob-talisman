package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fneifnox.mobtalisman.util.EnderChestScreenHandler;
import net.fneifnox.mobtalisman.util.EnderChestScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.TypedActionResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class EndermiteTalisman extends AccessoryItem {

    private static final Map<UUID, Boolean> equippedPlayers = new HashMap<>();

    public EndermiteTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            UseItemCallback.EVENT.register((player, world, hand) -> {
                ItemStack enderStack = player.getStackInHand(hand);

                if (enderStack.getItem() == Items.ENDER_CHEST) {
                    if (!world.isClient && playerHasEndermiteTalismanEquipped(player)) {
                        player.openHandledScreen(new EnderChestScreenHandlerFactory(player));
                        player.incrementStat(Stats.OPEN_ENDERCHEST);
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.BLOCK_ENDER_CHEST_OPEN, player.getSoundCategory(), 1.0F, 1.0F);
                    }
                    return TypedActionResult.success(enderStack);
                }

                return TypedActionResult.pass(enderStack);
            });

            UUID playerId = reference.entity().getUuid();
            equippedPlayers.put(playerId, true);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            UUID playerId = reference.entity().getUuid();
            equippedPlayers.put(playerId, false);
        }
    }

    public static boolean playerHasEndermiteTalismanEquipped(PlayerEntity player) {
        return equippedPlayers.getOrDefault(player.getUuid(), false);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.endermite_talisman"));

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForEndermiteTalisman();
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
