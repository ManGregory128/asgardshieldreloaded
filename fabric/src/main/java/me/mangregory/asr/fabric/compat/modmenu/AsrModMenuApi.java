package me.mangregory.asr.fabric.compat.modmenu;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.config.ModConfig;

/**
 * ResourcefulConfig 2.x has no built-in ModMenu entrypoint (unlike 3.x), so register ours.
 * Only loaded when ModMenu is installed.
 */
public class AsrModMenuApi implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(parent, null, AsgardShieldReloaded.CONFIGURATOR.getConfig(ModConfig.class));
    }
}
