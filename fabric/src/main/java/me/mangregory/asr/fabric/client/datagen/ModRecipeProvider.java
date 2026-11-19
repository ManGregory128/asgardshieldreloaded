package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
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

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        // 1.21.4: FabricRecipeProvider extends RecipeProvider.Runner; recipes are built in an
        // anonymous RecipeProvider, and shaped()/shapeless() need a HolderGetter<Item>.
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);

                giantSwordCommonTag(items, output, AsgardShieldItems.WOODEN_GIANT_SWORD.get(), ItemTags.PLANKS, C_RODS_WOODEN, ItemTags.PLANKS);
                giantSwordCommonTag(items, output, AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), Items.DIAMOND, C_RODS_WOODEN, Items.DIAMOND);
                giantSwordCommonTag(items, output, AsgardShieldItems.IRON_GIANT_SWORD.get(), Items.IRON_INGOT, C_RODS_WOODEN, Items.IRON_INGOT);
                giantSwordCommonTag(items, output, AsgardShieldItems.GOLDEN_GIANT_SWORD.get(), Items.GOLD_INGOT, C_RODS_WOODEN, Items.GOLD_INGOT);
                giantSwordCommonTag(items, output, AsgardShieldItems.STONE_GIANT_SWORD.get(), ItemTags.STONE_TOOL_MATERIALS, C_RODS_WOODEN, ItemTags.STONE_TOOL_MATERIALS);
                giantSwordCommonTag(items, output, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get(), C_GEMS_QUARTZ, C_RODS_BLAZE, C_GEMS_QUARTZ);
                giantSwordCommonTag(items, output, AsgardShieldItems.COPPER_GIANT_SWORD.get(), Items.COPPER_INGOT, C_RODS_WOODEN,  Items.COPPER_INGOT);
                giantSwordEnder(items, output, AsgardShieldItems.ENDER_GIANT_SWORD.get());
                giantSwordSkull(items, output, AsgardShieldItems.SKULL_GIANT_SWORD.get());
                netheriteUpgrades(items, output, AsgardShieldItems.DIAMOND_GIANT_SWORD.get(), AsgardShieldItems.NETHERITE_GIANT_SWORD.get());

                asgardShieldCommonTag(items, output, AsgardShieldItems.DIAMOND_SHIELD.get(), Items.DIAMOND, Items.DIAMOND);
                asgardShieldCommonTag(items, output, AsgardShieldItems.IRON_SHIELD.get(), Items.IRON_INGOT, Items.IRON_INGOT);
                asgardShieldCommonTag(items, output, AsgardShieldItems.STONE_SHIELD.get(), ItemTags.STONE_TOOL_MATERIALS, ItemTags.STONE_TOOL_MATERIALS);
                asgardShieldCommonTag(items, output, AsgardShieldItems.COPPER_SHIELD.get(), Items.COPPER_INGOT, Items.COPPER_INGOT);
                asgardShieldCommonTag(items, output, AsgardShieldItems.WOODEN_SHIELD.get(), ItemTags.PLANKS, ItemTags.PLANKS);
                asgardShieldCommonTag(items, output, AsgardShieldItems.NETHERQUARTZ_SHIELD.get(), C_GEMS_QUARTZ, C_GEMS_QUARTZ);
                asgardShieldEnder(items, output, AsgardShieldItems.ENDER_SHIELD.get());
                asgardShieldSkull(items, output, AsgardShieldItems.SKULL_SHIELD.get());
                netheriteUpgrades(items, output, AsgardShieldItems.DIAMOND_SHIELD.get(), AsgardShieldItems.NETHERITE_SHIELD.get());

                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_NETHERITE_SHIELD.get(), AsgardShieldItems.NETHERITE_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_DIAMOND_SHIELD.get(), AsgardShieldItems.DIAMOND_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_IRON_SHIELD.get(), AsgardShieldItems.IRON_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_STONE_SHIELD.get(), AsgardShieldItems.STONE_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_COPPER_SHIELD.get(), AsgardShieldItems.COPPER_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_WOODEN_SHIELD.get(), AsgardShieldItems.WOODEN_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_NETHERQUARTZ_SHIELD.get(), AsgardShieldItems.NETHERQUARTZ_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_ENDER_SHIELD.get(), AsgardShieldItems.ENDER_SHIELD.get());
                asgardShieldCommonGilded(items, output, AsgardShieldItems.GILDED_SKULL_SHIELD.get(), AsgardShieldItems.SKULL_SHIELD.get());
            }
        };
    }

    private static void giantSwordCommonTag(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            Item bladeItem,
            TagKey<Item> rodTag,
            Item unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .define('#', bladeItem)
                .define('/', rodTag)
                .unlockedBy("has_blade", hasItems(items, unlockRepresentative))
                .save(out);
    }

    private static void giantSwordCommonTag(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            TagKey<Item> bladeTag,
            TagKey<Item> rodTag,
            TagKey<Item> unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("###")
                .pattern("/# ")
                .define('#', bladeTag)
                .define('/', rodTag)
                .unlockedBy("has_blade", hasItems(items, unlockRepresentative))
                .save(out);
    }

    private static void giantSwordEnder(HolderGetter<Item> items, RecipeOutput out, ItemLike result) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_RODS_BLAZE)
                .unlockedBy("has_ender_eye", hasItems(items, Items.ENDER_EYE))
                .save(out);
    }

    private static void netheriteUpgrades(HolderGetter<Item> items, RecipeOutput out, ItemLike base, ItemLike result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(base),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.COMBAT,
                result.asItem()
        )
                .unlocks("has_template", hasItems(items, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .save(out, result + "_smithing");
    }

    private static void giantSwordSkull(HolderGetter<Item> items, RecipeOutput out, ItemLike result) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern(" ##")
                .pattern("#*#")
                .pattern("/# ")
                .define('#', Items.BONE)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_RODS_WOODEN)
                .unlockedBy("has_skull", hasItems(items, Items.SKELETON_SKULL))
                .save(out);
    }

    private static void asgardShieldCommonTag(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            TagKey<Item> plateTag,
            TagKey<Item> unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', plateTag)
                .define('/', ModRecipeProvider.C_LEATHERS)
                .unlockedBy("has_plate", hasItems(items, unlockRepresentative))
                .save(out);
    }
    private static void asgardShieldCommonTag(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            Item plateTag,
            Item unlockRepresentative
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', plateTag)
                .define('/', ModRecipeProvider.C_LEATHERS)
                .unlockedBy("has_plate", hasItems(items, unlockRepresentative))
                .save(out);
    }

    private static void asgardShieldCommonGilded(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result,
            ItemLike shield
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern("###")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', Items.GOLD_INGOT)
                .define('/', shield)
                .unlockedBy("has_plate", hasItems(items, shield))
                .save(out);
    }

    private static void asgardShieldEnder(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', Items.OBSIDIAN)
                .define('*', Items.ENDER_EYE)
                .define('/', C_LEATHERS)
                .unlockedBy("has_ender_eye", hasItems(items, Items.ENDER_EYE))
                .save(out);
    }

    private static void asgardShieldSkull(
            HolderGetter<Item> items,
            RecipeOutput out,
            ItemLike result
    ) {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, result)
                .pattern("#*#")
                .pattern("#/#")
                .pattern(" # ")
                .define('#', ItemTags.STONE_TOOL_MATERIALS)
                .define('*', Items.SKELETON_SKULL)
                .define('/', C_LEATHERS)
                .unlockedBy("has_skull", hasItems(items, Items.SKELETON_SKULL))
                .save(out);
    }

    // 1.21.4: RecipeProvider.inventoryTrigger is private and ItemPredicate.Builder.of needs a
    // HolderGetter; use the public TriggerInstance.hasItems(ItemPredicate...) instead.
    private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItems(HolderGetter<Item> items, ItemLike item)
    {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, item).build());
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItems(HolderGetter<Item> items, TagKey<Item> tagKey)
    {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, tagKey).build());
    }

    @Override
    public String getName() {
        return "asr recipes";
    }
}
