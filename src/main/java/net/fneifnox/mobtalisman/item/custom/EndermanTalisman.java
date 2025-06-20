package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.BooleanComponent;
import net.fneifnox.mobtalisman.component.Vec3dComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.List;

import static net.fneifnox.mobtalisman.component.MyComponents.ENDERMAN_POSITION;
import static net.fneifnox.mobtalisman.component.MyComponents.HAS_SPIDER_TALISMAN;

public class EndermanTalisman extends AccessoryItem {

    public EndermanTalisman(Settings properties) {
        super(properties);
    }

    private static Vec3d savedPosition = null;

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        if (ENDERMAN_POSITION.get(player).getValue() == Vec3d.ZERO) {
            Vec3d position = player.getPos();
            savedPosition = position;
            setVec3d(player, position);
            player.sendMessage(Text.translatable("message.mob-talisman.enderman_talisman.position.saved"), false);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        teleportToSavedPosition(player);
    }

    public static Vec3d useVec3d(Entity provider) {
        return ENDERMAN_POSITION.get(provider).getValue();
    }

    public static void setVec3d(Entity provider, Vec3d position) {
        Vec3dComponent component = ENDERMAN_POSITION.get(provider);
        component.setValue(position);
    }

    public static Vec3d getSavedPosition(ServerPlayerEntity player) {
        return savedPosition;
    }

    public static void teleportToSavedPosition(ServerPlayerEntity player) {
        Vec3d position = EndermanTalisman.useVec3d(player);
        if (position != null) {
            player.teleport(player.getServerWorld(), position.x, position.y, position.z, player.getYaw(), player.getPitch());
            setVec3d(player, Vec3d.ZERO);
            player.sendMessage(Text.translatable("message.mob-talisman.enderman_talisman.teleported"), false);
        }
        else {
            player.sendMessage(Text.translatable("message.mob-talisman.enderman_talisman.teleported_error"), false);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.enderman_talisman"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}