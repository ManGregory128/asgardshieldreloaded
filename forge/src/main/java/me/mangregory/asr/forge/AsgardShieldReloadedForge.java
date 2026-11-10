package me.mangregory.asr.forge;

import dev.architectury.platform.forge.EventBuses;
import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.forge.client.AsgardShieldReloadedForgeClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AsgardShieldReloaded.MOD_ID)
public class AsgardShieldReloadedForge {

    public AsgardShieldReloadedForge() {
        // Architectury on Forge needs the mod event bus registered before DeferredRegister.register()
        EventBuses.registerModEventBus(AsgardShieldReloaded.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        AsgardShieldReloaded.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> AsgardShieldReloadedForgeClient::init);
    }
}
