package me.mangregory.asr.items;

/**
 * Per-stack block cooldown state, stored in the item stack's NBT (1.20.1 has no data components).
 */
public record StackCooldown(int blockTicks, long cooldownUntil) {

    public static final String BLOCK_TICKS_KEY = "AsrBlockTicks";
    public static final String COOLDOWN_UNTIL_KEY = "AsrCooldownUntil";

    public static final StackCooldown EMPTY = new StackCooldown(0, 0);
}
