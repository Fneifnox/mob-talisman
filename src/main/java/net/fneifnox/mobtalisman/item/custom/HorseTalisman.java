package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class HorseTalisman implements Accessory {

    public static final Identifier STEP_HEIGHT_ID = Identifier.of("mobtalisman", "step_height");

    public static final EntityAttribute STEP_HEIGHT = new ClampedEntityAttribute(
            "attribute.name.generic.step_height",
            0.6D, 0.0D, 10.0D
    ).setTracked(true);

    private static final Identifier STEP_HEIGHT_MODIFIER_ID = Identifier.of("mobtalisman", "step_height_boost");

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        RegistryEntry<EntityAttribute> attributeEntry = Registries.ATTRIBUTE.getEntry(STEP_HEIGHT);

        var instance = player.getAttributeInstance(attributeEntry);
        if (instance != null && !instance.hasModifier(STEP_HEIGHT_MODIFIER_ID)) {
            instance.addPersistentModifier(new EntityAttributeModifier(
                    STEP_HEIGHT_MODIFIER_ID,
                    1.0,
                    EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        RegistryEntry<EntityAttribute> attributeEntry = Registries.ATTRIBUTE.getEntry(STEP_HEIGHT);

        var instance = player.getAttributeInstance(attributeEntry);
        if (instance != null) {
            instance.removeModifier(STEP_HEIGHT_MODIFIER_ID);
        }
    }
}




