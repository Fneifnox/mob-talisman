package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

public class GlowSquidTalisman extends AccessoryItem {

    public GlowSquidTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, -1, 0, true, false, true));
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        player.removeStatusEffect(StatusEffects.GLOWING);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.glow_squid_talisman"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
