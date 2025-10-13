package me.mangregory.asr.util.handlers.items;

import dev.architectury.event.EventResult;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.util.RandomUtil;
import me.mangregory.asr.util.handlers.EventHandler;
import net.minecraft.core.BlockPos;
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

public class AsgardShieldHandler {
    public static EventResult handleShieldFunctionality(Player player, Item item, InteractionHand hand, DamageSource source) {
        Entity enemy = source.getEntity();
        Entity projectile = source.getDirectEntity();
        ItemStack stack = player.getItemInHand(hand);
        long damageAccumulator = ModConfig.ASGARD_SHIELD_BASE_ATTACKDMG;
        float knockback = ModConfig.ASGARD_SHIELD_BASE_KNOCKBACK;
        switch (item.toString()) {
            case "asr:wooden_shield":
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.25))
                    EventHandler.collectArrow(player, (Arrow) projectile);
                break;
            case "asr:gilded_wooden_shield":
                knockback += 0.5F;
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.50))
                    EventHandler.collectArrow(player, (Arrow) projectile);
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
                    EventHandler.reflectArrow(player, (Arrow) projectile, source.getEntity());
                    damageAccumulator += 3;
                    knockback = 0.0F;
                }
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.value(),
                        SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_diamond_shield":
                if (source.is(DamageTypes.ARROW) && RandomUtil.chance(0.60)) {
                    EventHandler.reflectArrow(player, (Arrow) projectile, source.getEntity());
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
                    knockback = EventHandler.teleportEnemy(enemy, knockback);
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
                    knockback = EventHandler.teleportEnemy(enemy, knockback);
                else knockback += 0.5F;
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT,
                        SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:skull_shield":
                if (RandomUtil.chance(0.1F)) damageAccumulator += 5;
                if (enemy instanceof LivingEntity && RandomUtil.chance(0.15D))
                    EventHandler.hurtNearbyEntity(enemy);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
            case "asr:gilded_skull_shield":
                if (RandomUtil.chance(0.1F)) damageAccumulator += 5;
                if (enemy instanceof LivingEntity && RandomUtil.chance(0.30D))
                    EventHandler.hurtNearbyEntity(enemy);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                break;
        }
        EventHandler.knockbackEnemy(player, enemy, projectile, knockback);
        stack.hurtAndBreak((int) damageAccumulator, player, hand);
        return EventResult.pass();
    }
}
