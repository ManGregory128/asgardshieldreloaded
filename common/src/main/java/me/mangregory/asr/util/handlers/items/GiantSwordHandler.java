package me.mangregory.asr.util.handlers.items;

import dev.architectury.event.EventResult;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.util.handlers.EventHandler;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import static me.mangregory.asr.util.handlers.EventHandler.playSound;

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
                playSound(player, SoundEvents.STONE_BREAK, 0.8F);
                break;
            case "asr:iron_giant_sword", "asr:golden_giant_sword":
                playSound(player, SoundEvents.ANVIL_LAND, 0.6F);
                break;
            case "asr:diamond_giant_sword":
                playSound(player, SoundEvents.NOTE_BLOCK_CHIME.value(), 0.8F);
                break;
            case "asr:netherquartz_giant_sword":
                playSound(player, SoundEvents.NOTE_BLOCK_XYLOPHONE.value(), 0.8F);
                break;
            case "asr:skull_giant_sword":
                playSound(player, SoundEvents.SKELETON_HURT, 0.8F);
                break;
            case "asr:ender_giant_sword":
                playSound(player, SoundEvents.ENDER_DRAGON_HURT, 0.8F);
                break;
            case "asr:copper_giant_sword":
                playSound(player, SoundEvents.COPPER_GOLEM_HURT, 0.8F);
                break;
            case "asr:netherite_giant_sword":
                playSound(player, SoundEvents.NETHERITE_BLOCK_BREAK, 0.8F);
                break;
        }
        EventHandler.knockbackEnemy(player, enemy, projectile, knockback);
        player.getItemInHand(player.getUsedItemHand()).hurtAndBreak((int) damageAccumulator, player, player.getUsedItemHand());
        return EventResult.pass();
    }
}
