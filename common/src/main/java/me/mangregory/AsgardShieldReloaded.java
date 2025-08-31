package me.mangregory;

import me.mangregory.items.AsgardShieldItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AsgardShieldReloaded {
    public static final String MOD_ID = "asr";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOGGER.info("Initializing Asgard Shield Reloaded");
        AsgardShieldItems.init();
    }
}
