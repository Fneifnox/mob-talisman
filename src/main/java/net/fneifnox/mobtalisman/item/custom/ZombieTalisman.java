package net.fneifnox.mobtalisman.item.custom;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class ZombieTalisman extends AccessoryItem {

    public static final Identifier DROPPED_EXPERIENCE_ID = Identifier.of("mobtalisman", "dropped_experience_bonus");

    public ZombieTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(AdditionalEntityAttributes.DROPPED_EXPERIENCE));
            if (attribute != null && attribute.getModifier(DROPPED_EXPERIENCE_ID) == null) {
                reference.entity().getAttributeInstance(AdditionalEntityAttributes.DROPPED_EXPERIENCE)
                        .addPersistentModifier(new EntityAttributeModifier(
                                DROPPED_EXPERIENCE_ID, 100 / CONFIG.increasedXPForZombieTalisman(), EntityAttributeModifier.Operation.ADD_VALUE
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(AdditionalEntityAttributes.DROPPED_EXPERIENCE)
                    .removeModifier(DROPPED_EXPERIENCE_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.increasedXPForZombieTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.zombie_talisman.prefix")
                    .append(Text.literal("+" + (int) chance).formatted(Formatting.GREEN))
                    .append(Text.translatable("tooltip.mob-talisman.zombie_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.zombie_talisman.prefix")
                    .append(Text.literal("+" + chance).formatted(Formatting.GREEN))
                    .append(Text.translatable("tooltip.mob-talisman.zombie_talisman.suffix")));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
