package net.verid.eschaton.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.verid.eschaton.Eschaton;
import net.verid.eschaton.block.custom.SculkAgglomerationBlockEntity;

public class ModBlockEntities {
    public static BlockEntityType<SculkAgglomerationBlockEntity> SCULK_AGGLOMERATION;

    public static void registerAll() {
        SCULK_AGGLOMERATION = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(Eschaton.MOD_ID, "sculk_agglomeration"),
                FabricBlockEntityTypeBuilder.create(SculkAgglomerationBlockEntity::new, ModBlocks.SCULK_AGGLOMERATION).build(null)
        );
    }
}
