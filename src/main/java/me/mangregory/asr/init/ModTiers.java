package me.mangregory.asr.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
	public static final ForgeTier CUSTOM_DIAMOND = new ForgeTier(3, 3100, 8.0f, 1.5f, 15, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.DIAMOND));
}
