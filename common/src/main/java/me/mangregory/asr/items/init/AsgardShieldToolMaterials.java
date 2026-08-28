package me.mangregory.asr.items.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class AsgardShieldToolMaterials {
    public static final Tier ALTDIAMOND = createTier(
            2600,
            8.0f,
            4.0f,
            15,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            Items.DIAMOND
    );

    public static final Tier ALTIRON = createTier(
            410,
            6.0f,
            3.0f,
            10,
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            Items.IRON_INGOT
    );

    public static final Tier ALTGOLD = createTier(
            60,
            12.0f,
            1.0f,
            22,
            BlockTags.INCORRECT_FOR_GOLD_TOOL,
            Items.GOLD_INGOT
    );

    public static final Tier ALTSTONE = createTier(
            215,
            4.0f,
            2.0f,
            5,
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            Items.COBBLESTONE
    );

    public static final Tier ALTWOOD = createTier(
            88,
            2.0f,
            1.0f,
            7,
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            Items.OAK_PLANKS
    );

    public static final Tier ENDTOOLMATERIAL = createTier(
            3100,
            8.0f,
            4.0f,
            15,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            Items.OBSIDIAN
    );

    public static final Tier NQ_TOOLMATERIAL = createTier(
            480,
            6.0f,
            3.0f,
            13,
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            Items.QUARTZ
    );

    public static final Tier SKULL_TOOLMATERIAL = createTier(
            360,
            5.0f,
            2.0f,
            6,
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            Items.BONE
    );

    public static final Tier ALTCOPPER = createTier(
            300,
            4.0f,
            2.0f,
            10,
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            Items.COPPER_INGOT
    );

    public static final Tier ALTNETHERITE = createTier(
            4000,
            9.0f,
            5.0f,
            18,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            Items.NETHERITE_INGOT
    );

    private static Tier createTier(
            int uses,
            float speed,
            float attackDamage,
            int enchantmentValue,
            TagKey<Block> incorrectBlocks,
            ItemLike repairIngredient
    ) {
        return new Tier() {
            @Override
            public int getUses() {
                return uses;
            }

            @Override
            public float getSpeed() {
                return speed;
            }

            @Override
            public float getAttackDamageBonus() {
                return attackDamage;
            }

            @Override
            public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
                return incorrectBlocks;
            }

            @Override
            public int getEnchantmentValue() {
                return enchantmentValue;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(repairIngredient);
            }
        };
    }
}
