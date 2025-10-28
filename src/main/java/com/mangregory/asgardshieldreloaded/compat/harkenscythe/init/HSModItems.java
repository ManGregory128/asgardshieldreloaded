package com.mangregory.asgardshieldreloaded.compat.harkenscythe.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.mangregory.asgardshieldreloaded.config.ASRConfig;
import com.mangregory.asgardshieldreloaded.items.ItemAsgardShield;
import com.mangregory.asgardshieldreloaded.items.ItemGiantSword;

public class HSModItems
{
    public static Item.ToolMaterial MATERIAL_BIOMASS;
    public static Item.ToolMaterial MATERIAL_LIVINGMETAL;

    public static ItemSword BIOMASS_GIANT_SWORD;
    public static ItemSword LIVINGMETAL_GIANT_SWORD;

    public static ItemAsgardShield BIOMASS_SHIELD;
    public static ItemAsgardShield GILDED_BIOMASS_SHIELD;
    public static ItemAsgardShield LIVINGMETAL_SHIELD;
    public static ItemAsgardShield GILDED_LIVINGMETAL_SHIELD;

    public static void init()
    {
        final ItemStack livingmetalStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("harkenscythe:livingmetal_ingot")));
        final ItemStack biomassStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("harkenscythe:biomass")));

        MATERIAL_BIOMASS = EnumHelper.addToolMaterial("material_biomass", 3, ASRConfig.SWORDS.BIOMASS_GIANT_SWORD_DURABILITY, 6.5F, (float) ASRConfig.SWORDS.BIOMASS_GIANT_SWORD_DAMAGE - 4, 17).setRepairItem(biomassStack);
        MATERIAL_LIVINGMETAL = EnumHelper.addToolMaterial("material_livingmetal", 3, ASRConfig.SWORDS.LIVINGMETAL_GIANT_SWORD_DURABILITY, 7.5F, (float) ASRConfig.SWORDS.LIVINGMETAL_GIANT_SWORD_DAMAGE - 4, 20).setRepairItem(livingmetalStack);

        BIOMASS_GIANT_SWORD = new ItemGiantSword("biomass_giant_sword", MATERIAL_BIOMASS, ASRConfig.SWORDS.BIOMASS_GIANT_SWORD_MAXUSEDURATION);
        LIVINGMETAL_GIANT_SWORD = new ItemGiantSword("livingmetal_giant_sword", MATERIAL_LIVINGMETAL, ASRConfig.SWORDS.LIVINGMETAL_GIANT_SWORD_MAXUSEDURATION);

        BIOMASS_SHIELD = new ItemAsgardShield("biomass_shield", MATERIAL_BIOMASS, ASRConfig.SHIELDS.BIOMASS_SHIELD_DURABILITY, ASRConfig.SHIELDS.BIOMASS_SHIELD_MAXUSEDURATION, 13136249);
        GILDED_BIOMASS_SHIELD = new ItemAsgardShield("gilded_biomass_shield", MATERIAL_BIOMASS, ASRConfig.SHIELDS.GILDED_BIOMASS_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_BIOMASS_SHIELD_MAXUSEDURATION, 13136249);
        LIVINGMETAL_SHIELD = new ItemAsgardShield("livingmetal_shield", MATERIAL_LIVINGMETAL, ASRConfig.SHIELDS.LIVINGMETAL_SHIELD_DURABILITY, ASRConfig.SHIELDS.LIVINGMETAL_SHIELD_MAXUSEDURATION, 12905471);
        GILDED_LIVINGMETAL_SHIELD = new ItemAsgardShield("gilded_livingmetal_shield", MATERIAL_LIVINGMETAL, ASRConfig.SHIELDS.GILDED_LIVINGMETAL_SHIELD_DURABILITY, ASRConfig.SHIELDS.GILDED_LIVINGMETAL_SHIELD_MAXUSEDURATION, 12905471);
    }
}