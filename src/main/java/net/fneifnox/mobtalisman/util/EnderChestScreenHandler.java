package net.fneifnox.mobtalisman.util;

import net.fneifnox.mobtalisman.item.custom.EndermiteTalisman;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public class EnderChestScreenHandler extends GenericContainerScreenHandler {
    private final PlayerEntity player;

    public EnderChestScreenHandler(PlayerEntity player, int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ScreenHandlerType.GENERIC_9X3, syncId, playerInventory, inventory, 3);
        this.player = player;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        player.getWorld().playSound(null, player.getX() + 0.5, player.getY() + 0.5, player.getZ() + 0.5,
                SoundEvents.BLOCK_ENDER_CHEST_CLOSE, player.getSoundCategory(), 1.0F, 1.0F);
    }
}