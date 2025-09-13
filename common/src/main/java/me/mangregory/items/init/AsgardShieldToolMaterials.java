package me.mangregory.items.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class AsgardShieldToolMaterials {
    public static final ToolMaterial ALTDIAMOND = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            3100,
            -2.4f,
            4f,
            15,
            ItemTags.DIAMOND_TOOL_MATERIALS
    );
    public static final ToolMaterial ALTIRON = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            410,
            6f,
            3f,
            10,
            ItemTags.IRON_TOOL_MATERIALS
    );
    public static final ToolMaterial ALTGOLD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            60,
            12.0f,
            1f,
            22,
            ItemTags.GOLD_TOOL_MATERIALS
    );
    public static final ToolMaterial ALTSTONE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            215,
            4f,
            2f,
            5,
            ItemTags.STONE_TOOL_MATERIALS
    );
    public static final ToolMaterial ALTWOOD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            88,
            2f,
            1f,
            7,
            ItemTags.WOODEN_TOOL_MATERIALS
    );
    public static final ToolMaterial ENDTOOLMATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            4200,
            -2.4f,
            4f,
            20,
            ItemTags.NETHERITE_TOOL_MATERIALS
    );
}
