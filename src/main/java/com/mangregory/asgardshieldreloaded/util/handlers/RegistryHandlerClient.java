package com.mangregory.asgardshieldreloaded.util.handlers;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.mangregory.asgardshieldreloaded.init.ModItems;

@EventBusSubscriber(value = Side.CLIENT)
public class RegistryHandlerClient
{
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for (Item item : ModItems.ITEMS) registerModel(item);
    }

    private static void registerModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}