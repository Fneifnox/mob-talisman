package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.cca.BooleanComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.fneifnox.mobtalisman.component.cca.MyComponents.HAS_GLOW_SQUID_TALISMAN;

public class GlowSquidTalisman extends AccessoryItem {

    public GlowSquidTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof PlayerEntity player)) return;
        if (CONFIG.glowSquidTalisman.glowingEffectForEveryEntity()) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, -1, 0, true, false, true));
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            if (!(reference.entity() instanceof PlayerEntity player)) return;
            setBooleanTrue(player);
            if (reference.entity().getWorld().isClient()) {
                setBooleanTrue(player);
            }

            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_BEACON_ACTIVATE, player.getSoundCategory(), 1.0F, 1.0F);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            if (!(reference.entity() instanceof PlayerEntity player)) return;
            setBooleanFalse(player);
            if (CONFIG.glowSquidTalisman.glowingEffectForEveryEntity()) {
                player.removeStatusEffect(StatusEffects.GLOWING);
            }
            if (reference.entity().getWorld().isClient()) {
                setBooleanFalse(player);
                if (CONFIG.glowSquidTalisman.glowingEffectForEveryEntity()) {
                    player.removeStatusEffect(StatusEffects.GLOWING);
                }
            }
        }
    }

    public static boolean useBoolean(Entity provider) {
        return HAS_GLOW_SQUID_TALISMAN.get(provider).getValue();
    }

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = HAS_GLOW_SQUID_TALISMAN.get(provider);
        component.setValue(false);
        HAS_GLOW_SQUID_TALISMAN.sync(provider);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = HAS_GLOW_SQUID_TALISMAN.get(provider);
        component.setValue(true);
        HAS_GLOW_SQUID_TALISMAN.sync(provider);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!CONFIG.glowSquidTalisman.glowingEffectForEveryEntity()) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.glow_squid_talisman.items"));
        }
        else if (CONFIG.glowSquidTalisman.glowingEffectForEveryEntity()) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.glow_squid_talisman.entities"));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForGlowSquidTalisman();
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
