package net.verid.eschaton.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.verid.eschaton.Eschaton;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.verid.eschaton.block.custom.SculkJawBlock;

public class ModBlocks {

    public static Block LAMP_BLOCK;
    public static Block SOUL_LAMP_BLOCK;
    public static Block AMETHYST_LAMP_BLOCK;
    public static Block WARPED_SHROOM_LAMP_BLOCK;
    public static Block CRIMSON_SHROOM_LAMP_BLOCK;
    public static Block REDSTONE_POWERED_LAMP_BLOCK;
    public static Block FALLIUM_ORE_BLOCK;
    public static Block SCULK_JAW;

    public static void registerModBlocks() {
        Eschaton.LOGGER.info("Registering Mod Blocks for " + Eschaton.MOD_ID);

        SCULK_JAW = registerBlock("sculk_jaw",
                new SculkJawBlock(AbstractBlock.Settings.create()
                        .strength(2.0f)
                        .sounds(BlockSoundGroup.SCULK)
                        .luminance(state -> 3)
                        .nonOpaque()));


        FALLIUM_ORE_BLOCK = registerBlock("fallium_ore_block",
                new ExperienceDroppingBlock(UniformIntProvider.create(5,7),
                        AbstractBlock.Settings.create()
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.STONE)
                        .luminance(state -> 15)
                                .requiresTool()));


        LAMP_BLOCK = registerBlock("lamp_block",
                new Block(AbstractBlock.Settings.create()
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.LANTERN)
                        .luminance(state -> 15)));

        SOUL_LAMP_BLOCK = registerBlock("soul_lamp_block",
                new Block(AbstractBlock.Settings.create()
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.LANTERN)
                        .luminance(state -> 13)));

        AMETHYST_LAMP_BLOCK = registerBlock("amethyst_lamp_block",
                new Block(AbstractBlock.Settings.create()
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.LANTERN)
                        .luminance(state -> 14)));

        WARPED_SHROOM_LAMP_BLOCK = registerBlock("warped_shroom_lamp_block",
                new Block(AbstractBlock.Settings.create()
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.SHROOMLIGHT)
                        .luminance(state -> 15)));

        CRIMSON_SHROOM_LAMP_BLOCK = registerBlock("crimson_shroom_lamp_block",
                new Block(AbstractBlock.Settings.create()
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.SHROOMLIGHT)
                        .luminance(state -> 15)));

        REDSTONE_POWERED_LAMP_BLOCK = registerBlock("redstone_powered_lamp_block",
                new Block(AbstractBlock.Settings.create()
                        .requiresTool()
                        .strength(1.5f)
                        .sounds(BlockSoundGroup.LANTERN)
                        .luminance(state -> 15)) {

                    @Override
                    public boolean emitsRedstonePower(BlockState state) {
                        return true;
                    }

                    @Override
                    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
                        return 15;
                    }

                    @Override
                    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
                        return 15;
                    }

                    public boolean isOpaque(BlockState state) {
                        return true;
                    }

                    public boolean isFullCube(BlockState state, BlockView world, BlockPos pos) {
                        return true;
                    }
                });

        // Add all blocks to creative inventory
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(REDSTONE_POWERED_LAMP_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(LAMP_BLOCK);
            entries.add(SOUL_LAMP_BLOCK);
            entries.add(AMETHYST_LAMP_BLOCK);
            entries.add(WARPED_SHROOM_LAMP_BLOCK);
            entries.add(CRIMSON_SHROOM_LAMP_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(FALLIUM_ORE_BLOCK);
            entries.add(SCULK_JAW);
        });
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Eschaton.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Eschaton.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
}
