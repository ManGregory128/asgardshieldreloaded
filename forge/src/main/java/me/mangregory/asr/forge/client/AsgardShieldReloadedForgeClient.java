package me.mangregory.asr.forge.client;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.items.init.AsgardShieldItems;
import me.mangregory.asr.util.handlers.BlockHudRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public final class AsgardShieldReloadedForgeClient {

    private AsgardShieldReloadedForgeClient() {
    }

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AsgardShieldReloadedForgeClient::onClientSetup);
        // ResourcefulConfig 2.x has no ModMenu hook of its own; expose the config screen via Forge's
        // extension point, which ModMenu picks up on 1.20.1
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) ->
                        new AsrConfigScreen(parent, AsgardShieldReloaded.CONFIGURATOR.getConfig(ModConfig.class))));
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
