package net.verid.eschaton.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.verid.eschaton.Eschaton;

public class ModItems {

    public static Item FALLIUM_KEY;
    public static Item FALLIUM_PEBBLES;
    public static Item FALLIUM_PLATING;

    public static void registerModItems() {
        Eschaton.LOGGER.info("Registering Mod Items for " + Eschaton.MOD_ID);

        FALLIUM_KEY = registerItem("fallium_key", new Item(new Item.Settings()));
        FALLIUM_PEBBLES = registerItem("fallium_pebbles", new Item(new Item.Settings()));
        FALLIUM_PLATING = registerItem("fallium_plating", new Item(new Item.Settings()));

        // to add both items to the creative inventory (small reminder btw
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(FALLIUM_KEY);
            entries.add(FALLIUM_PEBBLES);
            entries.add(FALLIUM_PLATING);
        });
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Eschaton.MOD_ID, name), item);
    }
}
