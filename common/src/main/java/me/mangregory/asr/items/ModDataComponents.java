package me.mangregory.asr.items;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.mangregory.asr.AsgardShieldReloaded;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

public final class ModDataComponents {
    private static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(
            AsgardShieldReloaded.MOD_ID, Registries.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<StackCooldown>> STACK_COOLDOWN =
            COMPONENTS.register(ResourceLocation.fromNamespaceAndPath(AsgardShieldReloaded.MOD_ID, "stack_cooldown"),
                    () -> DataComponentType.<StackCooldown>builder()
                            .persistent(StackCooldown.CODEC)
                            .networkSynchronized(StackCooldown.STREAM_CODEC)
                            .build());

    private ModDataComponents() {
    }

    public static void init() {
        COMPONENTS.register();
    }
}
