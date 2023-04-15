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

    public static final Item.ToolMaterial MATERIAL_NEWWOOD = EnumHelper.addToolMaterial("material_newwood", 0, ASRConfig.SWORDS.WOODEN_GIANT_SWORD_DURABILITY, 2.0F, (float) ASRConfig.SWORDS.WOODEN_GIANT_SWORD_DAMAGE - 4, 15);
    public static final Item.ToolMaterial MATERIAL_NEWSTONE = EnumHelper.addToolMaterial("material_newstone", 1, ASRConfig.SWORDS.STONE_GIANT_SWORD_DURABILITY, 4.0F, (float) ASRConfig.SWORDS.STONE_GIANT_SWORD_DAMAGE - 4, 5);
    public static final Item.ToolMaterial MATERIAL_NEWIRON = EnumHelper.addToolMaterial("material_newiron", 2, ASRConfig.SWORDS.IRON_GIANT_SWORD_DURABILITY, 6.0F, (float) ASRConfig.SWORDS.IRON_GIANT_SWORD_DAMAGE - 4, 14);
    public static final Item.ToolMaterial MATERIAL_NEWGOLD = EnumHelper.addToolMaterial("material_newgold", 0, ASRConfig.SWORDS.GOLD_GIANT_SWORD_DURABILITY, 12.0F, (float) ASRConfig.SWORDS.GOLD_GIANT_SWORD_DAMAGE - 4, 22);
    public static final Item.ToolMaterial MATERIAL_NEWDIAMOND = EnumHelper.addToolMaterial("material_newdiamond", 3, ASRConfig.SWORDS.DIAMOND_GIANT_SWORD_DURABILITY, 8.0F, (float) ASRConfig.SWORDS.DIAMOND_GIANT_SWORD_DAMAGE - 4, 10);
    public static final Item.ToolMaterial MATERIAL_NETHERQUARTZ = EnumHelper.addToolMaterial("material_netherquartz", 2, ASRConfig.SWORDS.NETHERQUARTZ_GIANT_SWORD_DURABILITY, 6.0F, (float) ASRConfig.SWORDS.NETHERQUARTZ_GIANT_SWORD_DAMAGE - 4, 14);
    public static final Item.ToolMaterial MATERIAL_SKULL = EnumHelper.addToolMaterial("material_skull", 1, ASRConfig.SWORDS.SKULL_GIANT_SWORD_DURABILITY, 4.0F, (float) ASRConfig.SWORDS.SKULL_GIANT_SWORD_DAMAGE - 4, 5);
    public static final Item.ToolMaterial MATERIAL_ENDER = EnumHelper.addToolMaterial("material_ender", 3, ASRConfig.SWORDS.ENDER_GIANT_SWORD_DURABILITY, 8.0F, (float) ASRConfig.SWORDS.ENDER_GIANT_SWORD_DAMAGE - 4, 10);

    public static final ItemSword WOODEN_GIANT_SWORD = new ItemGiantSword("wooden_giant_sword", MATERIAL_NEWWOOD, ASRConfig.SWORDS.WOODEN_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword STONE_GIANT_SWORD = new ItemGiantSword("stone_giant_sword", MATERIAL_NEWSTONE, ASRConfig.SWORDS.STONE_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword IRON_GIANT_SWORD = new ItemGiantSword("iron_giant_sword", MATERIAL_NEWIRON, ASRConfig.SWORDS.IRON_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword GOLDEN_GIANT_SWORD = new ItemGiantSword("golden_giant_sword", MATERIAL_NEWGOLD, ASRConfig.SWORDS.GOLD_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword DIAMOND_GIANT_SWORD = new ItemGiantSword("diamond_giant_sword", MATERIAL_NEWDIAMOND, ASRConfig.SWORDS.DIAMOND_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword NETHERQUARTZ_GIANT_SWORD = new ItemGiantSword("netherquartz_giant_sword", MATERIAL_NETHERQUARTZ, ASRConfig.SWORDS.NETHERQUARTZ_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword SKULL_GIANT_SWORD = new ItemGiantSword("skull_giant_sword", MATERIAL_SKULL, ASRConfig.SWORDS.SKULL_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword ENDER_GIANT_SWORD = new ItemGiantSword("ender_giant_sword", MATERIAL_ENDER, ASRConfig.SWORDS.ENDER_GIANT_SWORD_MAXUSEDURATION);

    public static final ItemAsgardShield WOODEN_SHIELD = new ItemAsgardShield("wooden_shield", ASRConfig.SHIELDS.WOODEN_SHIELD_DURABILITY, ASRConfig.SHIELDS.WOODEN_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_WOODEN_SHIELD = new ItemAsgardShield("gilded_wooden_shield", ASRConfig.SHIELDS.GILDED_WOODEN_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_WOODEN_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield STONE_SHIELD = new ItemAsgardShield("stone_shield", ASRConfig.SHIELDS.STONE_SHIELD_DURABILITY, ASRConfig.SHIELDS.STONE_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_STONE_SHIELD = new ItemAsgardShield("gilded_stone_shield", ASRConfig.SHIELDS.GILDED_STONE_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_STONE_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield IRON_SHIELD = new ItemAsgardShield("iron_shield", ASRConfig.SHIELDS.IRON_SHIELD_DURABILITY, ASRConfig.SHIELDS.IRON_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_IRON_SHIELD = new ItemAsgardShield("gilded_iron_shield", ASRConfig.SHIELDS.GILDED_IRON_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_IRON_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield DIAMOND_SHIELD = new ItemAsgardShield("diamond_shield", ASRConfig.SHIELDS.DIAMOND_SHIELD_DURABILITY, ASRConfig.SHIELDS.DIAMOND_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_DIAMOND_SHIELD = new ItemAsgardShield("gilded_diamond_shield", ASRConfig.SHIELDS.GILDED_DIAMOND_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_DIAMOND_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield NETHERQUARTZ_SHIELD = new ItemAsgardShield("netherquartz_shield", ASRConfig.SHIELDS.NETHERQUARTZ_SHIELD_DURABILITY, ASRConfig.SHIELDS.NETHERQUARTZ_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_NETHERQUARTZ_SHIELD = new ItemAsgardShield("gilded_netherquartz_shield", ASRConfig.SHIELDS.GILDED_NETHERQUARTZ_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_NETHERQUARTZ_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield SKULL_SHIELD = new ItemAsgardShield("skull_shield", ASRConfig.SHIELDS.SKULL_SHIELD_DURABILITY, ASRConfig.SHIELDS.SKULL_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_SKULL_SHIELD = new ItemAsgardShield("gilded_skull_shield", ASRConfig.SHIELDS.GILDED_SKULL_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_SKULL_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield ENDER_SHIELD = new ItemAsgardShield("ender_shield", ASRConfig.SHIELDS.ENDER_SHIELD_DURABILITY, ASRConfig.SHIELDS.ENDER_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_ENDER_SHIELD = new ItemAsgardShield("gilded_ender_shield", ASRConfig.SHIELDS.GILDED_ENDER_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_ENDER_SHIELD_MAXUSEDURATION);
}