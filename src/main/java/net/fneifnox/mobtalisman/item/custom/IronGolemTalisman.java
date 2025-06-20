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

public class IronGolemTalisman extends AccessoryItem {

    public static final Identifier MAX_HEALTH_ID = Identifier.of("mobtalisman", "max_health_bonus");

    public IronGolemTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH));
            if (attribute != null && attribute.getModifier(MAX_HEALTH_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                        .addPersistentModifier(new EntityAttributeModifier(
                                MAX_HEALTH_ID, 0 + CONFIG.increasedHeartsForIronGolemTalisman(), EntityAttributeModifier.Operation.ADD_VALUE
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
        float chance = CONFIG.increasedHeartsForIronGolemTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.prefix")
                    .append(Text.literal("+" + ((int) chance / 2)).formatted(Formatting.RED))
                    .append(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.prefix")
                    .append(Text.literal("+" + (chance / 2)).formatted(Formatting.RED))
                    .append(Text.translatable("tooltip.mob-talisman.iron_golem_talisman.suffix")));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}






