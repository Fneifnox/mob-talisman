package net.fneifnox.mobtalisman.component;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class EndermanVec3dComponent implements Vec3dComponent, AutoSyncedComponent {
    private Vec3d value = Vec3d.ZERO;
    private final Entity provider;

    public EndermanVec3dComponent(Entity provider) {
        this.provider = provider;
    }

    @Override
    public Vec3d getValue() {
        return this.value;
    }

    @Override
    public void setValue(Vec3d value) {
        this.value = value;
        MyComponents.HAS_SPIDER_TALISMAN.sync(this.provider);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return true;
    }

    @Override
    public void writeSyncPacket(RegistryByteBuf buf, ServerPlayerEntity player) {
        buf.writeVec3d(value);
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        this.value = buf.readVec3d();
    }

    @Override
    public void readFromNbt(NbtCompound tag, @NotNull RegistryWrapper.WrapperLookup lookup) {
        if (tag.contains("x") && tag.contains("y") && tag.contains("z")) {
            double x = tag.getDouble("x");
            double y = tag.getDouble("y");
            double z = tag.getDouble("z");
            this.value = new Vec3d(x, y, z);
        } else {
            this.value = null;
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag, @NotNull RegistryWrapper.WrapperLookup lookup) {
        if (this.value != null) {
            tag.putDouble("x", this.value.x);
            tag.putDouble("y", this.value.y);
            tag.putDouble("z", this.value.z);
        }
    }
}
