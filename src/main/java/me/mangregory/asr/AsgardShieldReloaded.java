package me.mangregory.asr;

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
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AsgardShieldReloaded.MODID)
public class AsgardShieldReloaded {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "asr";

    public AsgardShieldReloaded(IEventBus modEventBus, ModContainer modContainer) {

        // Register the Deferred Register to the mod event bus so items get registered
        ItemInit.ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(new EventHandler());
        NeoForge.EVENT_BUS.register(FirstPersonRenderingHandler.class);
        //      NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::buildContents);
    }

    private void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ItemInit.DIAMOND_GIANT_SWORD.get());
            event.accept(ItemInit.IRON_GIANT_SWORD.get());
            event.accept(ItemInit.WOODEN_GIANT_SWORD.get());
            event.accept(ItemInit.STONE_GIANT_SWORD.get());
            event.accept(ItemInit.GOLDEN_GIANT_SWORD.get());
            event.accept(ItemInit.ENDER_GIANT_SWORD.get());
            event.accept(ItemInit.WOODEN_SHIELD.get());
            event.accept(ItemInit.GILDED_WOODEN_SHIELD.get());
            event.accept(ItemInit.STONE_SHIELD.get());
            event.accept(ItemInit.GILDED_STONE_SHIELD.get());
            event.accept(ItemInit.IRON_SHIELD.get());
            event.accept(ItemInit.GILDED_IRON_SHIELD.get());
            event.accept(ItemInit.DIAMOND_SHIELD.get());
            event.accept(ItemInit.GILDED_DIAMOND_SHIELD.get());
            event.accept(ItemInit.NETHERQUARTZ_SHIELD.get());
            event.accept(ItemInit.GILDED_NETHERQUARTZ_SHIELD.get());
            event.accept(ItemInit.SKULL_SHIELD.get());
            event.accept(ItemInit.GILDED_SKULL_SHIELD.get());
            event.accept(ItemInit.ENDER_SHIELD.get());
            event.accept(ItemInit.GILDED_ENDER_SHIELD.get());
        }
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        private static final ResourceLocation BLOCKING_PROPERTY_RESLOC = new ResourceLocation(MODID, "blocking");
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                ItemProperties.register(ItemInit.WOODEN_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.GILDED_WOODEN_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.STONE_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.GILDED_STONE_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.IRON_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.GILDED_IRON_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.DIAMOND_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.GILDED_DIAMOND_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.NETHERQUARTZ_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.GILDED_NETHERQUARTZ_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.SKULL_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.GILDED_SKULL_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.ENDER_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
                ItemProperties.register(ItemInit.GILDED_ENDER_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) ->
                        $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F);
            });
        }
    }
}
