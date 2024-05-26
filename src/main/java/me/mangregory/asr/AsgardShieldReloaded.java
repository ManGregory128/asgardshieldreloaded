package me.mangregory.asr;


import com.google.common.eventbus.Subscribe;
import me.mangregory.asr.init.ItemInit;
import me.mangregory.asr.util.handlers.EventHandler;
import me.mangregory.asr.util.handlers.FirstPersonRenderingHandler;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;


import static me.mangregory.asr.init.ItemInit.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AsgardShieldReloaded.MODID)
public class AsgardShieldReloaded {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "asr";

    public AsgardShieldReloaded(IEventBus modEventBus, ModContainer modContainer) {

        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(EventHandler.class);
        NeoForge.EVENT_BUS.register(FirstPersonRenderingHandler.class);
        modEventBus.addListener(this::buildContents);
    }

    @SubscribeEvent
    private void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(DIAMOND_GIANT_SWORD.get());
            event.accept(IRON_GIANT_SWORD.get());
            event.accept(WOODEN_GIANT_SWORD.get());
            event.accept(STONE_GIANT_SWORD.get());
            event.accept(GOLDEN_GIANT_SWORD.get());
            event.accept(ENDER_GIANT_SWORD.get());
            event.accept(WOODEN_SHIELD.get());
            event.accept(GILDED_WOODEN_SHIELD.get());
            event.accept(STONE_SHIELD.get());
            event.accept(GILDED_STONE_SHIELD.get());
            event.accept(IRON_SHIELD.get());
            event.accept(GILDED_IRON_SHIELD.get());
            event.accept(DIAMOND_SHIELD.get());
            event.accept(GILDED_DIAMOND_SHIELD.get());
            event.accept(NETHERQUARTZ_SHIELD.get());
            event.accept(GILDED_NETHERQUARTZ_SHIELD.get());
            event.accept(SKULL_SHIELD.get());
            event.accept(GILDED_SKULL_SHIELD.get());
            event.accept(ENDER_SHIELD.get());
            event.accept(GILDED_ENDER_SHIELD.get());
        }
    }

    public static class ClientModEvents {
        private static final ResourceLocation BLOCKING_PROPERTY_RESLOC = new ResourceLocation(MODID, "blocking");

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemProperties.register(WOODEN_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(GILDED_WOODEN_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(STONE_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(GILDED_STONE_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(IRON_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(GILDED_IRON_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(DIAMOND_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(GILDED_DIAMOND_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(NETHERQUARTZ_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(GILDED_NETHERQUARTZ_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(SKULL_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(GILDED_SKULL_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ENDER_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(GILDED_ENDER_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
            });
        }
    }
}
