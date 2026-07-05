package me.mangregory.asr.util.handlers.items;

import dev.architectury.event.EventResult;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.util.handlers.EventHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class GiantSwordHandler {
    public static EventResult handleSwordFunctionality(Player player, Item item, DamageSource source) {
        float knockback = ModConfig.GIANT_SWORD_BASE_KNOCKBACK;
        Entity projectile = source.getDirectEntity();
        Entity enemy = source.getEntity();
        long damageAccumulator = ModConfig.GIANT_SWORD_BASE_ATTACKDMG;

        switch (item.toString()) {
            case "asr:wooden_giant_sword":
                break;
            case "asr:stone_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                break;
            case "asr:iron_giant_sword", "asr:golden_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                break;
            case "asr:diamond_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.NOTE_BLOCK_CHIME.value(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                break;
            case "asr:netherquartz_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.NOTE_BLOCK_XYLOPHONE.value(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                break;
            case "asr:skull_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                break;
            case "asr:ender_giant_sword":
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                break;
        }
        EventHandler.knockbackEnemy(player, enemy, projectile, knockback);
        player.getItemInHand(player.getUsedItemHand()).hurtAndBreak((int) damageAccumulator, player, player.getUsedItemHand());
        return EventResult.pass();
    }
}
