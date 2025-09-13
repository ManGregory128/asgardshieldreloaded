package me.mangregory;

import me.mangregory.items.init.AsgardShieldItems;
import me.mangregory.util.handlers.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AsgardShieldReloaded {
    public static final String MOD_ID = "asr";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOGGER.info("Initializing Asgard Shield Reloaded");
        AsgardShieldItems.init();
        EventHandler.registerEvents();
    }

    public static void log(String message) {
        LOGGER.info(message);
    }
}
