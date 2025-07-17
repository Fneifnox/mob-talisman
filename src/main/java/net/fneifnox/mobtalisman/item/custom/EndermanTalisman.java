package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fneifnox.mobtalisman.component.cca.BooleanComponent;
import net.fneifnox.mobtalisman.component.cca.Vec3dComponent;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Objects;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;
import static net.fneifnox.mobtalisman.component.cca.MyComponents.*;

public class EndermanTalisman extends AccessoryItem {

    public EndermanTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;
        if (!ENDERMAN_EQUIPPED.get(player).getValue()) {
            Vec3d position = player.getPos();
            setVec3d(player, position);
            setBooleanTrue(player);
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

    public static void setBooleanFalse(Entity provider) {
        BooleanComponent component = ENDERMAN_EQUIPPED.get(provider);
        component.setValue(false);
    }

    public static void setBooleanTrue(Entity provider) {
        BooleanComponent component = ENDERMAN_EQUIPPED.get(provider);
        component.setValue(true);
    }

    public static void teleportToSavedPosition(ServerPlayerEntity player) {
        Vec3d position = EndermanTalisman.useVec3d(player);
        if (position != null) {
            Identifier id = Identifier.of("mob-talisman", "custom/home_sweet_home");
            AdvancementEntry entry = Objects.requireNonNull(player.getServer()).getAdvancementLoader().get(id);
            player.getAdvancementTracker().grantCriterion(entry, "teleported");

            player.teleport(player.getServerWorld(), position.x, position.y, position.z, player.getYaw(), player.getPitch());
            setBooleanFalse(player);
            player.sendMessage(Text.translatable("message.mob-talisman.enderman_talisman.teleported"), false);
        }
        else {
            player.sendMessage(Text.translatable("message.mob-talisman.enderman_talisman.teleported_error"), false);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mob-talisman.enderman_talisman"));

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForEndermanTalisman();
            if (dropchance == (int) dropchance) {
                tooltip.add(Text.translatable("tooltip.mob-talisman.dropchance")
                        .append(Text.literal("" + (int) dropchance + "%").formatted(Formatting.GRAY)));
            }
            else {
                tooltip.add(Text.translatable("tooltip.mob-talisman.dropchance")
                        .append(Text.literal("" + dropchance + "%").formatted(Formatting.GRAY)));
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}