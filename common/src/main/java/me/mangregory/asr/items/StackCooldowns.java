package me.mangregory.asr.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class StackCooldowns {

    private StackCooldowns() {
    }

    public static boolean isOnCooldown(Player player, ItemStack stack) {
        StackCooldown cooldown = get(stack);
        return cooldown.cooldownUntil() > player.level().getGameTime();
    }

    public static long getRemainingCooldown(Player player, ItemStack stack) {
        StackCooldown cooldown = get(stack);
        return Math.max(0L, cooldown.cooldownUntil() - player.level().getGameTime());
    }

    public static void addCooldown(Player player, ItemStack stack, int duration) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(StackCooldown.BLOCK_TICKS_KEY, duration);
        tag.putLong(StackCooldown.COOLDOWN_UNTIL_KEY, player.level().getGameTime() + duration);
    }

    public static int getBlockTicks(Player player, ItemStack stack) {
        return player.isUsingItem() && player.getUseItem() == stack
                ? player.getTicksUsingItem()
                : 0;
    }

    public static float getFillPercentage(Player player, ItemStack stack, float partialTick, int maxBlockDuration) {
        if (player.isUsingItem() && player.getUseItem() == stack) {
            return Mth.clamp(1.0F - (player.getTicksUsingItem() + partialTick) / maxBlockDuration, 0.0F, 1.0F);
        }

        StackCooldown cooldown = get(stack);
        if (cooldown.cooldownUntil() <= player.level().getGameTime() || cooldown.blockTicks() <= 0)
            return 1.0F;

        double now = player.level().getGameTime() + partialTick;
        double remaining = (cooldown.cooldownUntil() - now) / cooldown.blockTicks();
        return (float) Mth.clamp(1.0D - remaining, 0.0D, 1.0D);
    }

    private static StackCooldown get(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains(StackCooldown.COOLDOWN_UNTIL_KEY))
            return StackCooldown.EMPTY;
        return new StackCooldown(tag.getInt(StackCooldown.BLOCK_TICKS_KEY), tag.getLong(StackCooldown.COOLDOWN_UNTIL_KEY));
    }
}
