package com.mangregory.asgardshieldreloaded.items;

import net.minecraft.item.ItemStack;

public interface IDyeable
{
    public int getDyedColor(ItemStack stack);

    public void setDyedColor(ItemStack stack, int color);

    public int getDefaultDyedColorForMeta(int meta);
}
