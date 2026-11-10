package me.mangregory.asr.network;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;
import me.mangregory.asr.config.ClientConfigCache;
import me.mangregory.asr.config.ModConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class ConfigSyncPacket extends BaseS2CMessage {

    private static final SimpleNetworkManager CHANNEL = SimpleNetworkManager.create("asgardshieldreloaded");
    public static final MessageType TYPE = CHANNEL.registerS2C("config_sync", ConfigSyncPacket::new);

    private final long giantSwordBlockDuration;
    private final long asgardShieldBlockDuration;

    public ConfigSyncPacket(long giantSwordBlockDuration, long asgardShieldBlockDuration) {
        this.giantSwordBlockDuration = giantSwordBlockDuration;
        this.asgardShieldBlockDuration = asgardShieldBlockDuration;
    }

    public ConfigSyncPacket(FriendlyByteBuf buf) {
        this(buf.readVarLong(), buf.readVarLong());
    }

    /**
     * Forces class initialization, which registers the S2C channel and its client receiver.
     */
    public static void register() {
        MessageType type = TYPE;
        if (type == null)
            throw new IllegalStateException("Config sync channel not registered");
    }

    @Override
    public MessageType getType() {
        return TYPE;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeVarLong(giantSwordBlockDuration);
        buf.writeVarLong(asgardShieldBlockDuration);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> ClientConfigCache.updateFromPacket(giantSwordBlockDuration, asgardShieldBlockDuration));
    }

    public static void sendToPlayer(ServerPlayer player) {
        new ConfigSyncPacket(
                ModConfig.GIANT_SWORD_BLOCK_DURATION,
                ModConfig.ASGARD_SHIELD_BLOCK_DURATION
        ).sendTo(player);
    }
}
