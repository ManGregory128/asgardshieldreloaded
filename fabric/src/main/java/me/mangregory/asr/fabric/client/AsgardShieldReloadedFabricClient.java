package me.mangregory.asr.fabric.client;

import me.mangregory.asr.util.handlers.BlockHudRenderer;
import net.fabricmc.api.ClientModInitializer;

public final class AsgardShieldReloadedFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Blocking item models are data-driven in 1.21.2+ (assets/<ns>/items/<id>.json
        // with a minecraft:using_item condition), so no ItemProperties registration is needed.

        // Register HUD renderer for block time display
        BlockHudRenderer.register();
    }
}
