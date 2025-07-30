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

public class RabbitTalisman extends AccessoryItem {

    public static final Identifier JUMP_STRENGTH_ID = Identifier.of("mobtalisman", "jump_strength_bonus");

    public RabbitTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH));
            if (attribute != null && attribute.getModifier(JUMP_STRENGTH_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH)
                        .addPersistentModifier(new EntityAttributeModifier(
                                JUMP_STRENGTH_ID, (0 + CONFIG.rabbitTalisman.increasedJumpHeightForRabbitTalisman()) / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH)
                    .removeModifier(JUMP_STRENGTH_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.rabbitTalisman.increasedJumpHeightForRabbitTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.rabbit_talisman.prefix")
                    .append(Text.literal("+" + (int) chance).formatted(Formatting.DARK_GREEN))
                    .append(Text.translatable("tooltip.mob-talisman.rabbit_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.rabbit_talisman.prefix")
                    .append(Text.literal("+" + chance).formatted(Formatting.DARK_GREEN))
                    .append(Text.translatable("tooltip.mob-talisman.rabbit_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForRabbitTalisman();
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
