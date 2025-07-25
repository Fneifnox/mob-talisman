package net.fneifnox.mobtalisman.component.cca;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.minecraft.inventory.Inventories.readNbt;
import static net.minecraft.inventory.Inventories.writeNbt;

public class DonkeyItemStacksComponent implements ItemStacksComponent, AutoSyncedComponent {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(CONFIG.backpackRowsForDonkeyTalisman() * 9, ItemStack.EMPTY);
    private final Entity provider;

    public DonkeyItemStacksComponent(Entity provider) {
        this.provider = provider;
    }

    @Override
    public DefaultedList<ItemStack> getValue() {
        return inventory;
    }

    @Override
    public void setValue(DefaultedList<ItemStack> value) {
        this.inventory = value;
        MyComponents.DONKEY_ITEMSTACKS_INVENTORY.sync(this.provider);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return true;
    }

    @Override
    public void writeSyncPacket(RegistryByteBuf buf, ServerPlayerEntity player) {
        NbtCompound tag = new NbtCompound();
        buf.writeNbt(tag);
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        buf.readNbt();
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup lookup) {
        readNbt(tag, this.inventory, lookup);
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup lookup) {
        writeNbt(tag, this.inventory, lookup);
    }
}
