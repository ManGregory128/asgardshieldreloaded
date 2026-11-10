package com.mangregory.asgardshieldreloaded.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ParticleUtil
{
    // TODO: Break particles for when shields are hit or used
    public static void breakFx(World world, EntityPlayer player, ItemStack stack)
    {
        for (int i = 0; i < 5; i++)
        {
            Vec3d vec3d = new Vec3d(world.rand.nextFloat() - 0.5D * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            vec3d = vec3d.rotatePitch(-player.rotationPitch * 0.017453292F);
            vec3d = vec3d.rotateYaw(-player.rotationYaw * 0.017453292F);
            double d0 = -world.rand.nextFloat() * 0.6D - 0.3D;
            Vec3d vec3d1 = new Vec3d(world.rand.nextFloat() - 0.5D * 0.3D, d0, 0.6D);
            vec3d1 = vec3d1.rotatePitch(-player.rotationPitch * 0.017453292F);
            vec3d1 = vec3d1.rotateYaw(-player.rotationYaw * 0.017453292F);
            vec3d1 = vec3d1.add(player.posX, player.posY + player.getEyeHeight(), player.posZ);
            if (world instanceof WorldServer) world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
            else world.spawnParticle(EnumParticleTypes.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
        }
    }

    public static void enderFx(World world, BlockPos pos)
    {
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

    public static void skullFx(World world, BlockPos pos)
    {
        double xCoord = pos.getX();
        double yCoord = pos.getY() + 1.0D;
        double zCoord = pos.getZ();
        world.spawnParticle(EnumParticleTypes.CLOUD, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
    }
}
