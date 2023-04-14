package com.mangregory.asgardshieldreloaded.util.handlers;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.init.ModItems;
import com.mangregory.asgardshieldreloaded.items.ItemAsgardShield;
import com.mangregory.asgardshieldreloaded.items.ItemGiantSword;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;

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
            enderFx(world, pos);
        }
    }

    @SubscribeEvent
    public static void onSwordBlock(PlayerInteractEvent.RightClickItem event)
    {
        EntityPlayer player = event.getEntityPlayer();
        if (player.getHeldItem(event.getHand()).getItem().equals(ModItems.ENDER_GIANT_SWORD))
        {
            World world = player.getEntityWorld();
            BlockPos pos = player.getPosition();
            enderFx(world, pos);
        }
    }

    @SubscribeEvent
    public static void onShieldBlock(LivingAttackEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer && !event.getEntityLiving().getEntityWorld().isRemote)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (!event.getSource().isUnblockable() && player.isActiveItemStackBlocking() && player.getActiveItemStack().getItem() instanceof ItemAsgardShield)
            {
                ItemStack shieldStack = player.getActiveItemStack();
                Item shieldItem = shieldStack.getItem();
                String shieldName = shieldItem.getRegistryName().toString();
                float damage = event.getAmount();
                float knockback = 0.0F;
                DamageSource source = event.getSource();
                Entity projectile = source.getImmediateSource();
                Entity enemy = source.getTrueSource();
                boolean cancel = true;

                switch (shieldName)
                {
                    case AsgardShieldReloaded.NAMESPACE + "wooden_shield":
                        knockback = 1.0F;
                        if (source.isFireDamage())
                        {
                            cancel = false;
                            break;
                        }
                        if (projectile instanceof EntityArrow)
                        {
                            EntityArrow arrow = (EntityArrow) projectile;
                            if (arrow.isBurning())
                            {
                                cancel = false;
                                break;
                            }
                            if (RandomUtil.chance(0.5D)) dropArrowAtPlayer(arrow, player);
                        }
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "gilded_wooden_shield":
                        knockback = 1.5F;
                        if (source.isFireDamage())
                        {
                            cancel = false;
                            break;
                        }
                        if (projectile instanceof EntityArrow)
                        {
                            EntityArrow arrow = (EntityArrow) projectile;
                            if (arrow.isBurning())
                            {
                                cancel = false;
                                break;
                            }
                            dropArrowAtPlayer(arrow, player);
                        }
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "stone_shield":
                        if (source.isExplosion())
                        {
                            damage *= 1.5F;
                            cancel = false;
                            break;
                        }
                        else if (source.isFireDamage() && RandomUtil.chance(0.5D)) damage = 0.0F;
                        knockback = 1.5F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "gilded_stone_shield":
                        if (source.isExplosion())
                        {
                            damage *= 1.5F;
                            cancel = false;
                            break;
                        }
                        else if (source.isFireDamage()) damage = 0.0F;
                        knockback = 1.5F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "iron_shield":
                        if (player.isWet()) damage *= 1.5F;
                        if (source.isExplosion() && RandomUtil.chance(0.5F)) damage *= 0.5F;
                        knockback = 1.0F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "gilded_iron_shield":
                        if (player.isWet()) damage *= 1.5F;
                        if (source.isExplosion()) damage *= 0.5F;
                        knockback = 1.5F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "diamond_shield":
                        if (projectile != null && RandomUtil.chance(0.3D))
                        {
                            Vec3d look = player.getLookVec();
                            if (projectile instanceof EntityArrow)
                            {
                                EntityArrow arrow = (EntityArrow) projectile;
                                arrow.shoot(look.x, look.y, look.z, 1.1F, 0.1F);
                            }
                            else if (projectile instanceof EntityThrowable)
                            {
                                EntityThrowable throwable = (EntityThrowable) projectile;
                                throwable.shoot(look.x, look.y, look.z, 1.1F, 0.1F);
                            }
                            else if (projectile instanceof EntityFireball)
                            {
                                EntityFireball fireball = (EntityFireball) projectile;
                                fireball.motionX = look.x;
                                fireball.motionY = look.y;
                                fireball.motionZ = look.z;
                                fireball.accelerationX = fireball.motionX * 0.1D;
                                fireball.accelerationY = fireball.motionY * 0.1D;
                                fireball.accelerationZ = fireball.motionZ * 0.1D;
                            }
                            damage *= 1.5F;
                        }
                        else knockback = 1.0F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "gilded_diamond_shield":
                        if (projectile != null && RandomUtil.chance(0.6D))
                        {
                            Vec3d look = player.getLookVec();
                            double speed = projectile.motionX * projectile.motionX + projectile.motionY * projectile.motionY + projectile.motionZ * projectile.motionZ;
                            speed = Math.sqrt(speed);
                            speed += 0.2f;
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
                            damage *= 1.5F;
                            knockback = 0.0F;
                        }
                        else knockback = 1.5F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "netherquartz_shield":
                        player.addExhaustion(damage);
                        if (projectile instanceof EntitySmallFireball && RandomUtil.chance(0.5D))
                        {
                            player.getEntityWorld().playSound(null, projectile.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            projectile.setDead();
                            player.inventory.addItemStackToInventory(new ItemStack(Items.FIRE_CHARGE));
                        }
                        knockback = 1.0F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "gilded_netherquartz_shield":
                        player.addExhaustion(damage);
                        if (projectile instanceof EntitySmallFireball)
                        {
                            player.getEntityWorld().playSound(null, projectile.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            projectile.setDead();
                            player.inventory.addItemStackToInventory(new ItemStack(Items.FIRE_CHARGE));
                        }
                        knockback = 1.5F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "skull_shield":
                        if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                        if (enemy instanceof EntityLiving && RandomUtil.chance(0.15D))
                        {
                            List<EntityMob> entities = enemy.getEntityWorld().getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(enemy.posX - 4, enemy.posY - 4, enemy.posZ - 4, enemy.posX + 4, enemy.posY + 4, enemy.posZ + 4));
                            if (entities.size() > 1)
                            {
                                ((EntityLiving) enemy).getNavigator().tryMoveToEntityLiving(entities.get(1), 1.0F);
                                ((EntityLiving) enemy).attackEntityAsMob(entities.get(1));
                            }
                            else enemy.attackEntityFrom(DamageSource.MAGIC, 10000.0F);
                        }
                        knockback = 1.0F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "gilded_skull_shield":
                        if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                        if (enemy instanceof EntityLiving && RandomUtil.chance(0.3D))
                        {
                            List<EntityMob> entities = enemy.getEntityWorld().getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(enemy.posX - 4, enemy.posY - 4, enemy.posZ - 4, enemy.posX + 4, enemy.posY + 4, enemy.posZ + 4));
                            if (entities.size() > 1)
                            {
                                ((EntityLiving) enemy).getNavigator().tryMoveToEntityLiving(entities.get(1), 1.0F);
                                ((EntityLiving) enemy).attackEntityAsMob(entities.get(1));
                            }
                            else enemy.attackEntityFrom(DamageSource.MAGIC, 10000.0F);
                        }
                        knockback = 1.5F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "ender_shield":
                        if (enemy instanceof EntityEnderman || enemy instanceof EntityEndermite || enemy instanceof EntityDragon)
                        {
                            cancel = false;
                            break;
                        }
                        if (enemy instanceof EntityLivingBase && RandomUtil.chance(0.2D))
                        {
                            double d0 = enemy.posX + (enemy.getEntityWorld().rand.nextDouble() - 0.5D) * 64.0D;
                            double d1 = enemy.posY + (double) (enemy.getEntityWorld().rand.nextInt(64) - 32);
                            double d2 = enemy.posZ + (enemy.getEntityWorld().rand.nextDouble() - 0.5D) * 64.0D;
                            boolean teleport = ((EntityLivingBase) enemy).attemptTeleport(d0, d1, d2);
                            if (teleport)
                            {
                                enemy.getEntityWorld().playSound(null, enemy.prevPosX, enemy.prevPosY, enemy.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, enemy.getSoundCategory(), 1.0F, 1.0F);
                                enemy.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
                            }
                        }
                        knockback = 1.0F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                    case AsgardShieldReloaded.NAMESPACE + "gilded_ender_shield":
                        if (enemy instanceof EntityEnderman || enemy instanceof EntityEndermite || enemy instanceof EntityDragon)
                        {
                            cancel = false;
                            break;
                        }
                        if (enemy instanceof EntityLivingBase && RandomUtil.chance(0.2D))
                        {
                            double d0 = enemy.posX + (enemy.getEntityWorld().rand.nextDouble() - 0.5D) * 64.0D;
                            double d1 = enemy.posY + (double) (enemy.getEntityWorld().rand.nextInt(64) - 32);
                            double d2 = enemy.posZ + (enemy.getEntityWorld().rand.nextDouble() - 0.5D) * 64.0D;
                            boolean teleport = ((EntityLivingBase) enemy).attemptTeleport(d0, d1, d2);
                            if (teleport)
                            {
                                enemy.getEntityWorld().playSound(null, enemy.prevPosX, enemy.prevPosY, enemy.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, enemy.getSoundCategory(), 1.0F, 1.0F);
                                enemy.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
                            }
                        }
                        knockback = 1.5F;
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                        break;
                }
                if (cancel)
                {
                    shieldStack.damageItem((int) damage, player);
                    if (enemy instanceof EntityLivingBase && projectile == enemy) ((EntityLivingBase) enemy).knockBack(player, knockback * 0.5F, player.posX - enemy.posX, player.posZ - enemy.posZ);
                    player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                }
                event.setCanceled(cancel);
            }
        }
    }

    @SubscribeEvent
    public static void onUseTick(LivingEntityUseItemEvent.Tick event)
    {
        if (event.getItem().getItem() instanceof ItemAsgardShield)
        {
            ItemAsgardShield shield = (ItemAsgardShield) event.getItem().getItem();
            shield.cooldown++;
            if (shield.cooldown >= shield.maxUseDuration)
            {
                event.getEntityLiving().stopActiveHand();
                event.setCanceled(true);
            }
        }
        else if (event.getItem().getItem() instanceof ItemGiantSword)
        {
            ItemGiantSword shield = (ItemGiantSword) event.getItem().getItem();
            shield.cooldown++;
            if (shield.cooldown >= shield.maxUseDuration)
            {
                event.getEntityLiving().stopActiveHand();
                event.setCanceled(true);
            }
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

    public static void dropArrowAtPlayer(EntityArrow arrow, EntityPlayer player)
    {
        arrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        arrow.motionX = 0;
        arrow.motionY = 0;
        arrow.motionZ = 0;
        arrow.setPosition(player.posX, player.posY, player.posZ);
    }
}