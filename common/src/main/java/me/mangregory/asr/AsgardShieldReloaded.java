package me.mangregory.asr;

import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import dev.architectury.networking.NetworkManager;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import me.mangregory.asr.items.init.AsgardShieldItems;
import me.mangregory.asr.network.BlockCooldownSyncPacket;
import me.mangregory.asr.network.ConfigSyncPacket;
import me.mangregory.asr.config.ClientBlockCooldownCache;
import me.mangregory.asr.config.ClientConfigCache;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.util.handlers.BlockHudRenderer;
import me.mangregory.asr.util.handlers.EventHandler;
import me.mangregory.asr.util.handlers.LootEventHandler;
import me.mangregory.asr.util.handlers.PlayerEventHandler;
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
        PlayerEventHandler.registerEvents();

        EnvExecutor.runInEnv(Env.CLIENT, () -> BlockHudRenderer::register);
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
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, BlockCooldownSyncPacket.TYPE, BlockCooldownSyncPacket.CODEC,
                (packet, context) -> {
                    context.queue(() -> {
                        ClientBlockCooldownCache.update(packet.key(), packet.cooldown());
                    });
                });
    }

    static void initNetServer() {
        NetworkManager.registerS2CPayloadType(ConfigSyncPacket.TYPE, ConfigSyncPacket.CODEC);
        NetworkManager.registerS2CPayloadType(BlockCooldownSyncPacket.TYPE, BlockCooldownSyncPacket.CODEC);
    }
}
