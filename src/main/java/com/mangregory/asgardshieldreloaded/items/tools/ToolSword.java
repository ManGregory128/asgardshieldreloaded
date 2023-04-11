package com.mangregory.asgardshieldreloaded.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

import com.mangregory.asgardshieldreloaded.init.ModItems;

public class ToolSword extends ItemSword
{
    public ToolSword(String name, Item.ToolMaterial material)
    {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        ModItems.ITEMS.add(this);
    }
}