package com.mangregory.asgardshieldreloaded.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShieldUtil
{
    private static final String CONFUSED_UNTIL = "ASRConfusedUntil";

    public static void confuseEnemy(EntityLiving attacker, EntityPlayer player, int duration)
    {
        if (attacker == null || player == null) return;
        if (!attacker.isEntityAlive()) return;
        if (attacker.world.isRemote) return;

        attacker.getEntityData().setLong(CONFUSED_UNTIL, attacker.world.getTotalWorldTime() + duration);
        findNewTarget(attacker, player.getEntityBoundingBox().grow(10.0D));
    }

    public static void dropArrowAtPlayer(EntityArrow arrow, EntityPlayer player)
    {
        arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        arrow.motionX = 0;
        arrow.motionY = 0;
        arrow.motionZ = 0;
        arrow.setPosition(player.posX, player.posY, player.posZ);
        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
    }

    private static void findNewTarget(EntityLiving attacker, AxisAlignedBB area)
    {
        List<EntityLivingBase> targets = attacker.world.getEntitiesWithinAABB(EntityLivingBase.class, area);
        List<EntityLivingBase> validTargets = new ArrayList<>();

        for (EntityLivingBase entity : targets)
        {
            if (!isValidTarget(attacker, entity)) continue;
            validTargets.add(entity);
        }

        if (validTargets.isEmpty())
        {
            attacker.setAttackTarget(null);
            return;
        }

        EntityLivingBase target = validTargets.get(attacker.world.rand.nextInt(validTargets.size()));
        attacker.setAttackTarget(target);
    }

    // Meant for Harken Scythe shields
    public static boolean handleEssenceContainer(EntityPlayer player, String registryName)
    {
        for (int i = 0, size = player.inventory.getSizeInventory(); i < size; i++)
        {
            ItemStack stack = player.inventory.getStackInSlot(i);
            String regName = Objects.requireNonNull(stack.getItem().getRegistryName()).toString();
            if (regName.equals(registryName))
            {
                if (!player.capabilities.isCreativeMode)
                {
                    int itemDamage = stack.getItemDamage();
                    if (itemDamage + 1 < stack.getMaxDamage())
                    {
                        stack.setItemDamage(itemDamage + 1);
                    }
                    else
                    {
                        stack.shrink(1);
                        ResourceLocation newItem = regName.contains("keeper") ? new ResourceLocation("harkenscythe:essence_keeper") : new ResourceLocation("harkenscythe:essence_vessel");
                        if (regName.contains("keeper") || regName.contains("vessel"))
                        {
                            player.inventory.setInventorySlotContents(i, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(newItem))));
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static boolean isValidTarget(EntityLiving attacker, EntityLivingBase target)
    {
        return target != null && target != attacker && target.isEntityAlive() && target instanceof IMob;
    }

    public static void reflectProjectile(EntityPlayer player, Entity projectile)
    {
        Vec3d look = player.getLookVec();
        double speed = projectile.motionX * projectile.motionX + projectile.motionY * projectile.motionY + projectile.motionZ * projectile.motionZ;
        speed = Math.sqrt(speed);
        speed += 0.2F;
        projectile.motionX = look.x * speed;
        projectile.motionY = look.y * speed;
        projectile.motionZ = look.z * speed;
        projectile.rotationYaw = (float) (Math.atan2(projectile.motionX, projectile.motionZ) * 180.0D / Math.PI);
        projectile.rotationPitch = (float) (Math.atan2(projectile.motionY, speed) * 180.0D / Math.PI);
        if (player instanceof EntityPlayerMP) ((EntityPlayerMP) player).connection.sendPacket(new SPacketEntityVelocity(projectile));
        if (projectile instanceof EntityArrow)
        {
            ((EntityArrow) projectile).shootingEntity = player;
            projectile.motionX /= -0.10000000149011612D;
            projectile.motionY /= -0.10000000149011612D;
            projectile.motionZ /= -0.10000000149011612D;
        }
    }

    public static float teleportEnemy(Entity enemy, float knockback)
    {
        double d0 = enemy.posX + (enemy.getEntityWorld().rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = enemy.posY + (enemy.getEntityWorld().rand.nextInt(64) - 32);
        double d2 = enemy.posZ + (enemy.getEntityWorld().rand.nextDouble() - 0.5D) * 64.0D;
        boolean teleport = ((EntityLivingBase) enemy).attemptTeleport(d0, d1, d2);
        if (teleport)
        {
            enemy.getEntityWorld().playSound(null, enemy.prevPosX, enemy.prevPosY, enemy.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, enemy.getSoundCategory(), 1.0F, 1.0F);
            enemy.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
            return 0.0F;
        }
        return knockback;
    }

    public static void updateConfusion(EntityLiving attacker)
    {
        if (!attacker.getEntityData().hasKey(CONFUSED_UNTIL)) return;
        long expiration = attacker.getEntityData().getLong(CONFUSED_UNTIL);

        if (attacker.world.getTotalWorldTime() >= expiration)
        {
            attacker.getEntityData().removeTag(CONFUSED_UNTIL);
            return;
        }

        EntityLivingBase currentTarget = attacker.getAttackTarget();
        if (isValidTarget(attacker, currentTarget)) return;
        findNewTarget(attacker, attacker.getEntityBoundingBox().grow(10.0D));
    }
}
