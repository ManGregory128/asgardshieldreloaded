package me.mangregory;

import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import dev.architectury.networking.NetworkManager;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import me.mangregory.items.init.AsgardShieldItems;
import me.mangregory.network.ConfigSyncPacket;
import me.mangregory.config.ClientConfigCache;
import me.mangregory.config.ModConfig;
import me.mangregory.util.handlers.EventHandler;
import me.mangregory.util.handlers.LootEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AsgardShieldReloaded {
    public static final String MOD_ID = "asr";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Configurator CONFIGURATOR = new Configurator(MOD_ID);

    public static void init() {
        LOGGER.info("Initializing Asgard Shield Reloaded");
        CONFIGURATOR.register(ModConfig.class);
        AsgardShieldItems.init();
        EventHandler.registerEvents();
        LootEventHandler.registerEvents();
        EnvExecutor.runInEnv(Env.CLIENT, () -> AsgardShieldReloaded::initNetClient);
        EnvExecutor.runInEnv(Env.SERVER, () -> AsgardShieldReloaded::initNetServer);
    }
    static void initNetClient() {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, ConfigSyncPacket.TYPE, ConfigSyncPacket.CODEC,
                (packet, context) -> {
                    context.queue(() -> {
                        ClientConfigCache.updateFromPacket(packet.giantSwordBlockDuration(), packet.asgardShieldBlockDuration());
                    });
                });
    }

    static void initNetServer() {
        NetworkManager.registerS2CPayloadType(ConfigSyncPacket.TYPE, ConfigSyncPacket.CODEC);
    }
}
