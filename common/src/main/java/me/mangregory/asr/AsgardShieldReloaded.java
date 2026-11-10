package me.mangregory.asr;

import com.teamresourceful.resourcefulconfig.common.config.Configurator;

import me.mangregory.asr.items.init.AsgardShieldItems;
import me.mangregory.asr.network.ConfigSyncPacket;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.util.handlers.EventHandler;
import me.mangregory.asr.util.handlers.LootEventHandler;
import me.mangregory.asr.util.handlers.PlayerEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AsgardShieldReloaded {

    public static final String MOD_ID = "asr";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Configurator CONFIGURATOR = new Configurator();

    public static void init() {
        LOGGER.info("Initializing Asgard Shield Reloaded");
        CONFIGURATOR.registerConfig(ModConfig.class);

        AsgardShieldItems.init();
        EventHandler.registerEvents();
        LootEventHandler.registerEvents();
        PlayerEventHandler.registerEvents();

        // Registers the S2C config sync channel (architectury 9: channel + client receiver in one call)
        ConfigSyncPacket.register();
    }
}
