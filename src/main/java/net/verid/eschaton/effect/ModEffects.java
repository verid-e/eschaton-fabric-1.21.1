package net.verid.eschaton.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.verid.eschaton.Eschaton;
import net.verid.eschaton.effect.custom.SystolicStillnessEffect;

public class ModEffects {
    public static final StatusEffect SYSTOLIC_STILLNESS =
            register("systolic_stillness", new SystolicStillnessEffect());

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, Identifier.of(Eschaton.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        Eschaton.LOGGER.info("Registering effects for " + Eschaton.MOD_ID);
    }
}
