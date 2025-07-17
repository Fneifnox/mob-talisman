package net.fneifnox.mobtalisman.util;

import net.fneifnox.mobtalisman.item.custom.ShulkerTalisman;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShulkerBoxScreenHandler extends GenericContainerScreenHandler {
    private final SimpleInventory inventory;
    private final ItemStack shulkerStack;

    public ShulkerBoxScreenHandler(int syncId, PlayerInventory playerInventory, SimpleInventory inventory, ItemStack shulkerStack) {
        super(ScreenHandlerType.GENERIC_9X3, syncId, playerInventory, inventory, 3);
        this.inventory = inventory;
        this.shulkerStack = shulkerStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        saveInventoryToItem();
    }

    private void saveInventoryToItem() {
        List<ItemStack> newContents = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            newContents.add(inventory.getStack(i));
        }

        ContainerComponent newContainer = ContainerComponent.fromStacks(inventory.getHeldStacks());
        shulkerStack.set(DataComponentTypes.CONTAINER, newContainer);

        shulkerStack.set(DataComponentTypes.CONTAINER, newContainer);

    }

    public static void openShulkerBoxFromItem(PlayerEntity player, ItemStack stack) {
        if (!ShulkerTalisman.playerHasShulkerTalismanEquipped(player)) return;
        if (!(stack.getItem() instanceof BlockItem blockItem)) return;
        if (!(blockItem.getBlock() instanceof ShulkerBoxBlock)) return;

        ContainerComponent container = stack.get(DataComponentTypes.CONTAINER);
        if (container == null) container = ContainerComponent.DEFAULT;

        SimpleInventory inventory = new SimpleInventory(27);

        List<ItemStack> contents = new ArrayList<>(27);
        for (int i = 0; i < 27; i++) {
            contents.add(ItemStack.EMPTY);
        }

        int index = 0;
        for (Iterator<ItemStack> it = container.stream().iterator(); it.hasNext(); ) {
            ItemStack item = it.next();
            if (index >= 27) break;
            contents.set(index, item);
            index++;
        }

        for (int i = 0; i < 27; i++) {
            inventory.setStack(i, contents.get(i));
        }

        if (player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.openHandledScreen(new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return stack.getName();
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new ShulkerBoxScreenHandler(syncId, playerInventory, inventory, stack);
                }
            });
            serverPlayer.incrementStat(Stats.OPEN_SHULKER_BOX);
            PiglinBrain.onGuardedBlockInteracted(player, true);
        }
    }
}