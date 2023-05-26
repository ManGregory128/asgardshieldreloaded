package me.mangregory.asr.util.handlers;

import java.util.List;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
/*
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
*/
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.init.ItemInit;
import me.mangregory.asr.item.AsgardShieldItem;
import me.mangregory.asr.item.GiantSwordItem;
import me.mangregory.asr.util.RandomUtil;

@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event)
    {
        Player player = event.getEntity();
        Level level = player.getLevel();
        Vec3 pos = player.getPosition(0);

        if (level instanceof ServerLevel _level && player.getItemInHand(player.swingingArm).getItem().toString().equals("ender_giant_sword")) {
            enderFx(_level, pos);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockStart(PlayerInteractEvent.RightClickItem event)
    {
        Player player = event.getEntity();
        Level level = player.getLevel();
        Vec3 pos = player.getPosition(0);
        Item itemMainHand = player.getItemInHand(player.swingingArm).getItem();
        Item itemOffHand = player.getItemInHand(player.swingingArm).getItem();

        if (itemMainHand instanceof GiantSwordItem)
        {
            if (itemOffHand instanceof AsgardShieldItem)
            {
                if (!player.getCooldowns().isOnCooldown(itemOffHand))
                {
                    ((GiantSwordItem) itemMainHand).isBlocking = false;
                    ((AsgardShieldItem) itemOffHand).isBlocking = true;
                    if (event.getHand() == InteractionHand.MAIN_HAND)
                    {
                        event.setCancellationResult(InteractionResult.PASS);
                        event.setCanceled(true);
                    }
                }
                else
                {
                    ((GiantSwordItem) itemMainHand).isBlocking = true;
                    ((AsgardShieldItem) itemOffHand).isBlocking = false;
                }
            }
            else if (itemOffHand instanceof ShieldItem)
            {
                ((GiantSwordItem) itemMainHand).isBlocking = false;
                if (event.getHand() == InteractionHand.MAIN_HAND)
                {
                    event.setCancellationResult(InteractionResult.PASS);
                    event.setCanceled(true);
                }
            }
        }

        if (level instanceof ServerLevel _level && player.getItemInHand(player.swingingArm).getItem().toString().equals("ender_giant_sword")) {
            enderFx(_level, pos);
        }
    }
    /*
    @SubscribeEvent
    public static void onBlockActive(LivingAttackEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer && !event.getEntityLiving().getEntityWorld().isRemote)
        {
            DamageSource source = event.getSource();
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (!source.isUnblockable() && player.isActiveItemStackBlocking())
            {
                ItemStack stack = player.getActiveItemStack();
                Item item = stack.getItem();
                String name = item.getRegistryName().toString();
                float damage = event.getAmount();
                float knockback = 0.0F;
                Entity projectile = source.getImmediateSource();
                Entity enemy = source.getTrueSource();
                boolean cancel = true;

                if (item instanceof AsgardShieldItem)
                {
                    switch (name)
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
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_iron_shield":
                            if (player.isWet()) damage *= 1.5F;
                            if (source.isExplosion()) damage *= 0.5F;
                            knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.3D))
                            {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            }
                            else knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.6D))
                            {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
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
                            if (enemy instanceof EntityLivingBase && RandomUtil.chance(0.2D)) knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_ender_shield":
                            if (enemy instanceof EntityEnderman || enemy instanceof EntityEndermite || enemy instanceof EntityDragon)
                            {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof EntityLivingBase && RandomUtil.chance(0.4D)) knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                    }
                    if (cancel)
                    {
                        stack.damageItem((int) damage, player);
                        if (enemy instanceof EntityLivingBase && projectile == enemy) ((EntityLivingBase) enemy).knockBack(player, knockback * 0.5F, player.posX - enemy.posX, player.posZ - enemy.posZ);
                        player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                    }
                    event.setCanceled(cancel);
                }
                else if (item instanceof ItemGiantSword)
                {
                    switch (name)
                    {
                        case AsgardShieldReloaded.NAMESPACE + "wooden_giant_sword":
                            knockback = 1.0F;
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "stone_giant_sword":
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "iron_giant_sword":
                        case AsgardShieldReloaded.NAMESPACE + "golden_giant_sword":
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "diamond_giant_sword":
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "netherquartz_giant_sword":
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "skull_giant_sword":
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "ender_giant_sword":
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                    }
                    stack.damageItem((int) damage, player);
                    if (enemy instanceof EntityLivingBase && projectile == enemy) ((EntityLivingBase) enemy).knockBack(player, knockback * 0.5F, player.posX - enemy.posX, player.posZ - enemy.posZ);
                    player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                    event.setCanceled(true);
                }
            }
        }
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
        double d1 = enemy.posY + (double) (enemy.getEntityWorld().rand.nextInt(64) - 32);
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
    */
    public static void enderFx (ServerLevel level, Vec3 playerPos) {
        for (int i = 0; i < 3; i++) {
            int rand1 = level.random.nextInt(2) * 2 - 1;
            int rand2 = level.random.nextInt(2) * 2 - 1;
            double xCoord = playerPos.x + 0.5D + 0.25D * rand1;
            double yCoord = playerPos.y() + level.random.nextFloat();
            double zCoord = playerPos.z() + 0.5D + 0.25D * rand2;
            double speed = (level.random.nextFloat() - 0.5D) * 0.125D;

            level.sendParticles(ParticleTypes.PORTAL, xCoord, yCoord, zCoord, 2, 0, 0, 0, speed);
        }
    }
}