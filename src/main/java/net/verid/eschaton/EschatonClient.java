package net.verid.eschaton;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.verid.eschaton.client.SystolicHeartsHudOverlay;

public class EschatonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new SystolicHeartsHudOverlay());
    }
}
