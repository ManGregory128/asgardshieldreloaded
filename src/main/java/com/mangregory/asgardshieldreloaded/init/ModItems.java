package com.mangregory.asgardshieldreloaded.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import com.mangregory.asgardshieldreloaded.items.tools.ToolSword;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item.ToolMaterial MATERIAL_NEWWOOD = EnumHelper.addToolMaterial("material_newwood", 0, 88, 2.0F, 2.0F, 15);
    public static final Item.ToolMaterial MATERIAL_NEWSTONE = EnumHelper.addToolMaterial("material_newstone", 1, 196, 4.0F, 3.0F, 5);
    public static final Item.ToolMaterial MATERIAL_NEWIRON = EnumHelper.addToolMaterial("material_newiron", 2, 375, 6.0F, 4.0F, 14);
    public static final Item.ToolMaterial MATERIAL_NEWGOLD = EnumHelper.addToolMaterial("material_newgold", 0, 48, 12.0F, 2.0F, 22);
    public static final Item.ToolMaterial MATERIAL_NEWDIAMOND = EnumHelper.addToolMaterial("material_newdiamond", 3, 2341, 8.0F, 5.0F, 10);
    public static final Item.ToolMaterial MATERIAL_NETHERQUARTZ = EnumHelper.addToolMaterial("material_netherquartz", 2, 375, 6.0F, 4.0F, 14);
    public static final Item.ToolMaterial MATERIAL_SKULL = EnumHelper.addToolMaterial("material_skull", 1, 260, 4.0F, 3.0F, 5);
    public static final Item.ToolMaterial MATERIAL_ENDER = EnumHelper.addToolMaterial("material_ender", 3, 1829, 8.0F, 5.0F, 10);

    public static final ItemSword WOODEN_GIANT_SWORD = new ToolSword("wooden_giant_sword", MATERIAL_NEWWOOD);
    public static final ItemSword STONE_GIANT_SWORD = new ToolSword("stone_giant_sword", MATERIAL_NEWSTONE);
    public static final ItemSword IRON_GIANT_SWORD = new ToolSword("iron_giant_sword", MATERIAL_NEWIRON);
    public static final ItemSword GOLDEN_GIANT_SWORD = new ToolSword("golden_giant_sword", MATERIAL_NEWGOLD);
    public static final ItemSword DIAMOND_GIANT_SWORD = new ToolSword("diamond_giant_sword", MATERIAL_NEWDIAMOND);
    public static final ItemSword NETHERQUARTZ_GIANT_SWORD = new ToolSword("netherquartz_giant_sword", MATERIAL_NETHERQUARTZ);
    public static final ItemSword SKULL_GIANT_SWORD = new ToolSword("skull_giant_sword", MATERIAL_SKULL);
    public static final ItemSword ENDER_GIANT_SWORD = new ToolSword("ender_giant_sword", MATERIAL_ENDER);
}