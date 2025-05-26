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

        itemModelGenerator.register(ModItems.HORSE_TALISMAN, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BAT_TALISMAN, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_GOLEM_TALISMAN, Models.HANDHELD);
    }
}
