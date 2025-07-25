package net.fneifnox.mobtalisman.util;

import net.fneifnox.mobtalisman.component.cca.ItemStacksComponent;
import net.fneifnox.mobtalisman.component.cca.MyComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.collection.DefaultedList;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class DonkeyBackpackScreenHandler extends GenericContainerScreenHandler {
    private final SimpleInventory inventory;

    public DonkeyBackpackScreenHandler(int syncId, PlayerInventory playerInventory, SimpleInventory inventory) {
        super(getScreenHandlerTypeFor(CONFIG.backpackRowsForDonkeyTalisman()), syncId, playerInventory, inventory, getRowsFor(CONFIG.backpackRowsForDonkeyTalisman()));
        this.inventory = inventory;
    }

    private static ScreenHandlerType<GenericContainerScreenHandler> getScreenHandlerTypeFor(int rows) {
        return switch (rows) {
            case 1 -> ScreenHandlerType.GENERIC_9X1;
            case 2 -> ScreenHandlerType.GENERIC_9X2;
            case 3 -> ScreenHandlerType.GENERIC_9X3;
            case 4 -> ScreenHandlerType.GENERIC_9X4;
            case 5 -> ScreenHandlerType.GENERIC_9X5;
            case 6 -> ScreenHandlerType.GENERIC_9X6;
            default -> ScreenHandlerType.GENERIC_9X1;
        };
    }

    private static int getRowsFor(int rows) {
        return switch (rows) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> 4;
            case 5 -> 5;
            case 6 -> 6;
            default -> 1;
        };
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        saveInventory(player);
    }

    private void saveInventory(PlayerEntity player) {
        ItemStacksComponent component = MyComponents.DONKEY_ITEMSTACKS_INVENTORY.get(player);

        DefaultedList<ItemStack> newContents = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        for (int i = 0; i < inventory.size(); i++) {
            newContents.set(i, inventory.getStack(i));
        }

        component.setValue(newContents);
    }
}
