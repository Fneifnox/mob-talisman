package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

import java.util.*;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class SheepTalisman extends AccessoryItem {

    private static int ticks = 0;
    private static boolean gotActived = false;

    public SheepTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        ticks += 1;
        if (ticks >= player.getWorld().getTickManager().getTickRate()) {
            ticks = 0;

            if (player.getRandom().nextDouble() < (CONFIG.sheepTalisman.eatingGrassChanceForSheepTalisman() / 100) || gotActived) {
                BlockPos blockPos = player.getBlockPos();
                BlockPos blockPos2 = blockPos.down();
                gotActived = true;
                if (player.getServerWorld().getBlockState(blockPos2).isOf(Blocks.GRASS_BLOCK)) {
                    player.emitGameEvent(GameEvent.EAT);
                    player.getServerWorld().syncWorldEvent(WorldEvents.BLOCK_BROKEN, blockPos2, Block.getRawIdFromState(Blocks.GRASS_BLOCK.getDefaultState()));
                    player.getServerWorld().setBlockState(blockPos2, Blocks.DIRT.getDefaultState(), Block.NOTIFY_LISTENERS);
                    player.getHungerManager().setFoodLevel(20);
                    gotActived = false;

                    Identifier id = Identifier.of("mob-talisman", "custom/ruminant");
                    AdvancementEntry entry = Objects.requireNonNull(player.getServer()).getAdvancementLoader().get(id);
                    player.getAdvancementTracker().grantCriterion(entry, "ate_grass");
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.sheepTalisman.eatingGrassChanceForSheepTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.sheep_talisman.prefix")
                    .append(Text.literal("" + (int) chance).formatted(Formatting.YELLOW))
                    .append(Text.translatable("tooltip.mob-talisman.sheep_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.sheep_talisman.prefix")
                    .append(Text.literal("" + chance).formatted(Formatting.YELLOW))
                    .append(Text.translatable("tooltip.mob-talisman.sheep_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForSheepTalisman();
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

