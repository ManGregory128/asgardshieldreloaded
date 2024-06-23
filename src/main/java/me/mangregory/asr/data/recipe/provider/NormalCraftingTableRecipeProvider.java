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
                .define('#', ItemTags.STONE_TOOL_MATERIALS)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.WOODEN_SHIELD.get())
                .define('#', ItemTags.PLANKS)
                .define('/', Tags.Items.LEATHERS)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(Tags.Items.LEATHERS))
                .save(output, "wooden_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.STONE_SHIELD.get())
                .define('#', ItemTags.STONE_TOOL_MATERIALS)
                .define('/', Tags.Items.LEATHERS)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(Tags.Items.LEATHERS))
                .save(output, "stone_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.SKULL_SHIELD.get())
                .define('#', ItemTags.STONE_TOOL_MATERIALS)
                .define('/', Tags.Items.LEATHERS)
                .define('S', Items.SKELETON_SKULL)
                .pattern("#S#")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(Items.SKELETON_SKULL))
                .save(output, "skull_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.NETHERQUARTZ_SHIELD.get())
                .define('#', Tags.Items.GEMS_QUARTZ)
                .define('/', Tags.Items.LEATHERS)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(Tags.Items.GEMS_QUARTZ))
                .save(output, "netherquartz_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.IRON_SHIELD.get())
                .define('#', Tags.Items.INGOTS_IRON)
                .define('/', Tags.Items.LEATHERS)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
                .save(output, "iron_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.ENDER_SHIELD.get())
                .define('#', Items.OBSIDIAN)
                .define('E', Items.ENDER_EYE)
                .define('/', Tags.Items.LEATHERS)
                .pattern("#E#")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(Items.ENDER_EYE))
                .save(output, "ender_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.DIAMOND_SHIELD.get())
                .define('#', Tags.Items.GEMS_DIAMOND)
                .define('/', Tags.Items.LEATHERS)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(Tags.Items.GEMS_DIAMOND))
                .save(output, "diamond_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GILDED_WOODEN_SHIELD.get())
                .define('#', Tags.Items.INGOTS_GOLD)
                .define('/', ItemInit.WOODEN_SHIELD.get())
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(ItemInit.WOODEN_SHIELD.get()))
                .save(output, "gilded_wooden_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GILDED_STONE_SHIELD.get())
                .define('#', Tags.Items.INGOTS_GOLD)
                .define('/', ItemInit.STONE_SHIELD.get())
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(ItemInit.STONE_SHIELD.get()))
                .save(output, "gilded_stone_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GILDED_SKULL_SHIELD.get())
                .define('#', Tags.Items.INGOTS_GOLD)
                .define('/', ItemInit.SKULL_SHIELD.get())
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(ItemInit.SKULL_SHIELD.get()))
                .save(output, "gilded_skull_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GILDED_NETHERQUARTZ_SHIELD.get())
                .define('#', Tags.Items.INGOTS_GOLD)
                .define('/', ItemInit.NETHERQUARTZ_SHIELD.get())
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(ItemInit.NETHERQUARTZ_SHIELD.get()))
                .save(output, "gilded_netherquartz_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GILDED_IRON_SHIELD.get())
                .define('#', Tags.Items.INGOTS_GOLD)
                .define('/', ItemInit.IRON_SHIELD.get())
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(ItemInit.IRON_SHIELD.get()))
                .save(output, "gilded_iron_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GILDED_ENDER_SHIELD.get())
                .define('#', Tags.Items.INGOTS_GOLD)
                .define('/', ItemInit.ENDER_SHIELD.get())
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(ItemInit.ENDER_SHIELD.get()))
                .save(output, "gilded_ender_shield");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemInit.GILDED_DIAMOND_SHIELD.get())
                .define('#', Tags.Items.INGOTS_GOLD)
                .define('/', ItemInit.DIAMOND_SHIELD.get())
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .unlockedBy("has_item", has(ItemInit.DIAMOND_SHIELD.get()))
                .save(output, "gilded_diamond_shield");
    }
}
