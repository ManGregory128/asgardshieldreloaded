package me.mangregory.asr.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record StackCooldown(int blockTicks, long cooldownUntil) {
    public static final Codec<StackCooldown> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("block_ticks").forGetter(StackCooldown::blockTicks),
            Codec.LONG.fieldOf("cooldown_until").forGetter(StackCooldown::cooldownUntil)
    ).apply(instance, StackCooldown::new));

    public static final StreamCodec<net.minecraft.network.FriendlyByteBuf, StackCooldown> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, StackCooldown::blockTicks,
                    ByteBufCodecs.VAR_LONG, StackCooldown::cooldownUntil,
                    StackCooldown::new
            );

    public static final StackCooldown EMPTY = new StackCooldown(0, 0);
}
