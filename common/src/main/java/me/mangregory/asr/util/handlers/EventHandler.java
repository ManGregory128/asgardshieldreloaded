package me.mangregory.asr.util.handlers;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.*;
import me.mangregory.asr.items.AsgardShieldItem;
import me.mangregory.asr.items.GiantSwordItem;
import me.mangregory.asr.util.handlers.items.AsgardShieldHandler;
import me.mangregory.asr.util.handlers.items.GiantSwordHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class EventHandler {
    public static void registerEvents() {
        // Register right-click item event (for blocking)
        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            Level level = player.level();

            // Handle Giant Sword + Shield combo blocking
            Item itemMainHand = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            Item itemOffHand = player.getItemInHand(InteractionHand.OFF_HAND).getItem();

            ItemStack usedStack = player.getItemInHand(hand);
            Item usedItem = usedStack.getItem();
            if (itemMainHand instanceof GiantSwordItem && itemOffHand instanceof ShieldItem) {
                if (hand == InteractionHand.MAIN_HAND
                        && !player.getCooldowns().isOnCooldown(usedItem)) {
                    player.stopUsingItem();
                    ((GiantSwordItem) itemMainHand).resetCooldown(player, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return CompoundEventResult.interruptFalse(usedStack);
                }
            }
            Item usedItemInHand = player.getItemInHand(player.getUsedItemHand()).getItem();
            ResourceLocation usedItemId = BuiltInRegistries.ITEM.getKey(usedItemInHand);
            
            if (level instanceof ServerLevel serverLevel && !player.getCooldowns().isOnCooldown(usedItem) && (
                    usedItemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "ender_giant_sword")) ||
                            usedItemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "ender_shield")) ||
                            usedItemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "gilded_ender_shield")))) {
                particleFx(serverLevel, player, ParticleTypes.PORTAL);
            }

            if (level instanceof ServerLevel serverLevel && !player.getCooldowns().isOnCooldown(usedItem) && (
                    usedItemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "skull_giant_sword")) ||
                            usedItemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "skull_shield")) ||
                            usedItemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "gilded_skull_shield")))) {
                particleFx(serverLevel, player, ParticleTypes.CLOUD);
            }

            return CompoundEventResult.pass();
        });

        // Register living hurt event (for blocking damage)
        EntityEvent.LIVING_HURT.register((entity, damageSource, amount) -> {
            if (entity instanceof Player player && !entity.level().isClientSide()
                    && player.isBlocking() && !damageSource.is(DamageTypeTags.BYPASSES_SHIELD)) {
                InteractionHand hand = player.getUsedItemHand();
                Item item = entity.getItemInHand(hand).getItem();
                if (item instanceof GiantSwordItem)
                    return GiantSwordHandler.handleSwordFunctionality(player, item, damageSource);
                else if (item instanceof AsgardShieldItem)
                    return AsgardShieldHandler.handleShieldFunctionality(player, item, hand, damageSource);
            }
            return EventResult.pass();
        });
    }

    public static void hurtNearbyEntity(Entity enemy) {
        List<Entity> entities = enemy.level().getEntities(null, new AABB(
                enemy.getX() - 4,
                enemy.getY() - 4,
                enemy.getZ() - 4,
                enemy.getX() + 4,
                enemy.getY() + 4,
                enemy.getZ() + 4));
        if (entities.size() > 1) {
            ((LivingEntity) enemy).travel(entities.get(1).getPosition(0));
            ((LivingEntity) enemy).doHurtTarget(enemy);
        } else enemy.hurt(enemy.damageSources().magic(), 10000.0F);
    }

    public static void knockbackEnemy(Player player, Entity enemy, Entity projectile, float knockback) {
        if (enemy instanceof LivingEntity && projectile == enemy) {
            ((LivingEntity) enemy).knockback(knockback * 0.4F,
                    player.getX() - enemy.getX(),
                    player.getZ() - enemy.getZ());
        }
    }

    public static void particleFx(ServerLevel level, Player player, SimpleParticleType particleType) {
        Vec3 lookAngle = player.getLookAngle();
        Vec3 playerPos = player.getPosition(0);
        for (int i = 0; i < 3; i++) {
            // Spawn particles in front of the player at different distances
            double distance = 0.5D + (i * 0.3D); // 0.5 to 1.7 blocks in front
            double xCoord = playerPos.x + lookAngle.x * distance + (level.getRandom().nextDouble() - 0.5D) * 0.5D;
            double yCoord = playerPos.y + player.getEyeHeight() * 0.5D + (level.getRandom().nextDouble() - 0.5D) * 0.3D;
            double zCoord = playerPos.z + lookAngle.z * distance + (level.getRandom().nextDouble() - 0.5D) * 0.5D;

            double speed = (level.getRandom().nextFloat() - 0.5D) * 0.125D;

            level.sendParticles(particleType, xCoord, yCoord, zCoord, 2, 0.1, 0.1, 0.1, speed);
            if (particleType == ParticleTypes.CLOUD)
                break;
        }
    }

    public static void collectArrow(Player player, Arrow arrow) {
        arrow.setDeltaMovement(0, 0, 0);
        arrow.discard();
        ItemStack arrowStack = new ItemStack(Items.ARROW);
        player.getInventory().add(arrowStack);
        playSound(player, SoundEvents.ITEM_PICKUP, 0.8F);
    }

    public static void reflectArrow(Player player, Arrow arrow, Entity attacker) {
        arrow.setOwner(player);
        arrow.shootFromRotation(player, attacker.xRotO, attacker.yRotO, 0, 7.5F, 0.2F);
        arrow.hurtMarked = true;
    }

    public static void playSound(Player player, SoundEvent sound, float volume) {
        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), sound,
                SoundSource.PLAYERS, volume, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
    }

    public static float teleportEnemy(Entity enemy, float knockback) {
        if (!(enemy.level() instanceof ServerLevel serverLevel))
            return knockback;

        Vec3 prevPos = enemy.getPosition(0);
        BlockPos enemyPos = enemy.blockPosition();

        // Try to find a safe teleport location within a reasonable range
        for (int attempts = 0; attempts < 20; attempts++) {
            // Smaller range to keep enemy visible (8 blocks instead of 32)
            double offsetX = (serverLevel.getRandom().nextDouble() - 0.5D) * 16.0D;
            double offsetZ = (serverLevel.getRandom().nextDouble() - 0.5D) * 16.0D;

            BlockPos targetPos = enemyPos.offset((int)offsetX, 0, (int)offsetZ);
            // Find safe Y position by checking for valid ground
            BlockPos safePos = findSafePosition(serverLevel, targetPos, enemy);

            if (safePos != null) {
                enemy.teleportTo(safePos.getX() + 0.5, safePos.getY(), safePos.getZ() + 0.5);
                serverLevel.sendParticles(ParticleTypes.PORTAL, prevPos.x, prevPos.y + 1.0, prevPos.z,
                        50, 0.5, 1.0, 0.5, 0.2);
                // Play teleport sounds
                serverLevel.playSound(null, prevPos.x, prevPos.y, prevPos.z,
                        SoundEvents.ENDERMAN_TELEPORT, enemy.getSoundSource(), 1.0F, 1.0F);
                enemy.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);

                return 0.0F;
            }
        }
        return knockback;
    }

    private static BlockPos findSafePosition(ServerLevel level, BlockPos startPos, Entity entity) {
        // Check from slightly below to slightly above the start position
        for (int yOffset = -3; yOffset <= 10; yOffset++) {
            BlockPos checkPos = startPos.offset(0, yOffset, 0);

            // Ensure we're within world bounds
            if (level.isOutsideBuildHeight(checkPos.getY()))
                continue;
            // Check if position is safe for teleportation
            if (isSafePosition(level, checkPos, entity))
                return checkPos;
        }
        return null;
    }

    private static boolean isSafePosition(ServerLevel level, BlockPos pos, Entity entity) {
        // Check if there's solid ground to stand on
        BlockPos groundPos = pos.below();
        if (!level.getBlockState(groundPos).isSolid()) {
            return false;
        }
        // Check if the entity has enough space
        int entityHeight = (int) Math.ceil(entity.getBbHeight());
        for (int i = 0; i < Math.max(2, entityHeight); i++) {
            BlockPos checkPos = pos.above(i);
            if (!level.getBlockState(checkPos).isAir() &&
                    !level.getBlockState(checkPos).liquid()) {
                return false;
            }
        }
        // Check if position is not in lava or dangerous blocks
        return !level.getBlockState(pos).liquid() &&
                !level.getBlockState(pos.above()).liquid();
    }
}
