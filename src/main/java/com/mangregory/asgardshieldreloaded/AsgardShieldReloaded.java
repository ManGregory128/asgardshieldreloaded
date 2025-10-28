package com.mangregory.asgardshieldreloaded;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mangregory.asgardshieldreloaded.compat.CompatHandler;
import com.mangregory.asgardshieldreloaded.compat.harkenscythe.init.HSModItems;
import com.mangregory.asgardshieldreloaded.init.ModItems;
import com.mangregory.asgardshieldreloaded.items.IDyeable;

@Mod(modid = AsgardShieldReloaded.MOD_ID, name = AsgardShieldReloaded.NAME, version = AsgardShieldReloaded.VERSION, acceptedMinecraftVersions = AsgardShieldReloaded.ACCEPTED_VERSIONS, dependencies = AsgardShieldReloaded.DEPENDENCIES)
public class AsgardShieldReloaded
{
    public static final String MOD_ID = "asr";
    public static final String NAMESPACE = MOD_ID + ":";
    public static final String NAME = "Asgard Shield Reloaded";
    public static final String VERSION = "2.2.1";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String DEPENDENCIES = "after:harkenscythe";

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event)
    {
        CompatHandler.init();
    }

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void initClient(FMLInitializationEvent event)
    {
        IItemColor itemColorHandler = (stack, tintIndex) ->
        {
            if (tintIndex == 1 && stack.getItem() instanceof IDyeable)
            {
                return ((IDyeable) stack.getItem()).getDyedColor(stack);
            } else
            {
                return -1;
            }
        };
        
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.BLESSED_GOLDEN_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.DIAMOND_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.ENDER_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_DIAMOND_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_ENDER_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_IRON_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_NETHERQUARTZ_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_PATCHWORK_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_SKULL_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_STONE_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GILDED_WOODEN_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.GOLDEN_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.IRON_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.NETHERQUARTZ_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.PATCHWORK_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.SKULL_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.STONE_SHIELD);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, ModItems.WOODEN_SHIELD);

        if (Loader.isModLoaded("harkenscythe")) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, HSModItems.BIOMASS_SHIELD);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, HSModItems.GILDED_BIOMASS_SHIELD);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, HSModItems.GILDED_LIVINGMETAL_SHIELD);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemColorHandler, HSModItems.LIVINGMETAL_SHIELD);
        }
    }
}