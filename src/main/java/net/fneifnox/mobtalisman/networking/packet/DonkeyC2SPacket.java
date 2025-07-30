package net.fneifnox.mobtalisman.networking.packet;

import net.fneifnox.mobtalisman.component.cca.ItemStacksComponent;
import net.fneifnox.mobtalisman.component.cca.MyComponents;
import net.fneifnox.mobtalisman.item.custom.DonkeyTalisman;
import net.fneifnox.mobtalisman.util.DonkeyBackpackScreenHandler;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public record DonkeyC2SPacket() implements CustomPayload {

    public static final Id<DonkeyC2SPacket> ID = new Id<>(Identifier.of("mobtalisman", "donkey_id"));
    public static final Map<UUID, Integer> tickCounter = new WeakHashMap<>();

    public static final PacketCodec<RegistryByteBuf, DonkeyC2SPacket> CODEC =
            PacketCodec.of((buf, value) -> {
            }, buf -> new DonkeyC2SPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(ServerPlayerEntity player) {
        if (!DonkeyTalisman.playerHasDonkeyTalismanEquipped(player)) return;

        ItemStacksComponent container = MyComponents.DONKEY_ITEMSTACKS_INVENTORY.get(player);

        SimpleInventory inventory = new SimpleInventory(CONFIG.donkeyTalisman.backpackRowsForDonkeyTalisman() * 9);

        List<ItemStack> contents = new ArrayList<>(CONFIG.donkeyTalisman.backpackRowsForDonkeyTalisman() * 9);
        for (int i = 0; i < CONFIG.donkeyTalisman.backpackRowsForDonkeyTalisman() * 9; i++) {
            contents.add(ItemStack.EMPTY);
        }

        int index = 0;
        for (Iterator<ItemStack> it = container.getValue().iterator(); it.hasNext(); ) {
            ItemStack item = it.next();
            if (index >= CONFIG.donkeyTalisman.backpackRowsForDonkeyTalisman() * 9) break;
            contents.set(index, item);
            index++;
        }

        for (int i = 0; i < CONFIG.donkeyTalisman.backpackRowsForDonkeyTalisman() * 9; i++) {
            inventory.setStack(i, contents.get(i));
        }

        if (player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.openHandledScreen(new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return Text.translatable("container.mob-talisman.backpack");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new DonkeyBackpackScreenHandler(syncId, playerInventory, inventory);
                }
            });
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_CHEST_OPEN, player.getSoundCategory(), 1.0F, 1.0F);
            PiglinBrain.onGuardedBlockInteracted(player, true);
        }
    }
}