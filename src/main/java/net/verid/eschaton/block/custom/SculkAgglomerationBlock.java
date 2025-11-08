package net.verid.eschaton.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.verid.eschaton.block.ModBlockEntities;
import net.verid.eschaton.block.entity.SculkAgglomerationBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SculkAgglomerationBlock extends Block implements BlockEntityProvider {

    public SculkAgglomerationBlock(Settings settings) {
        super(settings);
    }

    // === Attach the block entity ===
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SculkAgglomerationBlockEntity(pos, state);
    }

    // === Handle right-click interaction ===
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult hit) {


        if (!world.isClient && world.getBlockEntity(pos) instanceof SculkAgglomerationBlockEntity agglomeration) {
            int xpGiven = player.totalExperience / 10; // give 10% of XP
            if (xpGiven > 0) {
                player.addExperience(-xpGiven);
                agglomeration.addCharge(xpGiven);
                player.sendMessage(
                        net.minecraft.text.Text.literal("§9Sculk absorbs " + xpGiven + " XP"), true);
            }
        }
        return ActionResult.SUCCESS;
    }
}
