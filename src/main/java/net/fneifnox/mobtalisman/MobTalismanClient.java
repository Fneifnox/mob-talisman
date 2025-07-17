package net.fneifnox.mobtalisman;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fneifnox.mobtalisman.event.KeyInputHandler;
import net.fneifnox.mobtalisman.item.ModItems;
import net.fneifnox.mobtalisman.item.custom.ArmadilloTalisman;
import net.fneifnox.mobtalisman.item.custom.WitchTalisman;
import net.fneifnox.mobtalisman.networking.ModMessages;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

public class MobTalismanClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ModMessages.registerS2CPackets();

        ClientTickEvents.START_WORLD_TICK.register(client -> {
            if (MinecraftClient.getInstance().player == null) return;
            if (ArmadilloTalisman.useBoolean(MinecraftClient.getInstance().player)) {
                System.out.println("A 1 " + ArmadilloTalisman.useBoolean(MinecraftClient.getInstance().player));
                ArmadilloTalisman.setBooleanFalse(MinecraftClient.getInstance().player);
                ItemStack armadilloTalisman = new ItemStack(ModItems.ARMADILLO_TALISMAN);
                MinecraftClient.getInstance().gameRenderer.showFloatingItem(armadilloTalisman);
                MinecraftClient.getInstance().world.playSound(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ(),
                        SoundEvents.ITEM_TOTEM_USE, MinecraftClient.getInstance().player.getSoundCategory(), 1.0F, 1.0F, false);
            }
            if (WitchTalisman.useBoolean(MinecraftClient.getInstance().player)) {
                System.out.println("W 1 " + WitchTalisman.useBoolean(MinecraftClient.getInstance().player));
                WitchTalisman.setBooleanFalse(MinecraftClient.getInstance().player);
                ItemStack witchTalisman = new ItemStack(ModItems.WITCH_TALISMAN);
                MinecraftClient.getInstance().gameRenderer.showFloatingItem(witchTalisman);
                MinecraftClient.getInstance().world.playSound(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ(),
                        SoundEvents.ITEM_TOTEM_USE, MinecraftClient.getInstance().player.getSoundCategory(), 1.0F, 1.0F, false);
            }
        });
    }
}
