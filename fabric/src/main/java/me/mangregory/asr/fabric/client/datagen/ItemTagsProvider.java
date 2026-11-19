package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ItemTagsProvider extends FabricTagProvider.ItemTagProvider {

    private static final TagKey<Item> ENCHANTABLE_DURABILITY = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("minecraft", "enchantable/durability")
    );
    // repair-item tags for the custom ToolMaterials (1.21.2+ ToolMaterial takes a TagKey<Item>)
    private static final TagKey<Item> REPAIR_COPPER = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "ingots/copper"));
    private static final TagKey<Item> REPAIR_QUARTZ = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "gems/quartz"));
    private static final TagKey<Item> REPAIR_OBSIDIAN = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "obsidian"));
    private static final TagKey<Item> REPAIR_BONE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "bones"));

    public ItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        add(ItemTags.SWORDS, AsgardShieldItems.DIAMOND_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.GOLDEN_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.IRON_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.STONE_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.WOODEN_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.ENDER_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.SKULL_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.NETHERITE_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.COPPER_GIANT_SWORD.get());

        add(REPAIR_COPPER, Items.COPPER_INGOT);
        add(REPAIR_QUARTZ, Items.QUARTZ);
        add(REPAIR_OBSIDIAN, Items.OBSIDIAN);
        add(REPAIR_BONE, Items.BONE);
        add(ENCHANTABLE_DURABILITY, AsgardShieldItems.ASGARD_SHIELDS.stream()
                .map(Supplier::get)
                .toArray(Item[]::new));
    }

    private void add(TagKey<Item> tag, ItemLike... suppliers) {
        getOrCreateTagBuilder(tag).add(Stream.of(suppliers)
                .map(ItemLike::asItem)
                .toArray(Item[]::new));
    }
}
