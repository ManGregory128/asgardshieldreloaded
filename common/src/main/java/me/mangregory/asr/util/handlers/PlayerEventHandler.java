package me.mangregory.asr.util.handlers;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import me.mangregory.asr.network.ConfigSyncPacket;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.UseCooldown;

import java.util.Optional;
import java.util.UUID;

public class PlayerEventHandler {
    public static void registerEvents() {
        // Register attack entity event
        PlayerEvent.ATTACK_ENTITY.register((player, level, entity, hand, hitResult) -> {
            if (level instanceof ServerLevel serverLevel &&
                    player.getItemInHand(player.swingingArm).getItem().toString().equals("asr:ender_giant_sword")) {
                EventHandler.particleFx(serverLevel, player, ParticleTypes.PORTAL);
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
                    player.getInventory().iterator().forEachRemaining(stack -> {
                        Item item = stack.getItem();
                        if (item instanceof GiantSwordItem && ((GiantSwordItem) item).getCooldown(player, stack) > 0) {
                            resetSwordCooldown(player, stack, (GiantSwordItem) item);
                        } else if (item instanceof AsgardShieldItem && ((AsgardShieldItem) item).getCooldown(player, stack) > 0) {
                            resetShieldCooldown(player, stack, (AsgardShieldItem) item);
                        } else if (item instanceof GiantSwordItem || item instanceof AsgardShieldItem) {
                            if (!stack.has(DataComponents.USE_COOLDOWN)) {
                                String uniqueId = UUID.randomUUID().toString();
                                String itemType = item instanceof GiantSwordItem ? "giant_sword" : "shield";
                                ResourceLocation uniqueCooldownGroup = ResourceLocation.fromNamespaceAndPath(
                                        AsgardShieldReloaded.MOD_ID,
                                        itemType + "_" + uniqueId
                                );
                                stack.set(DataComponents.USE_COOLDOWN, new UseCooldown(0.1f, Optional.of(uniqueCooldownGroup)));
                            }
                        }
                    });
                }
                else if (player.isUsingItem()) {
                    ItemStack usedStack = player.getItemInHand(player.getUsedItemHand());
                    Item usedItem = usedStack.getItem();
                    player.getInventory().iterator().forEachRemaining(stack -> {
                        Item item = stack.getItem();
                        switch (usedItem) {
                            case GiantSwordItem giantSwordItem when item instanceof GiantSwordItem && !player.getCooldowns().isOnCooldown(stack) -> {
                                if (giantSwordItem.getCooldown(player, usedStack) != ((GiantSwordItem) item).getCooldown(player, stack))
                                    resetSwordCooldown(player, stack, (GiantSwordItem) item);
                            }
                            case AsgardShieldItem asgardShieldItem when item instanceof AsgardShieldItem && !player.getCooldowns().isOnCooldown(stack) -> {
                                if (asgardShieldItem.getCooldown(player, usedStack) != ((AsgardShieldItem) item).getCooldown(player, stack))
                                    resetShieldCooldown(player, stack, (AsgardShieldItem) item);
                            }
                            case GiantSwordItem giantSwordItem when item instanceof AsgardShieldItem && !player.getCooldowns().isOnCooldown(stack) ->
                                    resetShieldCooldown(player, stack, (AsgardShieldItem) item);
                            case AsgardShieldItem asgardShieldItem when item instanceof GiantSwordItem && !player.getCooldowns().isOnCooldown(stack) ->
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
        player.getCooldowns().addCooldown(stack, sword.getCooldown(player, stack) / 2);
        sword.resetCooldown(player, stack);
    }

    private static void resetShieldCooldown(Player player, ItemStack stack, AsgardShieldItem item) {
        player.getCooldowns().addCooldown(stack, item.getCooldown(player, stack) / 2);
        item.resetCooldown(player, stack);
    }
}
