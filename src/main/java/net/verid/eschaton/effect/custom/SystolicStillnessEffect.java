package net.verid.eschaton.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SystolicStillnessEffect extends StatusEffect {

    public SystolicStillnessEffect() {
        super(StatusEffectCategory.HARMFUL, 0x550000);
    }

    public void onApplied(LivingEntity entity) {
        EntityAttributeInstance health = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (health != null) {
            health.setBaseValue(40.0);
        }
    }

    public void onRemoved(LivingEntity entity) {
        EntityAttributeInstance health = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (health != null) {
            health.setBaseValue(20.0);
        }
    }

    public void applyEffectTick(LivingEntity entity) {
        entity.setHealth(entity.getHealth());
    }

    public boolean canApplyEffectTick(int duration, int amplifier) {
        return true;
    }
}
