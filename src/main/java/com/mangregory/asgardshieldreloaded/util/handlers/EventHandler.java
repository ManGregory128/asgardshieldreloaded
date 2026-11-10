package com.mangregory.asgardshieldreloaded.util.handlers;

import com.mangregory.asgardshieldreloaded.util.ParticleUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.mangregory.asgardshieldreloaded.init.ModItems;

@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();
        Entity enemy = event.getTarget();
        if (player.getEntityWorld().isRemote && player.getHeldItemMainhand().getItem().equals(ModItems.ENDER_GIANT_SWORD)) ParticleUtil.enderFx(player.getEntityWorld(), player.getPosition());
        if (player.getEntityWorld().isRemote && player.getHeldItemMainhand().getItem().equals(ModItems.ENDER_SHIELD)) ParticleUtil.enderFx(player.getEntityWorld(), player.getPosition());
        if (player.getEntityWorld().isRemote && player.getHeldItemMainhand().getItem().equals(ModItems.GILDED_ENDER_SHIELD)) ParticleUtil.enderFx(player.getEntityWorld(), player.getPosition());
        if (player.getEntityWorld().isRemote && player.getHeldItemMainhand().getItem().equals(ModItems.GILDED_SKULL_SHIELD)) ParticleUtil.skullFx(enemy.getEntityWorld(), enemy.getPosition());
        if (player.getEntityWorld().isRemote && player.getHeldItemMainhand().getItem().equals(ModItems.SKULL_GIANT_SWORD)) ParticleUtil.skullFx(enemy.getEntityWorld(), enemy.getPosition());
        if (player.getEntityWorld().isRemote && player.getHeldItemMainhand().getItem().equals(ModItems.SKULL_SHIELD)) ParticleUtil.skullFx(enemy.getEntityWorld(), enemy.getPosition());
    }

    @SubscribeEvent
    public static void onBlockStart(PlayerInteractEvent.RightClickItem event)
    {
        EntityPlayer player = event.getEntityPlayer();
        if (player.getEntityWorld().isRemote && player.getHeldItem(event.getHand()).getItem().equals(ModItems.ENDER_GIANT_SWORD)) ParticleUtil.enderFx(player.getEntityWorld(), player.getPosition());
        if (player.getEntityWorld().isRemote && player.getHeldItem(event.getHand()).getItem().equals(ModItems.ENDER_SHIELD)) ParticleUtil.enderFx(player.getEntityWorld(), player.getPosition());
        if (player.getEntityWorld().isRemote && player.getHeldItem(event.getHand()).getItem().equals(ModItems.GILDED_ENDER_SHIELD)) ParticleUtil.enderFx(player.getEntityWorld(), player.getPosition());
    }
}