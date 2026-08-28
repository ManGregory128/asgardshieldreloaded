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

        // Register player tick event (for cooldown management)
        TickEvent.PLAYER_POST.register((player) -> {
            if (!player.level().isClientSide()) {
                if (!player.isUsingItem()) {
                    player.getInventory().items.iterator().forEachRemaining(stack -> {
                        Item item = stack.getItem();
                        if (item instanceof GiantSwordItem && ((GiantSwordItem) item).getCooldown(player, stack) > 0) {
                            resetSwordCooldown(player, stack, (GiantSwordItem) item);
                        } else if (item instanceof AsgardShieldItem && ((AsgardShieldItem) item).getCooldown(player, stack) > 0) {
                            resetShieldCooldown(player, stack, (AsgardShieldItem) item);
                        }
                    });
                }
                else if (player.isUsingItem()) {
                    ItemStack usedStack = player.getItemInHand(player.getUsedItemHand());
                    Item usedItem = usedStack.getItem();
                    player.getInventory().items.iterator().forEachRemaining(stack -> {
                        Item item = stack.getItem();
                        switch (usedItem) {
                            case GiantSwordItem giantSwordItem when item instanceof GiantSwordItem && !player.getCooldowns().isOnCooldown(item) -> {
                                if (giantSwordItem.getCooldown(player, usedStack) != ((GiantSwordItem) item).getCooldown(player, stack))
                                    resetSwordCooldown(player, stack, (GiantSwordItem) item);
                            }
                            case AsgardShieldItem asgardShieldItem when item instanceof AsgardShieldItem && !player.getCooldowns().isOnCooldown(item) -> {
                                if (asgardShieldItem.getCooldown(player, usedStack) != ((AsgardShieldItem) item).getCooldown(player, stack))
                                    resetShieldCooldown(player, stack, (AsgardShieldItem) item);
                            }
                            case GiantSwordItem ignored when item instanceof AsgardShieldItem && !player.getCooldowns().isOnCooldown(item) ->
                                    resetShieldCooldown(player, stack, (AsgardShieldItem) item);
                            case AsgardShieldItem ignored when item instanceof GiantSwordItem && !player.getCooldowns().isOnCooldown(item) ->
                                    resetSwordCooldown(player, stack, (GiantSwordItem) item);
                            default -> {
                                if (item instanceof GiantSwordItem && ((GiantSwordItem) item).getCooldown(player, stack) > 0)
                                    resetSwordCooldown(player, stack, (GiantSwordItem) item);
                                else if (item instanceof AsgardShieldItem && ((AsgardShieldItem) item).getCooldown(player, stack) > 0)
                                    resetShieldCooldown(player, stack, (AsgardShieldItem) item);
                            }
                        }
                    });
                }
            }
        });
    }
    private static void resetSwordCooldown(Player player, ItemStack stack, GiantSwordItem sword) {
        player.getCooldowns().addCooldown(sword, sword.getCooldown(player, stack) / 2);
        sword.resetCooldown(player, stack);
    }

    private static void resetShieldCooldown(Player player, ItemStack stack, AsgardShieldItem shield) {
        player.getCooldowns().addCooldown(shield, shield.getCooldown(player, stack) / 2);
        shield.resetCooldown(player, stack);
    }
}
