package me.mangregory.asr.fabric.client.datagen;

import me.mangregory.asr.items.init.AsgardShieldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ItemTagsProvider extends FabricTagProvider.ItemTagProvider {

    // 1.19.2 vanilla has no ItemTags fields for these; declare the keys directly
    private static final TagKey<Item> SWORDS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("minecraft", "swords"));
    private static final TagKey<Item> ENCHANTABLE_DURABILITY = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("minecraft", "enchantable/durability"));

    // vanilla items for our recipe tags (1.20.1 has no c:/forge: tags on Fabric)
    private static final TagKey<Item> GEMS_QUARTZ = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("asr", "gems/quartz"));
    private static final TagKey<Item> RODS_WOODEN = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("asr", "rods/wooden"));
    private static final TagKey<Item> RODS_BLAZE = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("asr", "rods/blaze"));
    private static final TagKey<Item> LEATHERS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("asr", "leathers"));

    public ItemTagsProvider(FabricDataGenerator output) {
        super(output);
    }

    @Override
    protected void generateTags() {
        add(SWORDS, AsgardShieldItems.DIAMOND_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.GOLDEN_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.IRON_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.STONE_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.WOODEN_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.NETHERQUARTZ_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.ENDER_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.SKULL_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.NETHERITE_GIANT_SWORD.get());
        add(SWORDS, AsgardShieldItems.COPPER_GIANT_SWORD.get());

        add(ENCHANTABLE_DURABILITY, AsgardShieldItems.ASGARD_SHIELDS.stream()
                .map(Supplier::get)
                .toArray(Item[]::new));

        add(GEMS_QUARTZ, net.minecraft.world.item.Items.QUARTZ);
        add(RODS_WOODEN, net.minecraft.world.item.Items.STICK);
        add(RODS_BLAZE, net.minecraft.world.item.Items.BLAZE_ROD);
        add(LEATHERS, net.minecraft.world.item.Items.LEATHER);
    }

    private void add(TagKey<Item> tag, ItemLike... suppliers) {
        getOrCreateTagBuilder(tag).add(Stream.of(suppliers)
                .map(ItemLike::asItem)
                .toArray(Item[]::new));
    }
}
