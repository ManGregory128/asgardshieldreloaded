package me.mangregory.asr.util.handlers;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import me.mangregory.asr.network.ConfigSyncPacket;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PlayerEventHandler {
    public static void registerEvents() {
        // Register attack entity event
        PlayerEvent.ATTACK_ENTITY.register((player, level, entity, hand, hitResult) -> {
            if (level instanceof ServerLevel serverLevel) {
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(player.getItemInHand(player.swingingArm).getItem());
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

        TickEvent.PLAYER_POST.register((player) -> {
            if (!player.level().isClientSide()) {
                for (ItemStack stack : player.getInventory().items) {
                    tickCooldownStack(player, stack);
                }
                tickCooldownStack(player, player.getOffhandItem());
            }
        });
    }

    private static void tickCooldownStack(Player player, ItemStack stack) {
        if (stack.isEmpty()) {
            return;
        }

        Item item = stack.getItem();
        if (item instanceof GiantSwordItem) {
            GiantSwordItem.tickStackCooldown(player, stack);
        } else if (item instanceof AsgardShieldItem) {
            AsgardShieldItem.tickStackCooldown(player, stack);
        }
    }
}
