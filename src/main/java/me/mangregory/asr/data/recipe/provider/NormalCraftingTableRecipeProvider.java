package me.mangregory.asr.data.recipe.provider;

import me.mangregory.asr.data.recipe.MainModRecipeProvider;
import me.mangregory.asr.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class NormalCraftingTableRecipeProvider extends MainModRecipeProvider {

    private final RecipeOutput output;

    public NormalCraftingTableRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput output) {
        super(generator, lookupProvider);
        this.output = output;
    }

    public void build() {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.IRON_GIANT_SWORD.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .define('#', Tags.Items.INGOTS_IRON)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
                .save(output, "iron_giant_sword");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.STONE_GIANT_SWORD.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .define('#', Tags.Items.STONES)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .unlockedBy("has_item", has(Tags.Items.STONES))
                .save(output, "stone_giant_sword");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.DIAMOND_GIANT_SWORD.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .define('#', Tags.Items.GEMS_DIAMOND)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .unlockedBy("has_item", has(Tags.Items.GEMS_DIAMOND))
                .save(output, "diamond_giant_sword");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GOLDEN_GIANT_SWORD.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .define('#', Tags.Items.INGOTS_GOLD)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_GOLD))
                .save(output, "golden_giant_sword");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.WOODEN_GIANT_SWORD.get())
                .define('/', Tags.Items.RODS_WOODEN)
                .define('#', ItemTags.PLANKS)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .unlockedBy("has_item", has(ItemTags.PLANKS))
                .save(output, "wooden_giant_sword");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.ENDER_GIANT_SWORD.get())
                .define('/', Tags.Items.RODS_BLAZE)
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_GOLD))
                .save(output, "ender_giant_sword");
    }
}
