package me.mangregory.asr.items.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class AsgardShieldToolMaterials {
    // 1.21.2+ replaced Tier with the ToolMaterial record; repair items are item tags now
    public static final TagKey<Item> REPAIR_COPPER = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "ingots/copper"));
    public static final TagKey<Item> REPAIR_QUARTZ = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "gems/quartz"));
    public static final TagKey<Item> REPAIR_OBSIDIAN = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "obsidian"));
    public static final TagKey<Item> REPAIR_BONE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("asr", "bones"));

    public static final ToolMaterial ALTDIAMOND = createMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2600, 8.0F, 4.0F, 15, ItemTags.DIAMOND_TOOL_MATERIALS);
    public static final ToolMaterial ALTIRON = createMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 410, 6.0F, 3.0F, 10, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial ALTGOLD = createMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 60, 12.0F, 1.0F, 22, ItemTags.GOLD_TOOL_MATERIALS);
    public static final ToolMaterial ALTSTONE = createMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 215, 4.0F, 2.0F, 5, ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial ALTWOOD = createMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 88, 2.0F, 1.0F, 7, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial ENDTOOLMATERIAL = createMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3100, 8.0F, 4.0F, 15, REPAIR_OBSIDIAN);
    public static final ToolMaterial NQ_TOOLMATERIAL = createMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 480, 6.0F, 3.0F, 13, REPAIR_QUARTZ);
    public static final ToolMaterial SKULL_TOOLMATERIAL = createMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 360, 5.0F, 2.0F, 6, REPAIR_BONE);
    public static final ToolMaterial ALTCOPPER = createMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 300, 4.0F, 2.0F, 10, REPAIR_COPPER);
    public static final ToolMaterial ALTNETHERITE = createMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4000, 9.0F, 5.0F, 18, ItemTags.NETHERITE_TOOL_MATERIALS);

    private static ToolMaterial createMaterial(
            TagKey<Block> incorrectBlocks,
            int durability,
            float speed,
            float attackDamageBonus,
            int enchantmentValue,
            TagKey<Item> repairItems
    ) {
        return new ToolMaterial(incorrectBlocks, durability, speed, attackDamageBonus, enchantmentValue, repairItems);
    }
}
