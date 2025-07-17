package net.fneifnox.mobtalisman.component.cca;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class WitchBooleanComponent implements BooleanComponent, AutoSyncedComponent {
    private boolean value = false;
    private final Entity provider;

    public WitchBooleanComponent(Entity provider) {
        this.provider = provider;
    }

    @Override
    public boolean getValue() {
        return this.value;
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
        MyComponents.WITCH_FLOATING_ITEM.sync(this.provider);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return true;
    }

    @Override
    public void writeSyncPacket(RegistryByteBuf buf, ServerPlayerEntity player) {
        buf.writeBoolean(value);
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        this.value = buf.readBoolean();
    }

    @Override
    public void readFromNbt(NbtCompound tag, @NotNull RegistryWrapper.WrapperLookup lookup) {
        this.value = tag.getBoolean("value");
    }

    @Override
    public void writeToNbt(NbtCompound tag, @NotNull RegistryWrapper.WrapperLookup lookup) {
        tag.putBoolean("value", this.value);
    }
}