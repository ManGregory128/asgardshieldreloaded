package me.mangregory.asr.util.handlers.items;

import dev.architectury.event.EventResult;
import me.mangregory.asr.config.ModConfig;
import me.mangregory.asr.util.handlers.EventHandler;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static me.mangregory.asr.util.handlers.EventHandler.playSound;

public class GiantSwordHandler {
    public static EventResult handleSwordFunctionality(Player player, Item item, DamageSource source) {
        float knockback = ModConfig.GIANT_SWORD_BASE_KNOCKBACK;
        Entity projectile = source.getDirectEntity();
        Entity enemy = source.getEntity();
        long damageAccumulator = ModConfig.GIANT_SWORD_BASE_ATTACKDMG;

        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        
        if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "stone_giant_sword"))) {
            playSound(player, SoundEvents.STONE_BREAK, 0.8F);
        } else if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "iron_giant_sword")) || 
                   itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "golden_giant_sword"))) {
            playSound(player, SoundEvents.ANVIL_LAND, 0.6F);
        } else if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "diamond_giant_sword"))) {
            playSound(player, SoundEvents.NOTE_BLOCK_CHIME.value(), 0.8F);
        } else if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "netherquartz_giant_sword"))) {
            playSound(player, SoundEvents.NOTE_BLOCK_XYLOPHONE.value(), 0.8F);
        } else if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "skull_giant_sword"))) {
            playSound(player, SoundEvents.SKELETON_HURT, 0.8F);
        } else if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "ender_giant_sword"))) {
            playSound(player, SoundEvents.ENDER_DRAGON_HURT, 0.8F);
        } else if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "copper_giant_sword"))) {
            playSound(player, SoundEvents.COPPER_BREAK, 0.8F);
        } else if (itemId.equals(ResourceLocation.fromNamespaceAndPath("asr", "netherite_giant_sword"))) {
            playSound(player, SoundEvents.NETHERITE_BLOCK_BREAK, 0.8F);
        }
        
        EventHandler.knockbackEnemy(player, enemy, projectile, knockback);
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        stack.hurtAndBreak((int) damageAccumulator, player, player.getEquipmentSlotForItem(stack));
        playSound(player, SoundEvents.SHIELD_BLOCK, 0.8F);
        return EventResult.interruptFalse();
    }
}
