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

public class TadpoleTalisman extends AccessoryItem {

    public static final Identifier SCALE_ID = Identifier.of("mobtalisman", "scale_bonus");

    public TadpoleTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.GENERIC_SCALE));
            if (attribute != null && attribute.getModifier(SCALE_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.GENERIC_SCALE)
                        .addPersistentModifier(new EntityAttributeModifier(
                                SCALE_ID, (0 + CONFIG.decreasedScaleForTadpoleTalisman()) / -100, EntityAttributeModifier.Operation.ADD_VALUE
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(EntityAttributes.GENERIC_SCALE)
                    .removeModifier(SCALE_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.decreasedScaleForTadpoleTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.tadpole_talisman.prefix")
                    .append(Text.literal("-" + (int) chance).formatted(Formatting.DARK_AQUA))
                    .append(Text.translatable("tooltip.mob-talisman.tadpole_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.tadpole_talisman.prefix")
                    .append(Text.literal("-" + chance).formatted(Formatting.DARK_AQUA))
                    .append(Text.translatable("tooltip.mob-talisman.tadpole_talisman.suffix")));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
