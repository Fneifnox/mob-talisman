package net.fneifnox.mobtalisman.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fneifnox.mobtalisman.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.HORSE_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAT_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_GOLEM_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.RABBIT_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDEN_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRIDER_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAT_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.DOLPHIN_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.DROWNED_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_SQUID_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHEEP_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BREEZE_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITCH_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHULKER_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TADPOLE_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TURTLE_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PHANTOM_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.COW_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PARROT_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SQUID_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.EVOKER_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.GUARDIAN_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ELDER_GUARDIAN_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAVAGER_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOAT_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITHER_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALLAY_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVERFISH_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIG_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.VILLAGER_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIGLIN_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ZOMBIE_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SKELETON_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZE_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARMADILLO_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_DRAGON_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUFFERFISH_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPIDER_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.AXOLOTL_TALISMAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERMAN_TALISMAN, Models.GENERATED);
    }
}
