package com.mangregory.asgardshieldreloaded.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import com.mangregory.asgardshieldreloaded.config.ASRConfig;
import com.mangregory.asgardshieldreloaded.items.ItemAsgardShield;
import com.mangregory.asgardshieldreloaded.items.ItemGiantSword;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item.ToolMaterial MATERIAL_NEWWOOD = EnumHelper.addToolMaterial("material_newwood", 0, ASRConfig.WOODEN_GIANT_SWORD_DURABILITY, 2.0F, (float) ASRConfig.WOODEN_GIANT_SWORD_DAMAGE - 4, 15);
    public static final Item.ToolMaterial MATERIAL_NEWSTONE = EnumHelper.addToolMaterial("material_newstone", 1, ASRConfig.STONE_GIANT_SWORD_DURABILITY, 4.0F, (float) ASRConfig.STONE_GIANT_SWORD_DAMAGE - 4, 5);
    public static final Item.ToolMaterial MATERIAL_NEWIRON = EnumHelper.addToolMaterial("material_newiron", 2, ASRConfig.IRON_GIANT_SWORD_DURABILITY, 6.0F, (float) ASRConfig.IRON_GIANT_SWORD_DAMAGE - 4, 14);
    public static final Item.ToolMaterial MATERIAL_NEWGOLD = EnumHelper.addToolMaterial("material_newgold", 0, ASRConfig.GOLD_GIANT_SWORD_DURABILITY, 12.0F, (float) ASRConfig.GOLD_GIANT_SWORD_DAMAGE - 4, 22);
    public static final Item.ToolMaterial MATERIAL_NEWDIAMOND = EnumHelper.addToolMaterial("material_newdiamond", 3, ASRConfig.DIAMOND_GIANT_SWORD_DURABILITY, 8.0F, (float) ASRConfig.DIAMOND_GIANT_SWORD_DAMAGE - 4, 10);
    public static final Item.ToolMaterial MATERIAL_NETHERQUARTZ = EnumHelper.addToolMaterial("material_netherquartz", 2, ASRConfig.NETHERQUARTZ_GIANT_SWORD_DURABILITY, 6.0F, (float) ASRConfig.NETHERQUARTZ_GIANT_SWORD_DAMAGE - 4, 14);
    public static final Item.ToolMaterial MATERIAL_SKULL = EnumHelper.addToolMaterial("material_skull", 1, ASRConfig.SKULL_GIANT_SWORD_DURABILITY, 4.0F, (float) ASRConfig.SKULL_GIANT_SWORD_DAMAGE - 4, 5);
    public static final Item.ToolMaterial MATERIAL_ENDER = EnumHelper.addToolMaterial("material_ender", 3, ASRConfig.ENDER_GIANT_SWORD_DURABILITY, 8.0F, (float) ASRConfig.ENDER_GIANT_SWORD_DAMAGE - 4, 10);

    public static final ItemSword WOODEN_GIANT_SWORD = new ItemGiantSword("wooden_giant_sword", MATERIAL_NEWWOOD, ASRConfig.WOODEN_GIANT_SWORD_COOLDOWN, ASRConfig.WOODEN_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword STONE_GIANT_SWORD = new ItemGiantSword("stone_giant_sword", MATERIAL_NEWSTONE, ASRConfig.STONE_GIANT_SWORD_COOLDOWN, ASRConfig.STONE_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword IRON_GIANT_SWORD = new ItemGiantSword("iron_giant_sword", MATERIAL_NEWIRON, ASRConfig.IRON_GIANT_SWORD_COOLDOWN, ASRConfig.IRON_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword GOLDEN_GIANT_SWORD = new ItemGiantSword("golden_giant_sword", MATERIAL_NEWGOLD, ASRConfig.GOLD_GIANT_SWORD_COOLDOWN, ASRConfig.GOLD_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword DIAMOND_GIANT_SWORD = new ItemGiantSword("diamond_giant_sword", MATERIAL_NEWDIAMOND, ASRConfig.DIAMOND_GIANT_SWORD_COOLDOWN, ASRConfig.DIAMOND_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword NETHERQUARTZ_GIANT_SWORD = new ItemGiantSword("netherquartz_giant_sword", MATERIAL_NETHERQUARTZ, ASRConfig.NETHERQUARTZ_GIANT_SWORD_COOLDOWN, ASRConfig.NETHERQUARTZ_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword SKULL_GIANT_SWORD = new ItemGiantSword("skull_giant_sword", MATERIAL_SKULL, ASRConfig.SKULL_GIANT_SWORD_COOLDOWN, ASRConfig.SKULL_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword ENDER_GIANT_SWORD = new ItemGiantSword("ender_giant_sword", MATERIAL_ENDER, ASRConfig.ENDER_GIANT_SWORD_COOLDOWN, ASRConfig.ENDER_GIANT_SWORD_MAXUSEDURATION);

    public static final ItemAsgardShield WOODEN_SHIELD = new ItemAsgardShield("wooden_shield", 118, 200);
    public static final ItemAsgardShield GILDED_WOODEN_SHIELD = new ItemAsgardShield("gilded_wooden_shield", 246, 200);
    public static final ItemAsgardShield STONE_SHIELD = new ItemAsgardShield("stone_shield", 131, 200);
    public static final ItemAsgardShield GILDED_STONE_SHIELD = new ItemAsgardShield("gilded_stone_shield", 259, 200);
    public static final ItemAsgardShield IRON_SHIELD = new ItemAsgardShield("iron_shield", 250, 200);
    public static final ItemAsgardShield GILDED_IRON_SHIELD = new ItemAsgardShield("gilded_iron_shield", 378, 200);
    public static final ItemAsgardShield DIAMOND_SHIELD = new ItemAsgardShield("diamond_shield", 780, 200);
    public static final ItemAsgardShield GILDED_DIAMOND_SHIELD = new ItemAsgardShield("gilded_diamond_shield", 908, 200);
    public static final ItemAsgardShield NETHERQUARTZ_SHIELD = new ItemAsgardShield("netherquartz_shield", 369, 200);
    public static final ItemAsgardShield GILDED_NETHERQUARTZ_SHIELD = new ItemAsgardShield("gilded_netherquartz_shield", 497, 200);
    public static final ItemAsgardShield SKULL_SHIELD = new ItemAsgardShield("skull_shield", 174, 200);
    public static final ItemAsgardShield GILDED_SKULL_SHIELD = new ItemAsgardShield("gilded_skull_shield", 302, 200);
    public static final ItemAsgardShield ENDER_SHIELD = new ItemAsgardShield("ender_shield", 435, 200);
    public static final ItemAsgardShield GILDED_ENDER_SHIELD = new ItemAsgardShield("gilded_ender_shield", 563, 200);
}