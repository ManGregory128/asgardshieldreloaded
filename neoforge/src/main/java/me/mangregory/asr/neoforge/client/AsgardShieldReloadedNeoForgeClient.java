package me.mangregory.asr.neoforge.client;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.items.init.AsgardShieldItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = AsgardShieldReloaded.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class AsgardShieldReloadedNeoForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
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
        });
    }
}
