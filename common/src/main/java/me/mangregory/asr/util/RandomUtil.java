package me.mangregory.asr.util;

import net.minecraft.util.RandomSource;

public class RandomUtil {
    /**
     * Returns true with a certain chance
     *
     * @param chance The chance to return true
     * @param random The random source to use
     * @return true with a certain chance or false
     */
    /**
     * Returns true with a certain chance, using the given random source (injectable for tests).
     */
    public static boolean chance(double chance, RandomSource random) {
        return random.nextDouble() <= chance;
    }
}
