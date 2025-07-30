package net.fneifnox.mobtalisman.item.custom;

import io.wispforest.accessories.api.AccessoryItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.*;

import java.util.List;

import static net.fneifnox.mobtalisman.MobTalisman.CONFIG;

public class PiglinTalisman extends AccessoryItem {

    private static final Map<UUID, BlockPos> lastPlayerBlockPos = new HashMap<>();
    private static final Map<UUID, Block> lastPlayerBlock = new HashMap<>();

    public PiglinTalisman(Settings properties) {
        super(properties);
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if (!(reference.entity() instanceof ServerPlayerEntity player)) return;

        BlockPos currentBlockPos = player.getBlockPos().down();
        Block currentBlock = player.getWorld().getBlockState(currentBlockPos).getBlock();
        BlockPos lastBlockPos = lastPlayerBlockPos.get(player.getUuid());
        Block lastBlock = lastPlayerBlock.get(player.getUuid());

        if (!currentBlockPos.equals(lastBlockPos) &&
                currentBlock != Blocks.AIR && lastBlock != Blocks.AIR &&
                currentBlock != Blocks.WATER && lastBlock != Blocks.WATER &&
                currentBlock != Blocks.LAVA && lastBlock != Blocks.LAVA) {
            lastPlayerBlockPos.put(player.getUuid(), currentBlockPos);
            lastPlayerBlock.put(player.getUuid(), currentBlock);

            if (player.getRandom().nextDouble() < (CONFIG.piglinTalisman.turnsIntoGoldBlockChanceForPiglinTalisman() / 100)) {
                BlockPos blockPos = player.getBlockPos();
                BlockPos blockPos2 = blockPos.down();
                player.getServerWorld().setBlockState(blockPos2, Blocks.GOLD_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);
                player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.BLOCK_METAL_PLACE, player.getSoundCategory(), 1.0F, 1.0F);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        float chance = CONFIG.piglinTalisman.turnsIntoGoldBlockChanceForPiglinTalisman();
        if (chance == (int) chance) {
            tooltip.add(Text.translatable("tooltip.mob-talisman.piglin_talisman.prefix")
                    .append(Text.literal("" + (int) chance).formatted(Formatting.YELLOW))
                    .append(Text.translatable("tooltip.mob-talisman.piglin_talisman.suffix")));
        }
        else {
            tooltip.add(Text.translatable("tooltip.mob-talisman.piglin_talisman.prefix")
                    .append(Text.literal("" + chance).formatted(Formatting.YELLOW))
                    .append(Text.translatable("tooltip.mob-talisman.piglin_talisman.suffix")));
        }

        if (CONFIG.showDropchancesAsTooltip()) {
            float dropchance = CONFIG.dropchanceForPiglinTalisman();
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
