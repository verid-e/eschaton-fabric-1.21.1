package net.verid.eschaton.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SculkJawBlock extends Block {
    public static final BooleanProperty BITING = BooleanProperty.of("biting");
    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 6.0, 14.0);

    public SculkJawBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BITING, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);

        if (!world.isClient && entity instanceof LivingEntity living && !state.get(BITING)) {
            // Switch to biting
            world.setBlockState(pos, state.with(BITING, true), Block.NOTIFY_ALL);

            // Play bite sound
            world.playSound(null, pos, SoundEvents.BLOCK_SCULK_SENSOR_CLICKING_STOP, SoundCategory.BLOCKS, 1.0F, 1.0F);

            // Get the DamageType safely
            RegistryKey<DamageType> sculkBiteKey = RegistryKey.of(
                    RegistryKeys.DAMAGE_TYPE,
                    Identifier.of("eschaton", "sculk_bite")
            );
            RegistryEntry<DamageType> sculkBiteType = world.getRegistryManager()
                    .get(RegistryKeys.DAMAGE_TYPE)
                    .getEntry(sculkBiteKey)
                    .orElseThrow();

            // Create a custom "magic-like" damage source that ignores armor
            // Use built-in magic damage source (bypasses armor like other magic damage)
            DamageSource sculkBite = world.getDamageSources().magic();
            living.damage(sculkBite, 4.0F);

            // Apply the damage
            living.damage(sculkBite, 4.0F);

            // Spawn sculk soul particles
            if (world instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 12; i++) {
                    double x = pos.getX() + 0.5 + (world.getRandom().nextDouble() - 0.5);
                    double y = pos.getY() + 0.3 + world.getRandom().nextDouble() * 0.5;
                    double z = pos.getZ() + 0.5 + (world.getRandom().nextDouble() - 0.5);
                    serverWorld.spawnParticles(ParticleTypes.SCULK_SOUL, x, y, z, 1, 0, 0, 0, 0.05);
                }
            }

            // Revert back to non-biting after 30 ticks (~1.5 seconds)
            world.scheduleBlockTick(pos, this, 30);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(BITING)) {
            world.setBlockState(pos, state.with(BITING, false), Block.NOTIFY_ALL);

            // Particle puff when reopening
            for (int i = 0; i < 8; i++) {
                double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5);
                double y = pos.getY() + 0.2 + random.nextDouble() * 0.3;
                double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5);
                world.spawnParticles(ParticleTypes.SCULK_CHARGE_POP, x, y, z, 1, 0, 0, 0, 0.04);
            }
        }
    }
}
