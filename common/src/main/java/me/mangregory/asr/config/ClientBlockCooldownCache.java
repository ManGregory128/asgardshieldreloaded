package me.mangregory.asr.config;

import java.util.HashMap;
import java.util.Map;

public class ClientBlockCooldownCache {
    private static final Map<String, Integer> COOLDOWNS = new HashMap<>();

    public static void update(String key, int cooldown) {
        if (cooldown <= 0) COOLDOWNS.remove(key);
        else COOLDOWNS.put(key, cooldown);
    }

    public static int get(String key) {
        return COOLDOWNS.getOrDefault(key, 0);
    }
}
