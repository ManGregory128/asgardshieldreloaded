package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeProvider.inventoryTrigger;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final TagKey<Item> C_GEMS_QUARTZ =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "gems/quartz"));
    private static final TagKey<Item> C_RODS_WOODEN =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "rods/wooden"));
    private static final TagKey<Item> C_RODS_BLAZE =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "rods/blaze"));
    private static final TagKey<Item> C_LEATHERS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "leathers"));

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput out) {
        return new RecipeProvider(provider, out) {
            @Override
            public void buildRecipes() {
                HolderGetter<Item> items = this.registries.lookupOrThrow(Registries.ITEM);

                giantSwordCommonTag(items, out, AsgardShieldItems.WOODEN_GIANT_SWORD.get(), ItemTags.WOODEN_TOOL_MATERIALS, C_RODS_WOODEN, ItemTags.WOODEN_TOOL_MATERIALS);
                giantSwordCommonTag(items, out, AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), ItemTags.DIAMOND_TOOL_MATERIALS, C_RODS_WOODEN, ItemTags.DIAMOND_TOOL_MATERIALS);
                giantSwordCommonTag(items, out, AsgardShieldItems.IRON_GIANT_SWORD.get(), ItemTags.IRON_TOOL_MATERIALS, C_RODS_WOODEN, ItemTags.IRON_TOOL_MATERIALS);
                giantSwordCommonTag(items, out, AsgardShieldItems.GOLDEN_GIANT_SWORD.get(), ItemTags.GOLD_TOOL_MATERIALS, C_RODS_WOODEN, ItemTags.GOLD_TOOL_MATERIALS);
                giantSwordCommonTag(items, out, AsgardShieldItems.STONE_GIANT_SWORD.get(), ItemTags.STONE_TOOL_MATERIALS, C_RODS_WOODEN, ItemTags.STONE_TOOL_MATERIALS);
                giantSwordCommonTag(items, out, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get(), C_GEMS_QUARTZ, C_RODS_BLAZE, C_GEMS_QUARTZ);
                giantSwordEnder(items, out, AsgardShieldItems.ENDER_GIANT_SWORD.get());
                giantSwordSkull(items, out, AsgardShieldItems.SKULL_GIANT_SWORD.get());

                asgardShieldCommonTag(items, out, AsgardShieldItems.DIAMOND_SHIELD.get(), ItemTags.DIAMOND_TOOL_MATERIALS, ItemTags.DIAMOND_TOOL_MATERIALS);
                asgardShieldCommonTag(items, out, AsgardShieldItems.IRON_SHIELD.get(), ItemTags.IRON_TOOL_MATERIALS, ItemTags.IRON_TOOL_MATERIALS);
                asgardShieldCommonTag(items, out, AsgardShieldItems.STONE_SHIELD.get(), ItemTags.STONE_TOOL_MATERIALS, ItemTags.STONE_TOOL_MATERIALS);
                asgardShieldCommonTag(items, out, AsgardShieldItems.WOODEN_SHIELD.get(), ItemTags.WOODEN_TOOL_MATERIALS, ItemTags.WOODEN_TOOL_MATERIALS);
                asgardShieldCommonTag(items, out, AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), C_GEMS_QUARTZ, C_GEMS_QUARTZ);
                asgardShieldEnder(items, out, AsgardShieldItems.ENDER_SHIELD.get());
                asgardShieldSkull(items, out, AsgardShieldItems.SKULL_SHIELD.get());

                asgardShieldCommonGilded(items, out, AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), AsgardShieldItems.DIAMOND_SHIELD.get());
                asgardShieldCommonGilded(items, out, AsgardShieldItems.GILDED_IRON_SHIELD.get(), AsgardShieldItems.IRON_SHIELD.get());
                asgardShieldCommonGilded(items, out, AsgardShieldItems.GILDED_STONE_SHIELD.get(), AsgardShieldItems.STONE_SHIELD.get());
                asgardShieldCommonGilded(items, out, AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), AsgardShieldItems.WOODEN_SHIELD.get());
                asgardShieldCommonGilded(items, out, AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), AsgardShieldItems.NETHERQUARTZ_SHIELD.get());
                asgardShieldCommonGilded(items, out, AsgardShieldItems.GILDED_ENDER_SHIELD.get(), AsgardShieldItems.ENDER_SHIELD.get());
                asgardShieldCommonGilded(items, out, AsgardShieldItems.GILDED_SKULL_SHIELD.get(), AsgardShieldItems.SKULL_SHIELD.get());
            }
        };
    }

    private void giantSwordCommonTag(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            TagKey<Item> bladeTag,
            TagKey<Item> rodTag,
            TagKey<Item> unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result, 1)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .define('#', bladeTag)
                .define('/', rodTag)
                .unlockedBy("has_blade", hasW(items, unlockRepresentative))
                .save(out);
    }

    private void giantSwordEnder(HolderGetter<Item> items, RecipeOutput out, ItemLike result) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result, 1)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_RODS_BLAZE)
                .unlockedBy("has_ender_eye", hasW(items, Items.ENDER_EYE))
                .save(out);
    }

    private void giantSwordSkull(HolderGetter<Item> items, RecipeOutput out, ItemLike result) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result, 1)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.BONE)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_RODS_WOODEN)
                .unlockedBy("has_skull", hasW(items, Items.SKELETON_SKULL))
                .save(out);
    }

    private void asgardShieldCommonTag(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            TagKey<Item> plateTag,
            TagKey<Item> unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', plateTag)
                .define('/', ModRecipeProvider.C_LEATHERS)
                .unlockedBy("has_plate", hasW(items, unlockRepresentative))
                .save(out);
    }

    private void asgardShieldCommonGilded(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            ItemLike shield
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', ItemTags.GOLD_TOOL_MATERIALS)
                .define('/', shield)
                .unlockedBy("has_plate", hasW(items, shield))
                .save(out);
    }

    private void asgardShieldEnder(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result, 1)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_LEATHERS)
                .unlockedBy("has_ender_eye", hasW(items, Items.ENDER_EYE))
                .save(out);
    }

    private void asgardShieldSkull(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result, 1)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', ItemTags.STONE_TOOL_MATERIALS)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_LEATHERS)
                .unlockedBy("has_skull", hasW(items, Items.SKELETON_SKULL))
                .save(out);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> hasW(HolderGetter<Item> items, ItemLike item)
    {
        return inventoryTrigger(ItemPredicate.Builder.item().of(items, item));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> hasW(HolderGetter<Item> items, TagKey<Item> tagKey)
    {
        return inventoryTrigger(ItemPredicate.Builder.item().of(items, tagKey));
    }

    @Override
    public @NonNull String getName() {
        return "asr recipes";
    }
}
