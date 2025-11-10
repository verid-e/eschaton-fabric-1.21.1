package net.verid.eschaton.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * SystolicStillnessEffect:
 * - Prevents natural regeneration while active.
 * - Prevents instant-heal spikes by restoring health to the previous tick's value if needed.
 *
 * Uses a WeakHashMap keyed by the entity UUID to store the previous health value.
 */
public class SystolicStillnessEffect extends StatusEffect {

    // transient per-entity previous-health store. WeakHashMap lets entries be GC'd when entities are gone.
    private static final Map<UUID, Float> PREV_HEALTH = new WeakHashMap<>();

    public SystolicStillnessEffect() {
        super(StatusEffectCategory.HARMFUL, 0x2A004B); // dark purple color
    }

    public static final Identifier SYSTOLIC_HEARTS =
            Identifier.of("eschaton", "textures/gui/systolic_hearts.png");


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Run every tick while effect active
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Remove natural regeneration effect if present
        if (entity.hasStatusEffect(StatusEffects.REGENERATION)) {
            entity.removeStatusEffect(StatusEffects.REGENERATION);
        }

        // Current and max health
        float current = entity.getHealth();
        float max = entity.getMaxHealth();

        // Lookup previous health (default to current if not present)
        UUID id = entity.getUuid();
        float last = PREV_HEALTH.getOrDefault(id, current);

        // If the entity was healed this tick above the last recorded health, undo that healing
        if (current > last) {
            entity.setHealth(MathHelper.clamp(last, 0f, max));
            current = entity.getHealth(); // update current after clamping
        }

        // Store current health for next tick
        PREV_HEALTH.put(id, current);

        // Delay natural regen timer so regen won't restart quickly
        try {
            // timeUntilRegen is a field on LivingEntity in these mappings; if not accessible in your mappings,
            // you can skip this or use other mechanisms to delay regen.
            entity.timeUntilRegen = 100;
        } catch (Throwable ignored) {
            // If field access differs in your mappings, ignore rather than crash.
        }

        return true;
    }
}
