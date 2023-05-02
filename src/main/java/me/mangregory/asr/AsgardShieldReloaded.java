package me.mangregory.asr;

import me.mangregory.asr.init.ItemInit;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import static me.mangregory.asr.init.ItemInit.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AsgardShieldReloaded.MODID)
public class AsgardShieldReloaded {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "asr";
    public AsgardShieldReloaded() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ItemInit.ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(DIAMOND_GIANT_SWORD);
            event.accept(IRON_GIANT_SWORD);
            event.accept(WOODEN_GIANT_SWORD);
            event.accept(STONE_GIANT_SWORD);
            event.accept(GOLDEN_GIANT_SWORD);
            event.accept(ENDER_GIANT_SWORD);
            event.accept(WOODEN_SHIELD);
            event.accept(GILDED_WOODEN_SHIELD);
            event.accept(STONE_SHIELD);
            event.accept(GILDED_STONE_SHIELD);
            event.accept(IRON_SHIELD);
            event.accept(GILDED_IRON_SHIELD);
            event.accept(DIAMOND_SHIELD);
            event.accept(GILDED_DIAMOND_SHIELD);
            event.accept(NETHERQUARTZ_SHIELD);
            event.accept(GILDED_NETHERQUARTZ_SHIELD);
            event.accept(SKULL_SHIELD);
            event.accept(GILDED_SKULL_SHIELD);
            event.accept(ENDER_SHIELD);
            event.accept(GILDED_ENDER_SHIELD);
        }
    }
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        private static ResourceLocation BLOCKING_PROPERTY_RESLOC =
                new ResourceLocation(MODID, "blocking");
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemProperties.register(WOODEN_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(GILDED_WOODEN_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(STONE_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(GILDED_STONE_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(IRON_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(GILDED_IRON_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(DIAMOND_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(GILDED_DIAMOND_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(NETHERQUARTZ_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(GILDED_NETHERQUARTZ_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(SKULL_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(GILDED_SKULL_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
            event.enqueueWork(() -> {
                ItemProperties.register(ENDER_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
                ItemProperties.register(GILDED_ENDER_SHIELD.get(), BLOCKING_PROPERTY_RESLOC, ($itemStack, $level, $entity, $seed) -> {
                    return $entity != null && $entity.isUsingItem() && $entity.getUseItem() == $itemStack ? 1.0F : 0.0F;
                });
            });
        }
    }
}
