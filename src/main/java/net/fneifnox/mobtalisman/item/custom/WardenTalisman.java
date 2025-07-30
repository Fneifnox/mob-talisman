package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class WardenTalisman extends AccessoryItem {

    public static final Identifier MAX_HEALTH_ID = Identifier.of("mobtalisman", "max_health_bonus");

    public WardenTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH));
            if (attribute != null && attribute.getModifier(MAX_HEALTH_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                        .addPersistentModifier(new EntityAttributeModifier(
                                MAX_HEALTH_ID, 0 + CONFIG.wardenTalisman.increasedHeartsForWardenTalisman(), EntityAttributeModifier.Operation.ADD_VALUE
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                    .removeModifier(MAX_HEALTH_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.wardenTalisman.increasedHeartsForWardenTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.warden_talisman.prefix")
                    .append(Text.literal("+" + ((int) chance / 2)).formatted(Formatting.RED))
                    .append(Text.translatable("tooltip.mob-talisman.warden_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.warden_talisman.prefix")
                    .append(Text.literal("+" + (chance / 2)).formatted(Formatting.RED))
                    .append(Text.translatable("tooltip.mob-talisman.warden_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForWardenTalisman();
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
