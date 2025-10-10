package me.mangregory.util.handlers;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.*;
import me.mangregory.AsgardShieldReloaded;
import me.mangregory.items.AsgardShieldItem;
import me.mangregory.items.GiantSwordItem;
import me.mangregory.util.ModConfig;
import me.mangregory.util.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventHandler {
    public static void registerEvents() {
        // Register attack entity event
        PlayerEvent.ATTACK_ENTITY.register((player, level, entity, hand, hitResult) -> {
            if (level instanceof ServerLevel serverLevel &&
                    player.getItemInHand(player.swingingArm).getItem().toString().equals("asr:ender_giant_sword")) {
                particleFx(serverLevel, player, ParticleTypes.PORTAL);
            }
            return EventResult.pass();
        });

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

        // Register right-click item event (for blocking)
        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            Level level = player.level();

            // Handle Giant Sword + Shield combo blocking
            Item itemMainHand = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            Item itemOffHand = player.getItemInHand(InteractionHand.OFF_HAND).getItem();

            if (itemMainHand instanceof GiantSwordItem && itemOffHand instanceof ShieldItem) {
                if (hand == InteractionHand.MAIN_HAND
                        && !player.getCooldowns().isOnCooldown(player.getItemInHand(InteractionHand.OFF_HAND))) {
                    player.stopUsingItem();
                    ((GiantSwordItem) itemMainHand).resetCooldown(player, player.getItemInHand(InteractionHand.MAIN_HAND));
                    return EventResult.interruptFalse().asMinecraft();
                }
            }
            ItemStack usedStack = player.getItemInHand(hand);
            if (level instanceof ServerLevel serverLevel && !player.getCooldowns().isOnCooldown(usedStack) && (
                    player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:ender_giant_sword") ||
                            player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:ender_shield") ||
                            player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:gilded_ender_shield"))) {
                particleFx(serverLevel, player, ParticleTypes.PORTAL);
            }

            if (level instanceof ServerLevel serverLevel && !player.getCooldowns().isOnCooldown(usedStack) && (
                    player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:skull_shield") ||
                            player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("asr:gilded_skull_shield"))) {
                particleFx(serverLevel, player, ParticleTypes.CLOUD);
            }

            return EventResult.pass().asMinecraft();
        });

        // Register living hurt event (for blocking damage)
        EntityEvent.LIVING_HURT.register((entity, damageSource, amount) -> {
            if (entity instanceof Player player && !entity.level().isClientSide()
                    && player.isBlocking() && !damageSource.is(DamageTypeTags.BYPASSES_SHIELD)) {
                InteractionHand hand = player.getUsedItemHand();
                Item item = entity.getItemInHand(hand).getItem();
                if (item instanceof GiantSwordItem)
                    return handleSwordFunctionality(player, item, damageSource);
                else if (item instanceof AsgardShieldItem)
                    return handleShieldFunctionality(player, item, hand, damageSource);
            }
            return EventResult.pass();
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

    private static EventResult handleSwordFunctionality(Player player, Item item, DamageSource source) {
        float knockback = ModConfig.GIANT_SWORD_BASE_KNOCKBACK;
        Entity projectile = source.getDirectEntity();
        Entity enemy = source.getEntity();
        int damageAccumulator = 1;

        switch (item.toString()) {
            case "asr:wooden_giant_sword":
                break;
            case "asr:stone_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:iron_giant_sword", "asr:golden_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:diamond_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.NOTE_BLOCK_CHIME.value(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:netherquartz_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.NOTE_BLOCK_XYLOPHONE.value(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:skull_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:ender_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
        }
        knockbackEnemy(player, enemy, projectile, knockback);
        player.getItemInHand(player.getUsedItemHand()).hurtAndBreak(damageAccumulator, player, player.getUsedItemHand());
        return EventResult.pass();
    }

    private static EventResult handleShieldFunctionality(Player player, Item item, InteractionHand hand, DamageSource source) {
        Entity enemy = source.getEntity();
        Entity projectile = source.getDirectEntity();
        ItemStack stack = player.getItemInHand(hand);
        int damageAccumulator = 1;
        float knockback = ModConfig.ASGARD_SHIELD_BASE_KNOCKBACK;
        switch (item.toString()) {
            case "asr:wooden_shield":
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.25))
                    collectArrow(player, (Arrow) projectile);
                break;
            case "asr:gilded_wooden_shield":
                knockback += 0.5F;
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.50))
                    collectArrow(player, (Arrow) projectile);
                break;
            case "asr:stone_shield":
                if (source.is(DamageTypeTags.IS_EXPLOSION))
                    damageAccumulator += 5;
                else if (source.is(DamageTypeTags.IS_FIRE) && RandomUtil.chance(0.50))
                    damageAccumulator = 0;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_stone_shield":
                knockback += 0.5F;
                if (source.is(DamageTypeTags.IS_EXPLOSION))
                    damageAccumulator += 5;
                else if (source.is(DamageTypeTags.IS_FIRE))
                    damageAccumulator = 0;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:iron_shield":
                if (player.isInWaterOrRain())
                    damageAccumulator += 5;
                else if (source.is(DamageTypeTags.IS_EXPLOSION) && RandomUtil.chance(0.50))
                    damageAccumulator = 0;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_iron_shield":
                knockback += 0.5F;
                if (player.isInWaterOrRain())
                    damageAccumulator += 5;
                else if (source.is(DamageTypeTags.IS_EXPLOSION))
                    damageAccumulator = 0;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:diamond_shield":
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.30)) {
                    reflectArrow(player, (Arrow) projectile, source.getEntity());
                    damageAccumulator += 3;
                    knockback = 0.0F;
                }
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.value(),
                        SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_diamond_shield":
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.60)) {
                    reflectArrow(player, (Arrow) projectile, source.getEntity());
                    damageAccumulator += 3;
                    knockback = 0.0F;
                }
                else knockback += 0.5F;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.value(),
                        SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:netherquartz_shield":
                player.causeFoodExhaustion(1.0F);
                if (projectile instanceof SmallFireball && RandomUtil.chance(0.5D)) {
                    player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                            SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                    projectile.discard();
                    if (RandomUtil.chance(0.25D)) player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                }
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.value(),
                        SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_netherquartz_shield":
                player.causeFoodExhaustion(1.0F);
                knockback += 0.5F;
                if (projectile instanceof SmallFireball) {
                    player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                            SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                    projectile.discard();
                    if (RandomUtil.chance(0.50D)) player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                }
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.value(),
                        SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:ender_shield":
                if (enemy instanceof EnderDragon || enemy instanceof EnderMan || enemy instanceof Endermite) {
                    player.stopUsingItem();
                    return EventResult.interruptTrue();
                }
                if (enemy instanceof LivingEntity && RandomUtil.chance(0.2D))
                    knockback = teleportEnemy(enemy, knockback);
                else knockback += 0.5F;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_ender_shield":
                if (enemy instanceof EnderDragon || enemy instanceof EnderMan || enemy instanceof Endermite) {
                    player.stopUsingItem();
                    return EventResult.interruptTrue();
                }
                if (enemy instanceof LivingEntity && RandomUtil.chance(0.4D))
                    knockback = teleportEnemy(enemy, knockback);
                else knockback += 0.5F;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:skull_shield":
                if (RandomUtil.chance(0.1F)) damageAccumulator += 5;;
                if (enemy instanceof LivingEntity && RandomUtil.chance(0.15D))
                    hurtNearbyEntity(enemy);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_skull_shield":
                if (RandomUtil.chance(0.1F)) damageAccumulator += 5;;
                if (enemy instanceof LivingEntity && RandomUtil.chance(0.30D))
                    hurtNearbyEntity(enemy);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
        }
        knockbackEnemy(player, enemy, projectile, knockback);
        stack.hurtAndBreak(damageAccumulator, player, hand);
        return EventResult.pass();
    }

    private static void hurtNearbyEntity(Entity enemy) {
        List<Entity> entities = enemy.level().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
        if (entities.size() > 1) {
            ((LivingEntity) enemy).travel(entities.get(1).getPosition(0));
            ((LivingEntity) enemy).doHurtTarget((ServerLevel) enemy.level(), entities.get(1));
        } else enemy.hurt(enemy.damageSources().magic(), 10000.0F);
    }

    private static void knockbackEnemy(Player player, Entity enemy, Entity projectile, float knockback) {
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
            double xCoord = playerPos.x + lookAngle.x * distance + (level.random.nextDouble() - 0.5D) * 0.5D;
            double yCoord = playerPos.y + player.getEyeHeight() * 0.5D + (level.random.nextDouble() - 0.5D) * 0.3D;
            double zCoord = playerPos.z + lookAngle.z * distance + (level.random.nextDouble() - 0.5D) * 0.5D;

            double speed = (level.random.nextFloat() - 0.5D) * 0.125D;

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
        player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
    }

    public static void reflectArrow(Player player, Arrow arrow, Entity attacker) {
        arrow.setOwner(player);
        arrow.shootFromRotation(player, attacker.xRotO, attacker.yRotO, 0, 7.5F, 0.2F);
        arrow.hurtMarked = true;
    }

    public static float teleportEnemy(Entity enemy, float knockback) {
        if (!(enemy.level() instanceof ServerLevel serverLevel)) {
            return knockback;
        }

        Vec3 prevPos = enemy.getPosition(0);
        BlockPos enemyPos = enemy.blockPosition();

        // Try to find a safe teleport location within a reasonable range
        for (int attempts = 0; attempts < 20; attempts++) {
            // Smaller range to keep enemy visible (8 blocks instead of 32)
            double offsetX = (serverLevel.random.nextDouble() - 0.5D) * 16.0D;
            double offsetZ = (serverLevel.random.nextDouble() - 0.5D) * 16.0D;

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
            if (level.isOutsideBuildHeight(checkPos.getY())) {
                continue;
            }
            // Check if position is safe for teleportation
            if (isSafePosition(level, checkPos, entity)) {
                return checkPos;
            }
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
