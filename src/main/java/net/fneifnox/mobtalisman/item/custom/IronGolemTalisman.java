package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class IronGolemTalisman extends AccessoryItem {

    public static final Identifier MAX_HEALTH_ID = Identifier.of("mobtalisman", "attack_damage_bonus");

    public IronGolemTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void getDynamicModifiers(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        if(reference.slotName().equals("necklace")) {
            builder.addStackable(
                EntityAttributes.GENERIC_MAX_HEALTH,
                    new EntityAttributeModifier(
                            MAX_HEALTH_ID,
                            5.0,
                            EntityAttributeModifier.Operation.ADD_VALUE
                    )
            );
        }
    }
}






