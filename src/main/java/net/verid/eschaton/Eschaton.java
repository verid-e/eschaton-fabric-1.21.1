package net.verid.eschaton;

import net.fabricmc.api.ModInitializer;

import net.verid.eschaton.block.ModBlocks;
import net.verid.eschaton.effect.ModEffects;
import net.verid.eschaton.item.ModItemGroup;
import net.verid.eschaton.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Eschaton implements ModInitializer {
	public static final String MOD_ID = "eschaton";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroup.registerItemGroups();
        ModEffects.register();
    }
}