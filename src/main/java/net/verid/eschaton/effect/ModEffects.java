package net.verid.eschaton.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.verid.eschaton.Eschaton;
import net.verid.eschaton.effect.custom.SystolicStillnessEffect;

public class ModEffects {

    // ✅ This is now a RegistryEntry, which 1.21.1 requires
    public static final RegistryEntry<StatusEffect> SYSTOLIC_STILLNESS =
            Registry.registerReference(
                    Registries.STATUS_EFFECT,
                    Identifier.of(Eschaton.MOD_ID, "systolic_stillness"),
                    new SystolicStillnessEffect()
            );

    public static void registerEffects() {
        Eschaton.LOGGER.info("Registering effects for " + Eschaton.MOD_ID);
    }
}
