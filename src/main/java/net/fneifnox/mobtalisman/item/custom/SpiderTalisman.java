package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.cca.BooleanComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

import static net.fneifnox.mobtalisman.component.cca.MyComponents.HAS_SPIDER_TALISMAN;


public class SpiderTalisman extends AccessoryItem {

    public SpiderTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            if (!(reference.entity() instanceof PlayerEntity player)) return;
            setBooleanTrue(player);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!reference.entity().getWorld().isClient()) {
            if (!(reference.entity() instanceof PlayerEntity player)) return;
            setBooleanFalse(player);
        }
    }

    public static boolean useBoolean(Entity provider) {
        return HAS_SPIDER_TALISMAN.get(provider).getValue();
    }

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = HAS_SPIDER_TALISMAN.get(provider);
        component.setValue(false);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = HAS_SPIDER_TALISMAN.get(provider);
        component.setValue(true);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.spider_talisman"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
