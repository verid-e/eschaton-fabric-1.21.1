package net.verid.eschaton.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.verid.eschaton.Eschaton;
import net.verid.eschaton.block.ModBlocks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup ESCHATON_ADDITIONS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Eschaton.MOD_ID, "eschaton_additions"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.LAMP_BLOCK))
                    .displayName(Text.translatable("itemgroups.eschaton.eschaton_additions"))
                    .entries((displayContext, entries) -> {entries.add(ModBlocks.LAMP_BLOCK);
                       entries.add(ModBlocks.SOUL_LAMP_BLOCK);
                       entries.add(ModBlocks.AMETHYST_LAMP_BLOCK);
                       entries.add(ModBlocks.REDSTONE_POWERED_LAMP_BLOCK);
                       entries.add(ModBlocks.WARPED_SHROOM_LAMP_BLOCK);
                       entries.add(ModBlocks.CRIMSON_SHROOM_LAMP_BLOCK);

                       entries.add(ModItems.FALLIUM_KEY);
                        entries.add(ModItems.FALLIUM_PEBBLES);
                        entries.add(ModItems.FALLIUM_PLATING);

                    }).build());

    public static void registerItemGroups() {
        Eschaton.LOGGER.info("Registering Item Groups for " + Eschaton.MOD_ID);
    }
}
