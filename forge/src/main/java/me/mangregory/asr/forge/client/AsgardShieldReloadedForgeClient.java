package me.mangregory.asr.forge.client;

import me.mangregory.asr.items.init.AsgardShieldItems;
import me.mangregory.asr.util.handlers.BlockHudRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public final class AsgardShieldReloadedForgeClient {

    private AsgardShieldReloadedForgeClient() {
    }

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AsgardShieldReloadedForgeClient::onClientSetup);
        // Register HUD renderer for block time display (architectury event, works on Forge)
        BlockHudRenderer.register();
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        ResourceLocation blockingId = new ResourceLocation("blocking");
        event.enqueueWork(() -> {
            for (var supplier : AsgardShieldItems.ASGARD_SHIELDS) {
                registerBlocking(supplier.get(), blockingId);
            }
            for (var supplier : AsgardShieldItems.GIANT_SWORDS) {
                registerBlocking(supplier.get(), blockingId);
            }
        });
    }

    private static void registerBlocking(Item item, ResourceLocation id) {
        ItemProperties.register(item, id,
                (stack, level, entity, seed) ->
                        entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }
}
