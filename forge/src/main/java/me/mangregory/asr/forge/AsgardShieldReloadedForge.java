package me.mangregory.asr.forge;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.forge.client.AsgardShieldReloadedForgeClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(AsgardShieldReloaded.MOD_ID)
public class AsgardShieldReloadedForge {

    public AsgardShieldReloadedForge() {
        AsgardShieldReloaded.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> AsgardShieldReloadedForgeClient::init);
    }
}
