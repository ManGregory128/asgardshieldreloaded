package me.mangregory.asr.items;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

final class TooltipKeys {
    private TooltipKeys() {}

    static String itemBaseKey(ItemStack itemStack) {
        Identifier id = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        // id: asr:diamond_shield -> item.asr.diamond_shield
        return "item." + id.getNamespace() + "." + id.getPath();
    }

    static String perk(ItemStack itemStack) {
        return itemBaseKey(itemStack) + ".perk";
    }

    static String perkDesc(ItemStack itemStack) {
        return itemBaseKey(itemStack) + ".perk.desc";
    }

    static String weakness(ItemStack itemStack) {
        return itemBaseKey(itemStack) + ".weakness";
    }

    static String weaknessDesc(ItemStack itemStack) {
        return itemBaseKey(itemStack) + ".weakness.desc";
    }
}
