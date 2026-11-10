package me.mangregory.asr.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Comment;
import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.annotations.FloatRange;
import com.teamresourceful.resourcefulconfig.common.annotations.LongRange;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;

@Config("asgard-shield-reloaded")
public class ModConfig {

    @Comment("Giant Sword maximum block duration in ticks (min 20 ticks = 1 second, max 200 ticks = 10 seconds)")
    @LongRange(min = 20, max = 200)
    @ConfigEntry(id = "giant_sword_block_duration", type = EntryType.LONG, translation = "config.asr.giant_sword_block_duration")
    public static long GIANT_SWORD_BLOCK_DURATION = 65;

    @Comment("Asgard Shield maximum block duration in ticks (min 20 ticks = 1 second, max 200 ticks = 10 seconds)")
    @LongRange(min = 20, max = 200)
    @ConfigEntry(id = "asgard_shield_block_duration", type = EntryType.LONG, translation = "config.asr.asgard_shield_block_duration")
    public static long ASGARD_SHIELD_BLOCK_DURATION = 65;

    @Comment("Giant Sword Base Knockback (min: 0.0, max: 5.0)")
    @FloatRange(min = 0.0F, max = 5.0F)
    @ConfigEntry(id = "giant_sword_base_knockback", type = EntryType.FLOAT, translation = "config.asr.giant_sword_base_knockback")
    public static float GIANT_SWORD_BASE_KNOCKBACK = 1.0F;

    @Comment("Asgard Shield Base Knockback (min: 0.0, max: 5.0)")
    @FloatRange(min = 0.0F, max = 5.0F)
    @ConfigEntry(id = "asgard_shield_base_knockback", type = EntryType.FLOAT, translation = "config.asr.asgard_shield_base_knockback")
    public static float ASGARD_SHIELD_BASE_KNOCKBACK = 1.0F;

    @Comment("Giant Sword Base Item Damage (min: 1, max: 5)")
    @LongRange(min = 1, max = 5)
    @ConfigEntry(id = "giant_sword_base_itemdamage", type = EntryType.LONG, translation = "config.asr.giant_sword_base_itemdamage")
    public static long GIANT_SWORD_BASE_ATTACKDMG = 1;

    @Comment("Asgard Shield Base Item Damage (min: 1, max: 5)")
    @LongRange(min = 1, max = 5)
    @ConfigEntry(id = "asgard_shield_base_itemdamage", type = EntryType.LONG, translation = "config.asr.asgard_shield_base_itemdamage")
    public static long ASGARD_SHIELD_BASE_ATTACKDMG = 1;

    @Comment("Enable or disable Giant Sword equipping sound")
    @ConfigEntry(id = "enable_giant_sword_equip_sound", type = EntryType.BOOLEAN, translation = "config.asr.enable_giant_sword_equip_sound")
    public static boolean ENABLE_GIANT_SWORD_EQUIP_SOUND = true;

    @Comment("Enable or disable Asgard Shield equipping sound")
    @ConfigEntry(id = "enable_asgard_shield_equip_sound", type = EntryType.BOOLEAN, translation = "config.asr.enable_asgard_shield_equip_sound")
    public static boolean ENABLE_ASGARD_SHIELD_EQUIP_SOUND = true;
}
