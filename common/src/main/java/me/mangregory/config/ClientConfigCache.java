package me.mangregory.config;

public class ClientConfigCache {
    public static long giantSwordBlockDuration = ModConfig.GIANT_SWORD_BLOCK_DURATION;
    public static long asgardShieldBlockDuration = ModConfig.ASGARD_SHIELD_BLOCK_DURATION;

    public static void updateFromPacket(long giantSwordDuration, long asgardShieldDuration) {
        giantSwordBlockDuration = giantSwordDuration;
        asgardShieldBlockDuration = asgardShieldDuration;
    }
}
