package com.mangregory.asgardshieldreloaded.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
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
        if (player.getHeldItemMainhand().getItem().equals(ModItems.ENDER_GIANT_SWORD))
        {
            World world = player.getEntityWorld();
            BlockPos pos = player.getPosition();
            for (int i = 0; i < 3; i++)
            {
                int rand1 = world.rand.nextInt(2) * 2 - 1;
                int rand2 = world.rand.nextInt(2) * 2 - 1;
                double xCoord = pos.getX() + 0.5D + 0.25D * rand1;
                double yCoord = pos.getY() + world.rand.nextFloat();
                double zCoord = pos.getZ() + 0.5D + 0.25D * rand2;
                double xSpeed = (world.rand.nextFloat() * 1.0F * rand1);
                double ySpeed = (world.rand.nextFloat() - 0.5D) * 0.125D;
                double zSpeed = (world.rand.nextFloat() * 1.0F * rand2);
                world.spawnParticle(EnumParticleTypes.PORTAL, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
            }
        }
    }
}