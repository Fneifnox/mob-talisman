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

public class HorseTalisman extends AccessoryItem {

    public static final Identifier STEP_HEIGHT_ID = Identifier.of("mobtalisman", "step_height_bonus");

    public HorseTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT));
            if (attribute != null && attribute.getModifier(STEP_HEIGHT_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT)
                        .addPersistentModifier(new EntityAttributeModifier(
                                STEP_HEIGHT_ID, 0 + CONFIG.horseTalisman.increasedStepHeightForHorseTalisman(), EntityAttributeModifier.Operation.ADD_VALUE
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT)
                    .removeModifier(STEP_HEIGHT_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.horseTalisman.increasedStepHeightForHorseTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.horse_talisman.prefix")
                    .append(Text.literal("+" + (int) chance).formatted(Formatting.DARK_GREEN))
                    .append(Text.translatable("tooltip.mob-talisman.horse_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.horse_talisman.prefix")
                    .append(Text.literal("+" + chance).formatted(Formatting.DARK_GREEN))
                    .append(Text.translatable("tooltip.mob-talisman.horse_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForHorseTalisman();
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




