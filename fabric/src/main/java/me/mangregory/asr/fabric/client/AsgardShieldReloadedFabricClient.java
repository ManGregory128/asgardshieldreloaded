package me.mangregory.asr.fabric.client;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class AsgardShieldReloadedFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ResourceLocation blockingId = ResourceLocation.withDefaultNamespace("blocking");
        for (var supplier : AsgardShieldItems.ASGARD_SHIELDS) {
            Item shield = supplier.get();
            ItemProperties.register(shield, blockingId,
                    (stack, level, entity, seed) ->
                            entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
            );
        }
        for (var supplier : AsgardShieldItems.GIANT_SWORDS) {
            Item sword = supplier.get();
            ItemProperties.register(sword, blockingId,
                    (stack, level, entity, seed) ->
                            entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
            );
        }
    }
}
