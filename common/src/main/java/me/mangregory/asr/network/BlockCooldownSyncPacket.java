package me.mangregory.asr.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

public record BlockCooldownSyncPacket(String key, int cooldown) implements CustomPacketPayload {

    public static final Type<BlockCooldownSyncPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath("asgardshieldreloaded", "block_cooldown_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, BlockCooldownSyncPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, BlockCooldownSyncPacket::key,
            ByteBufCodecs.VAR_INT, BlockCooldownSyncPacket::cooldown,
            BlockCooldownSyncPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void sendToPlayer(ServerPlayer player, String key, int cooldown) {
        try {
            NetworkManager.sendToPlayer(player, new BlockCooldownSyncPacket(key, cooldown));
        } catch (UnsupportedOperationException e) {
            // Connectionless players (e.g. gametest mock players) cannot receive custom payloads
        }
    }
}
