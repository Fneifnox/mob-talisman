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
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class AxolotlTalisman extends AccessoryItem {

    public AxolotlTalisman(Settings properties) {
        super(properties);
    }

    private static final Map<UUID, Vec3d> lastPositions = new HashMap<>();

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        Vec3d currentPos = player.getPos();
        Vec3d lastPos = lastPositions.get(player.getUuid());

        if (currentPos.equals(lastPos)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, -1, 2, true, false, true));
        }
        else {
            player.removeStatusEffect(StatusEffects.REGENERATION);
        }

        lastPositions.put(player.getUuid(), currentPos);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        player.removeStatusEffect(StatusEffects.REGENERATION);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.axolotl_talisman"));

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForAxolotlTalisman();
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

