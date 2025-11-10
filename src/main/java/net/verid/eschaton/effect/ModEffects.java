package net.verid.eschaton.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.verid.eschaton.effect.custom.SystolicStillnessEffect;

public class ModEffects {
    public static final StatusEffect SYSTOLIC_STILLNESS = new SystolicStillnessEffect();

    public static void register() {
        Registry.register(
                Registries.STATUS_EFFECT,
                Identifier.of("eschaton", "systolic_stillness"),
                SYSTOLIC_STILLNESS
        );

    }
}
