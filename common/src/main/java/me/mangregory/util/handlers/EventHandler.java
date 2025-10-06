package me.mangregory.util.handlers;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import me.mangregory.items.AsgardShieldItem;
import me.mangregory.items.GiantSwordItem;
import me.mangregory.util.ModConfig;
import me.mangregory.util.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

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
            if (entity instanceof Player player && !entity.level().isClientSide()
                    && player.isBlocking() && !damageSource.is(DamageTypeTags.BYPASSES_SHIELD)) {
                Item item = entity.getItemInHand(entity.swingingArm).getItem();
                if (item instanceof GiantSwordItem)
                    return handleSwordFunctionality(player, item, damageSource);
                else if (item instanceof AsgardShieldItem)
                    return handleShieldFunctionality(player, item, damageSource);
            }
            return EventResult.pass();
        });
    }

    private static EventResult handleSwordFunctionality(Player player, Item item, DamageSource source) {
        float knockback = ModConfig.GIANT_SWORD_BASE_KNOCKBACK;
        Entity projectile = source.getDirectEntity();
        Entity enemy = source.getEntity();

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

        return EventResult.pass();
    }

    private static EventResult handleShieldFunctionality(Player player, Item item, DamageSource source) {
        Entity enemy = source.getEntity();
        Entity projectile = source.getDirectEntity();
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
                    player.getItemInHand(player.swingingArm).hurtAndBreak(6, player, player.swingingArm);
                else if (source.is(DamageTypeTags.IS_FIRE) && RandomUtil.chance(0.50))
                    player.getItemInHand(player.swingingArm).hurtAndBreak(-4, player, player.swingingArm);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_stone_shield":
                knockback += 0.5F;
                if (source.is(DamageTypeTags.IS_EXPLOSION))
                    player.getItemInHand(player.swingingArm).hurtAndBreak(6, player, player.swingingArm);
                else if (source.is(DamageTypeTags.IS_FIRE))
                    player.getItemInHand(player.swingingArm).hurtAndBreak(-4, player, player.swingingArm);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:iron_shield":
                if (player.isInWaterOrRain())
                    player.getItemInHand(player.swingingArm).hurtAndBreak(6, player, player.swingingArm);
                else if (source.is(DamageTypeTags.IS_EXPLOSION) && RandomUtil.chance(0.50))
                    player.getItemInHand(player.swingingArm).hurtAndBreak(-4, player, player.swingingArm);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_iron_shield":
                knockback += 0.5F;
                if (player.isInWaterOrRain())
                    player.getItemInHand(player.swingingArm).hurtAndBreak(6, player, player.swingingArm);
                else if (source.is(DamageTypeTags.IS_EXPLOSION))
                    player.getItemInHand(player.swingingArm).hurtAndBreak(-4, player, player.swingingArm);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:diamond_shield":
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.30)) {
                    reflectArrow(player, (Arrow) projectile, source.getEntity());
                    player.getItemInHand(player.swingingArm).hurtAndBreak(4, player, player.swingingArm);
                    knockback = 0.0F;
                }
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.value(),
                        SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_diamond_shield":
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.60)) {
                    reflectArrow(player, (Arrow) projectile, source.getEntity());
                    player.getItemInHand(player.swingingArm).hurtAndBreak(4, player, player.swingingArm);
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
                    ((LivingEntity) enemy).randomTeleport(4.0, 4.0, 2.0, true);
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
                    ((LivingEntity) enemy).randomTeleport(4.0, 4.0, 2.0, true);
                else knockback += 0.5F;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:skull_shield":
                if (enemy instanceof LivingEntity && RandomUtil.chance(0.15D)) {
                    List<Entity> entities = enemy.level().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
                    if (entities.size() > 1) {
                        ((LivingEntity) enemy).travel(entities.get(1).getPosition(0));
                        ((LivingEntity) enemy).doHurtTarget((ServerLevel) enemy.level(), entities.get(1));
                    } else enemy.hurt(enemy.damageSources().magic(), 10000.0F);
                }
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
        }
        knockbackEnemy(player, enemy, projectile, knockback);
        return EventResult.pass();
    }

    private static void knockbackEnemy(Player player, Entity enemy, Entity projectile, float knockback) {
        if (enemy instanceof LivingEntity && projectile == enemy) {
            ((LivingEntity) enemy).knockback(knockback * 0.4F,
                    player.getX() - enemy.getX(),
                    player.getZ() - enemy.getZ());
        }
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
}

