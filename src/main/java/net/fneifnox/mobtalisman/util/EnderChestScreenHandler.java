package net.fneifnox.mobtalisman.util;

import net.fneifnox.mobtalisman.item.custom.EndermiteTalisman;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class EnderChestScreenHandler implements NamedScreenHandlerFactory {
    private final PlayerEntity player;

    public EnderChestScreenHandler(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.enderchest");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        if (EndermiteTalisman.playerHasEndermiteTalismanEquipped(player)) {
            return GenericContainerScreenHandler.createGeneric9x3(syncId, inv, player.getEnderChestInventory());
        }
        return null;
    }
}