package me.mangregory.asr.neoforge.client;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.util.handlers.BlockHudRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = AsgardShieldReloaded.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class AsgardShieldReloadedNeoForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Blocking item models are data-driven in 1.21.2+ (assets/<ns>/items/<id>.json
            // with a minecraft:using_item condition), so no ItemProperties registration is needed.

            // Register HUD renderer for block time display
            BlockHudRenderer.register();
        });
    }
}
