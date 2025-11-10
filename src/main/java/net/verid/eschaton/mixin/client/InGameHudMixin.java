package net.verid.eschaton.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.verid.eschaton.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    private static final Identifier SYSTOLIC_HEARTS =
            Identifier.of("eschaton", "textures/gui/systolic_hearts.png");

    @Inject(method = "renderHealthBar", at = @At("HEAD"))
    private void renderCustomHearts(
            DrawContext context,
            PlayerEntity player,
            int x, int y,
            int lines, int regeneratingHeartIndex,
            float maxHealth, int lastHealth, int health,
            int absorption, boolean blinking,
            CallbackInfo ci
    ) {
        if (player == null) return;

        // check the effect directly
        if (player.hasStatusEffect(ModEffects.SYSTOLIC_STILLNESS)) {
            RenderSystem.setShaderTexture(0, SYSTOLIC_HEARTS);
        }
    }
}
