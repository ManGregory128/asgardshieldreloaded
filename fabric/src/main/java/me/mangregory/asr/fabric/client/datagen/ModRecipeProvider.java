package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    // 1.20.1 has no "c:" convention tags on either loader (old Forge branch used "forge:" tags,
    // which don't exist on Fabric) - use our own asr: tags with the vanilla items instead
    private static final TagKey<Item> C_GEMS_QUARTZ =
            TagKey.create(Registries.ITEM, new ResourceLocation("asr", "gems/quartz"));
    private static final TagKey<Item> C_RODS_WOODEN =
            TagKey.create(Registries.ITEM, new ResourceLocation("asr", "rods/wooden"));
    private static final TagKey<Item> C_RODS_BLAZE =
            TagKey.create(Registries.ITEM, new ResourceLocation("asr", "rods/blaze"));
    private static final TagKey<Item> C_LEATHERS =
            TagKey.create(Registries.ITEM, new ResourceLocation("asr", "leathers"));

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {

        giantSwordCommonTag(recipeOutput, AsgardShieldItems.WOODEN_GIANT_SWORD.get(), ItemTags.PLANKS, C_RODS_WOODEN, ItemTags.PLANKS);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), Items.DIAMOND, C_RODS_WOODEN, Items.DIAMOND);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.IRON_GIANT_SWORD.get(), Items.IRON_INGOT, C_RODS_WOODEN, Items.IRON_INGOT);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.GOLDEN_GIANT_SWORD.get(), Items.GOLD_INGOT, C_RODS_WOODEN, Items.GOLD_INGOT);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.STONE_GIANT_SWORD.get(), ItemTags.STONE_TOOL_MATERIALS, C_RODS_WOODEN, ItemTags.STONE_TOOL_MATERIALS);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get(), C_GEMS_QUARTZ, C_RODS_BLAZE, C_GEMS_QUARTZ);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.COPPER_GIANT_SWORD.get(), Items.COPPER_INGOT, C_RODS_WOODEN,  Items.COPPER_INGOT);
        giantSwordEnder(recipeOutput, AsgardShieldItems.ENDER_GIANT_SWORD.get());
        giantSwordSkull(recipeOutput, AsgardShieldItems.SKULL_GIANT_SWORD.get());
        netheriteUpgrades(recipeOutput, AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), AsgardShieldItems.NETHERITE_GIANT_SWORD.get());

        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.DIAMOND_SHIELD.get(), Items.DIAMOND, Items.DIAMOND);
        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.IRON_SHIELD.get(), Items.IRON_INGOT, Items.IRON_INGOT);
        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.STONE_SHIELD.get(), ItemTags.STONE_TOOL_MATERIALS, ItemTags.STONE_TOOL_MATERIALS);
        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.COPPER_SHIELD.get(), Items.COPPER_INGOT, Items.COPPER_INGOT);
        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.WOODEN_SHIELD.get(), ItemTags.PLANKS, ItemTags.PLANKS);
        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), C_GEMS_QUARTZ, C_GEMS_QUARTZ);
        asgardShieldEnder(recipeOutput, AsgardShieldItems.ENDER_SHIELD.get());
        asgardShieldSkull(recipeOutput, AsgardShieldItems.SKULL_SHIELD.get());
        netheriteUpgrades(recipeOutput, AsgardShieldItems.DIAMOND_SHIELD.get(), AsgardShieldItems.NETHERITE_SHIELD.get());

        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_NETHERITE_SHIELD.get(), AsgardShieldItems.NETHERITE_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), AsgardShieldItems.DIAMOND_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_IRON_SHIELD.get(), AsgardShieldItems.IRON_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_STONE_SHIELD.get(), AsgardShieldItems.STONE_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_COPPER_SHIELD.get(), AsgardShieldItems.COPPER_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), AsgardShieldItems.WOODEN_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), AsgardShieldItems.NETHERQUARTZ_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_ENDER_SHIELD.get(), AsgardShieldItems.ENDER_SHIELD.get());
        asgardShieldCommonGilded(recipeOutput, AsgardShieldItems.GILDED_SKULL_SHIELD.get(), AsgardShieldItems.SKULL_SHIELD.get());
    }

    private void giantSwordCommonTag(
            Consumer<FinishedRecipe> out,
            ItemLike result,
            Item bladeItem,
            TagKey<Item> rodTag,
            Item unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .define('#', bladeItem)
                .define('/', rodTag)
                .unlockedBy("has_blade", has(unlockRepresentative))
                .save(out);
    }

    private void giantSwordCommonTag(
            Consumer<FinishedRecipe> out,
            ItemLike result,
            TagKey<Item> bladeTag,
            TagKey<Item> rodTag,
            TagKey<Item> unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .define('#', bladeTag)
                .define('/', rodTag)
                .unlockedBy("has_blade", has(unlockRepresentative))
                .save(out);
    }

    private void giantSwordEnder(Consumer<FinishedRecipe> out, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_RODS_BLAZE)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .save(out);
    }

    private void netheriteUpgrades(Consumer<FinishedRecipe> out, ItemLike base, ItemLike result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(base),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.COMBAT,
                result.asItem()
        )
                .unlocks("has_template", has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .save(out, result + "_smithing");
    }

    private void giantSwordSkull(Consumer<FinishedRecipe> out, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.BONE)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_RODS_WOODEN)
                .unlockedBy("has_skull", has(Items.SKELETON_SKULL))
                .save(out);
    }

    private void asgardShieldCommonTag(
            Consumer<FinishedRecipe> out,
            ItemLike result,
            TagKey<Item> plateTag,
            TagKey<Item> unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', plateTag)
                .define('/', ModRecipeProvider.C_LEATHERS)
                .unlockedBy("has_plate", has(unlockRepresentative))
                .save(out);
    }
    private void asgardShieldCommonTag(
            Consumer<FinishedRecipe> out,
            ItemLike result,
            Item plateTag,
            Item unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', plateTag)
                .define('/', ModRecipeProvider.C_LEATHERS)
                .unlockedBy("has_plate", has(unlockRepresentative))
                .save(out);
    }

    private void asgardShieldCommonGilded(
            Consumer<FinishedRecipe> out,
            ItemLike result,
            ItemLike shield
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', Items.GOLD_INGOT)
                .define('/', shield)
                .unlockedBy("has_plate", has(shield))
                .save(out);
    }

    private void asgardShieldEnder(
            Consumer<FinishedRecipe> out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_LEATHERS)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .save(out);
    }

    private void asgardShieldSkull(
            Consumer<FinishedRecipe> out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', ItemTags.STONE_TOOL_MATERIALS)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_LEATHERS)
                .unlockedBy("has_skull", has(Items.SKELETON_SKULL))
                .save(out);
    }

    @Override
    public String getName() {
        return "asr recipes";
    }
}
