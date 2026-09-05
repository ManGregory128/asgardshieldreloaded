package me.mangregory.asr.util.handlers;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import me.mangregory.asr.network.ConfigSyncPacket;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class PlayerEventHandler {
    public static void registerEvents() {
        // Register attack entity event
        PlayerEvent.ATTACK_ENTITY.register((player, level, entity, hand, hitResult) -> {
            if (level instanceof ServerLevel serverLevel) {
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(player.getItemInHand(hand).getItem());
                if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "ender_giant_sword"))) {
                    EventHandler.particleFx(serverLevel, player, ParticleTypes.PORTAL);
                }
            }
            return EventResult.pass();
        });

        // Register player join event (for config sync)
        PlayerEvent.PLAYER_JOIN.register((player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                ConfigSyncPacket.sendToPlayer(serverPlayer);
            }
        }));

    }
}
