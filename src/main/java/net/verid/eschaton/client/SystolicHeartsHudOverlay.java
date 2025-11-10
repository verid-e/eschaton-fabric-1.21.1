package net.verid.eschaton.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.effect.StatusEffect;
import net.verid.eschaton.effect.ModEffects;

public class SystolicHeartsHudOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        var player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (player.hasStatusEffect(RegistryEntry.of(ModEffects.SYSTOLIC_STILLNESS))) {
            context.fill(10, 10, 90, 30, 0x55FF0000);
            context.drawText(
                    MinecraftClient.getInstance().textRenderer,
                    "§4NO HEAL!",
                    14, 14, 0xFFFFFF, true
            );
        }
    }
}
