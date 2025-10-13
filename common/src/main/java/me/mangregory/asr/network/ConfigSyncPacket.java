package me.mangregory.asr.network;

import dev.architectury.networking.NetworkManager;
import me.mangregory.asr.config.ModConfig;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public record ConfigSyncPacket(long giantSwordBlockDuration, long asgardShieldBlockDuration) implements CustomPacketPayload {
    public static final Type<ConfigSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("asgardshieldreloaded", "config_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ConfigSyncPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG, ConfigSyncPacket::giantSwordBlockDuration,
            ByteBufCodecs.VAR_LONG, ConfigSyncPacket::asgardShieldBlockDuration,
            ConfigSyncPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void sendToPlayer(ServerPlayer player) {
        ConfigSyncPacket packet = new ConfigSyncPacket(
                ModConfig.GIANT_SWORD_BLOCK_DURATION,
                ModConfig.ASGARD_SHIELD_BLOCK_DURATION
        );
        NetworkManager.sendToPlayer(player, packet);
    }
}
