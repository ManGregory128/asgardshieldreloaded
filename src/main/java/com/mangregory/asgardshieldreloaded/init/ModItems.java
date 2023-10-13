package com.mangregory.asgardshieldreloaded.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import com.mangregory.asgardshieldreloaded.config.ASRConfig;
import com.mangregory.asgardshieldreloaded.items.ItemAsgardShield;
import com.mangregory.asgardshieldreloaded.items.ItemGiantSword;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item.ToolMaterial MATERIAL_NEWWOOD = EnumHelper.addToolMaterial("material_newwood", 0, ASRConfig.SWORDS.WOODEN_GIANT_SWORD_DURABILITY, 2.0F, (float) ASRConfig.SWORDS.WOODEN_GIANT_SWORD_DAMAGE - 4, 15).setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.PLANKS)));
    public static final Item.ToolMaterial MATERIAL_NEWSTONE = EnumHelper.addToolMaterial("material_newstone", 1, ASRConfig.SWORDS.STONE_GIANT_SWORD_DURABILITY, 4.0F, (float) ASRConfig.SWORDS.STONE_GIANT_SWORD_DAMAGE - 4, 5).setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.COBBLESTONE)));
    public static final Item.ToolMaterial MATERIAL_NEWIRON = EnumHelper.addToolMaterial("material_newiron", 2, ASRConfig.SWORDS.IRON_GIANT_SWORD_DURABILITY, 6.0F, (float) ASRConfig.SWORDS.IRON_GIANT_SWORD_DAMAGE - 4, 14).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final Item.ToolMaterial MATERIAL_NEWGOLD = EnumHelper.addToolMaterial("material_newgold", 0, ASRConfig.SWORDS.GOLD_GIANT_SWORD_DURABILITY, 12.0F, (float) ASRConfig.SWORDS.GOLD_GIANT_SWORD_DAMAGE - 4, 22).setRepairItem(new ItemStack(Items.GOLD_INGOT));
    public static final Item.ToolMaterial MATERIAL_NEWDIAMOND = EnumHelper.addToolMaterial("material_newdiamond", 3, ASRConfig.SWORDS.DIAMOND_GIANT_SWORD_DURABILITY, 8.0F, (float) ASRConfig.SWORDS.DIAMOND_GIANT_SWORD_DAMAGE - 4, 10).setRepairItem(new ItemStack(Items.DIAMOND));
    public static final Item.ToolMaterial MATERIAL_NETHERQUARTZ = EnumHelper.addToolMaterial("material_netherquartz", 2, ASRConfig.SWORDS.NETHERQUARTZ_GIANT_SWORD_DURABILITY, 6.0F, (float) ASRConfig.SWORDS.NETHERQUARTZ_GIANT_SWORD_DAMAGE - 4, 14).setRepairItem(new ItemStack(Items.QUARTZ));
    public static final Item.ToolMaterial MATERIAL_PATCHWORK = EnumHelper.addToolMaterial("material_patchwork", 0, ASRConfig.SWORDS.PATCHWORK_GIANT_SWORD_DURABILITY, 2.0F, (float) ASRConfig.SWORDS.PATCHWORK_GIANT_SWORD_DAMAGE - 4, 15).setRepairItem(new ItemStack(Items.ROTTEN_FLESH));
    public static final Item.ToolMaterial MATERIAL_SKULL = EnumHelper.addToolMaterial("material_skull", 1, ASRConfig.SWORDS.SKULL_GIANT_SWORD_DURABILITY, 4.0F, (float) ASRConfig.SWORDS.SKULL_GIANT_SWORD_DAMAGE - 4, 5).setRepairItem(new ItemStack(Items.BONE));
    public static final Item.ToolMaterial MATERIAL_ENDER = EnumHelper.addToolMaterial("material_ender", 3, ASRConfig.SWORDS.ENDER_GIANT_SWORD_DURABILITY, 8.0F, (float) ASRConfig.SWORDS.ENDER_GIANT_SWORD_DAMAGE - 4, 10).setRepairItem(new ItemStack(Item.getItemFromBlock(Blocks.OBSIDIAN)));

    public static final ItemSword WOODEN_GIANT_SWORD = new ItemGiantSword("wooden_giant_sword", MATERIAL_NEWWOOD, ASRConfig.SWORDS.WOODEN_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword STONE_GIANT_SWORD = new ItemGiantSword("stone_giant_sword", MATERIAL_NEWSTONE, ASRConfig.SWORDS.STONE_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword IRON_GIANT_SWORD = new ItemGiantSword("iron_giant_sword", MATERIAL_NEWIRON, ASRConfig.SWORDS.IRON_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword GOLDEN_GIANT_SWORD = new ItemGiantSword("golden_giant_sword", MATERIAL_NEWGOLD, ASRConfig.SWORDS.GOLD_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword DIAMOND_GIANT_SWORD = new ItemGiantSword("diamond_giant_sword", MATERIAL_NEWDIAMOND, ASRConfig.SWORDS.DIAMOND_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword NETHERQUARTZ_GIANT_SWORD = new ItemGiantSword("netherquartz_giant_sword", MATERIAL_NETHERQUARTZ, ASRConfig.SWORDS.NETHERQUARTZ_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword PATCHWORK_GIANT_SWORD = new ItemGiantSword("patchwork_giant_sword", MATERIAL_PATCHWORK, ASRConfig.SWORDS.PATCHWORK_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword SKULL_GIANT_SWORD = new ItemGiantSword("skull_giant_sword", MATERIAL_SKULL, ASRConfig.SWORDS.SKULL_GIANT_SWORD_MAXUSEDURATION);
    public static final ItemSword ENDER_GIANT_SWORD = new ItemGiantSword("ender_giant_sword", MATERIAL_ENDER, ASRConfig.SWORDS.ENDER_GIANT_SWORD_MAXUSEDURATION);

    public static final ItemAsgardShield WOODEN_SHIELD = new ItemAsgardShield("wooden_shield", MATERIAL_NEWWOOD, ASRConfig.SHIELDS.WOODEN_SHIELD_DURABILITY, ASRConfig.SHIELDS.WOODEN_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_WOODEN_SHIELD = new ItemAsgardShield("gilded_wooden_shield", MATERIAL_NEWWOOD, ASRConfig.SHIELDS.GILDED_WOODEN_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_WOODEN_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield STONE_SHIELD = new ItemAsgardShield("stone_shield", MATERIAL_NEWSTONE, ASRConfig.SHIELDS.STONE_SHIELD_DURABILITY, ASRConfig.SHIELDS.STONE_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_STONE_SHIELD = new ItemAsgardShield("gilded_stone_shield", MATERIAL_NEWSTONE, ASRConfig.SHIELDS.GILDED_STONE_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_STONE_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield IRON_SHIELD = new ItemAsgardShield("iron_shield", MATERIAL_NEWIRON, ASRConfig.SHIELDS.IRON_SHIELD_DURABILITY, ASRConfig.SHIELDS.IRON_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_IRON_SHIELD = new ItemAsgardShield("gilded_iron_shield", MATERIAL_NEWIRON, ASRConfig.SHIELDS.GILDED_IRON_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_IRON_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GOLDEN_SHIELD = new ItemAsgardShield("golden_shield", MATERIAL_NEWGOLD, ASRConfig.SHIELDS.GOLDEN_SHIELD_DURABILITY, ASRConfig.SHIELDS.GOLDEN_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield BLESSED_GOLDEN_SHIELD = new ItemAsgardShield("blessed_golden_shield", MATERIAL_NEWGOLD, ASRConfig.SHIELDS.BLESSED_GOLDEN_SHIELD_DURABILITY, ASRConfig.SHIELDS.BLESSED_GOLDEN_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield DIAMOND_SHIELD = new ItemAsgardShield("diamond_shield", MATERIAL_NEWDIAMOND, ASRConfig.SHIELDS.DIAMOND_SHIELD_DURABILITY, ASRConfig.SHIELDS.DIAMOND_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_DIAMOND_SHIELD = new ItemAsgardShield("gilded_diamond_shield", MATERIAL_NEWDIAMOND, ASRConfig.SHIELDS.GILDED_DIAMOND_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_DIAMOND_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield NETHERQUARTZ_SHIELD = new ItemAsgardShield("netherquartz_shield", MATERIAL_NETHERQUARTZ, ASRConfig.SHIELDS.NETHERQUARTZ_SHIELD_DURABILITY, ASRConfig.SHIELDS.NETHERQUARTZ_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_NETHERQUARTZ_SHIELD = new ItemAsgardShield("gilded_netherquartz_shield", MATERIAL_NETHERQUARTZ, ASRConfig.SHIELDS.GILDED_NETHERQUARTZ_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_NETHERQUARTZ_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield PATCHWORK_SHIELD = new ItemAsgardShield("patchwork_shield", MATERIAL_PATCHWORK, ASRConfig.SHIELDS.PATCHWORK_SHIELD_DURABILITY, ASRConfig.SHIELDS.PATCHWORK_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_PATCHWORK_SHIELD = new ItemAsgardShield("gilded_patchwork_shield", MATERIAL_PATCHWORK, ASRConfig.SHIELDS.GILDED_PATCHWORK_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_PATCHWORK_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield SKULL_SHIELD = new ItemAsgardShield("skull_shield", MATERIAL_SKULL, ASRConfig.SHIELDS.SKULL_SHIELD_DURABILITY, ASRConfig.SHIELDS.SKULL_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_SKULL_SHIELD = new ItemAsgardShield("gilded_skull_shield", MATERIAL_SKULL, ASRConfig.SHIELDS.GILDED_SKULL_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_SKULL_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield ENDER_SHIELD = new ItemAsgardShield("ender_shield", MATERIAL_ENDER, ASRConfig.SHIELDS.ENDER_SHIELD_DURABILITY, ASRConfig.SHIELDS.ENDER_SHIELD_MAXUSEDURATION);
    public static final ItemAsgardShield GILDED_ENDER_SHIELD = new ItemAsgardShield("gilded_ender_shield", MATERIAL_ENDER, ASRConfig.SHIELDS.GILDED_ENDER_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_ENDER_SHIELD_MAXUSEDURATION);
}