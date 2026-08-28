package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final TagKey<Item> C_GEMS_QUARTZ =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gems/quartz"));
    private static final TagKey<Item> C_RODS_WOODEN =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/wooden"));
    private static final TagKey<Item> C_RODS_BLAZE =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "rods/blaze"));
    private static final TagKey<Item> C_LEATHERS =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "leathers"));
    private static final TagKey<Item> C_STONES =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "stone"));

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {

        giantSwordCommonTag(recipeOutput, AsgardShieldItems.WOODEN_GIANT_SWORD.get(), ItemTags.PLANKS, C_RODS_WOODEN, ItemTags.PLANKS);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), Items.DIAMOND, C_RODS_WOODEN, Items.DIAMOND);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.IRON_GIANT_SWORD.get(), Items.IRON_INGOT, C_RODS_WOODEN, Items.IRON_INGOT);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.GOLDEN_GIANT_SWORD.get(), Items.GOLD_INGOT, C_RODS_WOODEN, Items.GOLD_INGOT);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.STONE_GIANT_SWORD.get(), C_STONES, C_RODS_WOODEN, C_STONES);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get(), C_GEMS_QUARTZ, C_RODS_BLAZE, C_GEMS_QUARTZ);
        giantSwordCommonTag(recipeOutput, AsgardShieldItems.COPPER_GIANT_SWORD.get(), Items.COPPER_INGOT, C_RODS_WOODEN,  Items.COPPER_INGOT);
        giantSwordEnder(recipeOutput, AsgardShieldItems.ENDER_GIANT_SWORD.get());
        giantSwordSkull(recipeOutput, AsgardShieldItems.SKULL_GIANT_SWORD.get());
        netheriteUpgrades(recipeOutput, AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), AsgardShieldItems.NETHERITE_GIANT_SWORD.get());

        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.DIAMOND_SHIELD.get(), Items.DIAMOND, Items.DIAMOND);
        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.IRON_SHIELD.get(), Items.IRON_INGOT, Items.IRON_INGOT);
        asgardShieldCommonTag(recipeOutput, AsgardShieldItems.STONE_SHIELD.get(), C_STONES, C_STONES);
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
            RecipeOutput out,
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
                .unlockedBy("has_blade", hasW(unlockRepresentative))
                .save(out);
    }

    private void giantSwordCommonTag(
            RecipeOutput out,
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
                .unlockedBy("has_blade", hasW(unlockRepresentative))
                .save(out);
    }

    private void giantSwordEnder(RecipeOutput out, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_RODS_BLAZE)
                .unlockedBy("has_ender_eye", hasW(Items.ENDER_EYE))
                .save(out);
    }

    private void netheriteUpgrades(RecipeOutput out, ItemLike base, ItemLike result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(base),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.COMBAT,
                result.asItem()
        )
                .unlocks("has_template", hasW(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .save(out, result + "_smithing");
    }

    private void giantSwordSkull(RecipeOutput out, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.BONE)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_RODS_WOODEN)
                .unlockedBy("has_skull", hasW(Items.SKELETON_SKULL))
                .save(out);
    }

    private void asgardShieldCommonTag(
            RecipeOutput out,
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
                .unlockedBy("has_plate", hasW(unlockRepresentative))
                .save(out);
    }
    private void asgardShieldCommonTag(
            RecipeOutput out,
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
                .unlockedBy("has_plate", hasW(unlockRepresentative))
                .save(out);
    }

    private void asgardShieldCommonGilded(
            RecipeOutput out,
            ItemLike result,
            ItemLike shield
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', Items.GOLD_INGOT)
                .define('/', shield)
                .unlockedBy("has_plate", hasW(shield))
                .save(out);
    }

    private void asgardShieldEnder(
            RecipeOutput out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_LEATHERS)
                .unlockedBy("has_ender_eye", hasW(Items.ENDER_EYE))
                .save(out);
    }

    private void asgardShieldSkull(
            RecipeOutput out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', C_STONES)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_LEATHERS)
                .unlockedBy("has_skull", hasW(Items.SKELETON_SKULL))
                .save(out);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> hasW(ItemLike item)
    {
        return inventoryTrigger(ItemPredicate.Builder.item().of(item));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> hasW(TagKey<Item> tagKey)
    {
        return inventoryTrigger(ItemPredicate.Builder.item().of(tagKey));
    }

    @Override
    public String getName() {
        return "asr recipes";
    }
}
