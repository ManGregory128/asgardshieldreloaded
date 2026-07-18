package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        add(ItemTags.SWORDS, AsgardShieldItems.DIAMOND_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.GOLDEN_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.IRON_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.STONE_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.WOODEN_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.ENDER_GIANT_SWORD.get());
        add(ItemTags.SWORDS, AsgardShieldItems.SKULL_GIANT_SWORD.get());
    }

    private void add(TagKey<Item> tag, ItemLike... suppliers) {
        tag(tag).addAll(Stream.of(suppliers)
                .map(ItemLike::asItem)
                .map(Item::builtInRegistryHolder)
                .map(Holder.Reference::key)
                .toList());
    }
}
