package net.fneifnox.mobtalisman.item.custom;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class AllayTalisman extends AccessoryItem {

    public static final Identifier COLLECTION_RANGE_ID = Identifier.of("mobtalisman", "collection_range_bonus");

    public AllayTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {

            var attribute = (reference.entity().getAttributeInstance(AdditionalEntityAttributes.COLLECTION_RANGE));
            if (attribute != null && attribute.getModifier(COLLECTION_RANGE_ID) == null) {
                reference.entity().getAttributeInstance(AdditionalEntityAttributes.COLLECTION_RANGE)
                        .addPersistentModifier(new EntityAttributeModifier(
                                COLLECTION_RANGE_ID, (0 + CONFIG.increasedCollectionRangeForAllayTalisman()) / 100, EntityAttributeModifier.Operation.ADD_VALUE
                        ));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            reference.entity().getAttributeInstance(AdditionalEntityAttributes.COLLECTION_RANGE)
                    .removeModifier(COLLECTION_RANGE_ID);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.increasedCollectionRangeForAllayTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.cat_talisman.prefix")
                    .append(Text.literal("+" + (int) chance).formatted(Formatting.GOLD))
                    .append(Text.translatable("tooltip.mob-talisman.allay_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.cat_talisman.prefix")
                    .append(Text.literal("+" + chance).formatted(Formatting.GOLD))
                    .append(Text.translatable("tooltip.mob-talisman.allay_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForAllayTalisman();
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
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
