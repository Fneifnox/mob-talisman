package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SkeletonTalisman extends AccessoryItem {

    public SkeletonTalisman(Settings properties) {
        super(properties);
    }

    private static final Map<UUID, Vec3d> savedDeathPosition = new HashMap<>();

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        teleportToSavedDeathPosition(player);
    }

    public static void lastDeathPosition(Vec3d lastDeathPos, ServerPlayerEntity player)  {
        savedDeathPosition.put(player.getUuid(), lastDeathPos);
    }

    public static Vec3d getSavedDeathPosition(ServerPlayerEntity player) {
        return savedDeathPosition.get(player.getUuid());
    }

    public static void teleportToSavedDeathPosition(ServerPlayerEntity player) {
        Vec3d position = SkeletonTalisman.getSavedDeathPosition(player);
        if (position != null) {
            player.teleport(player.getServerWorld(), position.x, position.y, position.z, player.getYaw(), player.getPitch());
            player.sendMessage(Text.translatable("message.mob-talisman.skeleton_talisman.teleported"), false);
        }
        else {
            player.sendMessage(Text.translatable("message.mob-talisman.skeleton_talisman.teleported_error"), false);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.skeleton_talisman"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
