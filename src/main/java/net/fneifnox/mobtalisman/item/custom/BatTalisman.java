package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class BatTalisman extends AccessoryItem {

    public BatTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        player.getAbilities().allowFlying = true;
        player.getAbilities().setFlySpeed(CONFIG.batTalisman.flySpeedForBatTalisman() / 100);
        player.sendAbilitiesUpdate();
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        if (!player.isCreative()) {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
        }
        player.getAbilities().setFlySpeed(0.05f);
        player.sendAbilitiesUpdate();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.bat_talisman"));

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForBatTalisman();
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
