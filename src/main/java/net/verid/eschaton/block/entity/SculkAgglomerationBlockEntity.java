package net.verid.eschaton.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.verid.eschaton.block.ModBlockEntities;
import net.verid.eschaton.block.ModBlocks;
import net.minecraft.block.Blocks;

import java.util.Random;

public class SculkAgglomerationBlockEntity extends BlockEntity {
    private int charge = 0;

    public SculkAgglomerationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCULK_AGGLOMERATION, pos, state);
    }

    public void addCharge(int amount) {
        this.charge += amount;
        markDirty();
    }

    public static void tick(World world, BlockPos pos, BlockState state, SculkAgglomerationBlockEntity be) {
        if (world.isClient) return;

        if (be.charge > 0 && world instanceof ServerWorld serverWorld) {
            be.charge--;

            Random random = serverWorld.random;

            // Choose a random nearby block within a 40×40 area
            int dx = random.nextInt(81) - 40;
            int dy = random.nextInt(11) - 5;  // Limit vertical conversion
            int dz = random.nextInt(81) - 40;
            BlockPos targetPos = pos.add(dx, dy, dz);

            // If the target block is natural (stone, dirt, etc.), convert it to sculk
            BlockState targetState = world.getBlockState(targetPos);
            if (targetState.isOf(Blocks.STONE) || targetState.isOf(Blocks.DIRT) || targetState.isOf(Blocks.DEEPSLATE)) {
                world.setBlockState(targetPos, Blocks.SCULK.getDefaultState());
            }

            // Optional: small particle or sound effect when it spreads
            if (random.nextInt(50) == 0) {
                world.playSound(null, pos, net.minecraft.sound.SoundEvents.BLOCK_SCULK_SPREAD,
                        net.minecraft.sound.SoundCategory.BLOCKS, 0.6f, 1.0f);
            }
        }
    }
}
