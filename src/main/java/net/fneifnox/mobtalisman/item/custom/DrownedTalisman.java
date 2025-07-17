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

public class DrownedTalisman extends AccessoryItem {

    public static final Identifier OXYGEN_BONUS_ID = Identifier.of("mobtalisman", "oxygen_bonus_bonus");

    public DrownedTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.GENERIC_OXYGEN_BONUS));
            if (attribute != null && attribute.getModifier(OXYGEN_BONUS_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.GENERIC_OXYGEN_BONUS)
                        .addPersistentModifier(new EntityAttributeModifier(
                                OXYGEN_BONUS_ID, (0 + CONFIG.increasedUnderwaterBreathingForDrownedTalisman()) / 100, EntityAttributeModifier.Operation.ADD_VALUE
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(EntityAttributes.GENERIC_OXYGEN_BONUS)
                    .removeModifier(OXYGEN_BONUS_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.increasedUnderwaterBreathingForDrownedTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.drowned_talisman.prefix")
                    .append(Text.literal("+" + (int) chance).formatted(Formatting.DARK_AQUA))
                    .append(Text.translatable("tooltip.mob-talisman.drowned_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.drowned_talisman.prefix")
                    .append(Text.literal("+" + chance).formatted(Formatting.DARK_AQUA))
                    .append(Text.translatable("tooltip.mob-talisman.drowned_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForDrownedTalisman();
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
