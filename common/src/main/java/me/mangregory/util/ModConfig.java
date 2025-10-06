package me.mangregory.util;

import com.teamresourceful.resourcefulconfig.api.annotations.*;

@Config(value = "asgard-shield-reloaded")
@ConfigInfo(
        titleTranslation = "config.asr.title",
        icon = "swords"
)
public class ModConfig {
    @Comment("Giant Sword maximum block duration in ticks (20 ticks = 1 second)")
    @ConfigOption.Range(min = 20, max = 200)
    @ConfigEntry(
            id = "giant_sword_block_duration",
            translation = "config.asr.giant_sword_block_duration"
    )
    public static long GIANT_SWORD_BLOCK_DURATION = 65;

    @Comment("Asgard Shields maximum block duration in ticks (20 ticks = 1 second)")
    @ConfigOption.Range(min = 20, max = 200)
    @ConfigEntry(
            id = "asgard_shield_block_duration",
            translation = "config.asr.asgard_shield_block_duration"
    )
    public static long ASGARD_SHIELD_BLOCK_DURATION = 65;

    @Comment("Giant Sword Base Knockback")
    @ConfigOption.Range(min = 0.0, max = 5.0)
    @ConfigEntry(
            id = "giant_sword_base_knockback",
            translation = "config.asr.giant_sword_base_knockback"
    )
    public static float GIANT_SWORD_BASE_KNOCKBACK = 1.0F;

    @Comment("Asgard Shield Base Knockback")
    @ConfigOption.Range(min = 0.0, max = 5.0)
    @ConfigEntry(
            id = "asgard_shield_base_knockback",
            translation = "config.asr.asgard_shield_base_knockback"
    )
    public static float ASGARD_SHIELD_BASE_KNOCKBACK = 1.0F;
}
