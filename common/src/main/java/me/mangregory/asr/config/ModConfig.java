package me.mangregory.asr.config;

import com.teamresourceful.resourcefulconfig.api.annotations.*;

@Config(value = "asgard-shield-reloaded")
@ConfigInfo(
        titleTranslation = "config.asr.title",
        descriptionTranslation = "config.asr.description",
        icon = "swords"
)
public class ModConfig {
    @Comment("Giant Sword maximum block duration in ticks (min 20 ticks = 1 second, max 200 ticks = 10 seconds)")
    @ConfigOption.Range(min = 20, max = 200)
    @ConfigEntry(
            id = "giant_sword_block_duration",
            translation = "config.asr.giant_sword_block_duration"
    )
    public static long GIANT_SWORD_BLOCK_DURATION = 65;

    @Comment("Asgard Shield maximum block duration in ticks (min 20 ticks = 1 second, max 200 ticks = 10 seconds)")
    @ConfigOption.Range(min = 20, max = 200)
    @ConfigEntry(
            id = "asgard_shield_block_duration",
            translation = "config.asr.asgard_shield_block_duration"
    )
    public static long ASGARD_SHIELD_BLOCK_DURATION = 65;

    @Comment("Giant Sword Base Knockback (min: 0.0, max: 5.0)")
    @ConfigOption.Range(min = 0.0, max = 5.0)
    @ConfigEntry(
            id = "giant_sword_base_knockback",
            translation = "config.asr.giant_sword_base_knockback"
    )
    public static float GIANT_SWORD_BASE_KNOCKBACK = 1.0F;

    @Comment("Asgard Shield Base Knockback (min: 0.0, max: 5.0)")
    @ConfigOption.Range(min = 0.0, max = 5.0)
    @ConfigEntry(
            id = "asgard_shield_base_knockback",
            translation = "config.asr.asgard_shield_base_knockback"
    )
    public static float ASGARD_SHIELD_BASE_KNOCKBACK = 1.0F;

    @Comment("Giant Sword Base Item Damage (min: 1, max: 5)")
    @ConfigOption.Range(min = 1, max = 5)
    @ConfigEntry(
            id = "giant_sword_base_itemdamage",
            translation = "config.asr.giant_sword_base_itemdamage"
    )
    public static long GIANT_SWORD_BASE_ATTACKDMG = 1;

    @Comment("Asgard Shield Base Item Damage (min: 1, max: 5)")
    @ConfigOption.Range(min = 1, max = 5)
    @ConfigEntry(
            id = "asgard_shield_base_itemdamage",
            translation = "config.asr.asgard_shield_base_itemdamage"
    )
    public static long ASGARD_SHIELD_BASE_ATTACKDMG = 1;

    @Comment("Enable or disable Giant Sword equipping sound")
    @ConfigEntry(
            id = "enable_giant_sword_equip_sound",
            translation = "config.asr.enable_giant_sword_equip_sound"
    )
    public static boolean ENABLE_GIANT_SWORD_EQUIP_SOUND = true;

    @Comment("Enable or disable Asgard Shield equipping sound")
    @ConfigEntry(
            id = "enable_asgard_shield_equip_sound",
            translation = "config.asr.enable_asgard_shield_equip_sound"
    )
    public static boolean ENABLE_ASGARD_SHIELD_EQUIP_SOUND = true;
}
