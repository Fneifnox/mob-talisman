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

public class CatTalisman extends AccessoryItem {

    public static final Identifier MOVEMENT_SPEED_ID = Identifier.of("mobtalisman", "movement_speed_bonus");

    public CatTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED));
            if (attribute != null && attribute.getModifier(MOVEMENT_SPEED_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)
                        .addPersistentModifier(new EntityAttributeModifier(
                                MOVEMENT_SPEED_ID, (0 + CONFIG.increasedMovementSpeedForCatTalisman()) / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)
                    .removeModifier(MOVEMENT_SPEED_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.increasedMovementSpeedForCatTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.cat_talisman.prefix")
                    .append(Text.literal("+" + (int) chance).formatted(Formatting.AQUA))
                    .append(Text.translatable("tooltip.mob-talisman.cat_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.cat_talisman.prefix")
                    .append(Text.literal("+" + chance).formatted(Formatting.AQUA))
                    .append(Text.translatable("tooltip.mob-talisman.cat_talisman.suffix")));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
