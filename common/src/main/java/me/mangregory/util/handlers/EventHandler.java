package me.mangregory.util.handlers;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import me.mangregory.items.GiantSwordItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EventHandler {
    public static void registerEvents() {
        // Register attack entity event
        PlayerEvent.ATTACK_ENTITY.register((player, level, entity, hand, hitResult) -> {
            Vec3 pos = player.getPosition(0);

            if (level instanceof ServerLevel serverLevel &&
                    player.getItemInHand(player.swingingArm).getItem().toString().equals("asr:ender_giant_sword")) {
                enderFx(serverLevel, pos);
            }

            return EventResult.pass();
        });

        // Register right-click item event (for blocking)
        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            Level level = player.level();
            Vec3 pos = player.getPosition(0);

            Item itemMainHand = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            Item itemOffHand = player.getItemInHand(InteractionHand.OFF_HAND).getItem();
            ItemStack itemStackUsed = player.getItemInHand(hand);

            if (itemMainHand instanceof GiantSwordItem && itemOffHand instanceof ShieldItem) {
                if (hand == InteractionHand.MAIN_HAND) {
                    player.stopUsingItem();
                    ((GiantSwordItem) itemMainHand).resetCooldown();
                    return EventResult.interruptFalse().asMinecraft();
                }
            }

            if (level instanceof ServerLevel serverLevel && (
                    player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:ender_giant_sword") ||
                            player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:ender_shield") ||
                            player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:gilded_ender_shield"))) {
                enderFx(serverLevel, pos);
            }

            if (level instanceof ServerLevel serverLevel && (
                    player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:skull_shield") ||
                            player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:gilded_skull_shield"))) {
                skullFx(serverLevel, pos);
            }

            return EventResult.pass().asMinecraft();
        });

        // Register living hurt event (for blocking damage)
        EntityEvent.LIVING_HURT.register((entity, damageSource, amount) -> {
            if (entity instanceof Player player && !entity.level().isClientSide()) {
                return handleBlockingDamage(player, damageSource, amount);
            }
            return EventResult.pass();
        });
    }

    private static EventResult handleBlockingDamage(Player player, DamageSource source, float amount) {
        if (player.isBlocking() && (source.is(DamageTypes.MOB_ATTACK) || source.is(DamageTypes.MOB_PROJECTILE)
                || source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION)
                || source.is(DamageTypes.ARROW) || source.is(DamageTypes.PLAYER_ATTACK)
                || source.is(DamageTypes.PLAYER_EXPLOSION) || source.is(DamageTypes.FIREBALL))) {

            ItemStack stack = player.getItemInHand(player.swingingArm);
            Item item = stack.getItem();
            float damage = amount * 0.5F;
            float knockback = 0.0F;
            Entity projectile = source.getDirectEntity();
            Entity enemy = source.getEntity();

            if (item instanceof GiantSwordItem) {
                // Handle giant sword blocking logic
                switch (item.toString()) {
                    case "asr:wooden_giant_sword":
                        knockback = 1.0F;
                        break;
                    case "asr:stone_giant_sword":
                        knockback = 1.0F;
                        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                        break;
                    case "asr:iron_giant_sword":
                        knockback = 1.0F;
                        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                        break;
                    case "asr:golden_giant_sword":
                        knockback = 1.0F;
                        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                        break;
                    case "asr:diamond_giant_sword":
                        knockback = 1.0F;
                        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.value(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                        break;
                    case "asr:netherquartz_giant_sword":
                        knockback = 1.0F;
                        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.value(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                        break;
                    case "asr:skull_giant_sword":
                        knockback = 1.0F;
                        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                        break;
                    case "asr:ender_giant_sword":
                        knockback = 1.0F;
                        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                        break;
                }

                // Apply knockback to enemy
                if (enemy instanceof LivingEntity && projectile == enemy) {
                    ((LivingEntity) enemy).knockback(knockback * 0.4F,
                            player.getX() - enemy.getX(),
                            player.getZ() - enemy.getZ());
                }
            }
        }

        return EventResult.pass();
    }

    public static void enderFx(ServerLevel level, Vec3 playerPos) {
        for (int i = 0; i < 3; i++) {
            int rand1 = level.random.nextInt(2) * 2 - 1;
            int rand2 = level.random.nextInt(2) * 2 - 1;
            double xCoord = playerPos.x + 0.5D + 0.25D * rand1;
            double yCoord = playerPos.y() + level.random.nextFloat();
            double zCoord = playerPos.z() + 0.5D + 0.25D * rand2;
            double speed = (level.random.nextFloat() - 0.5D) * 0.125D;

            level.sendParticles(ParticleTypes.PORTAL, xCoord, yCoord, zCoord, 2, 0, 0, 0, speed);
        }
    }

    public static void skullFx(ServerLevel level, Vec3 playerPos) {
        double xCoord = playerPos.x;
        double yCoord = playerPos.y + 1.0D;
        double zCoord = playerPos.z;
        double speed = (level.random.nextFloat() - 0.5D) * 0.125D;
        level.sendParticles(ParticleTypes.CLOUD, xCoord, yCoord, zCoord, 2, 0, 0, 0, speed);
    }

    // Helper methods for teleporting enemies, reflecting projectiles, etc.
    // ... (implement similar to your original methods)
}

