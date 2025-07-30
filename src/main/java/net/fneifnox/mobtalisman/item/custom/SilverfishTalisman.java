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

public class SilverfishTalisman extends AccessoryItem {

    public static final Identifier BLOCK_BREAK_SPEED_ID = Identifier.of("mobtalisman", "block_break_speed_bonus");

    public SilverfishTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED));
            if (attribute != null && attribute.getModifier(BLOCK_BREAK_SPEED_ID) == null) {
                reference.entity().getAttributeInstance(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED)
                        .addPersistentModifier(new EntityAttributeModifier(
                                BLOCK_BREAK_SPEED_ID, (0 + CONFIG.silverfishTalisman.increasedMiningSpeedForSilverfishTalisman()) / 100, EntityAttributeModifier.Operation.ADD_VALUE
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED)
                    .removeModifier(BLOCK_BREAK_SPEED_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.silverfishTalisman.increasedMiningSpeedForSilverfishTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.silverfish_talisman.prefix")
                    .append(Text.literal("+" + (int) chance).formatted(Formatting.DARK_PURPLE))
                    .append(Text.translatable("tooltip.mob-talisman.silverfish_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.silverfish_talisman.prefix")
                    .append(Text.literal("+" + chance).formatted(Formatting.DARK_PURPLE))
                    .append(Text.translatable("tooltip.mob-talisman.silverfish_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForSilverfishTalisman();
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
