package net.fneifnox.mobtalisman.component.cca;

import net.minecraft.nbt.NbtCompound;

public interface ComponentInterface {
    void readFromNbt(NbtCompound tag);
    void writeToNbt(NbtCompound tag);
}
