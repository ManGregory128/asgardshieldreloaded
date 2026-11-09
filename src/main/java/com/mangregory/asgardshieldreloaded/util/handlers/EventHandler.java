package com.mangregory.asgardshieldreloaded.util.handlers;

import java.util.List;
import java.util.Objects;

import com.mangregory.asgardshieldreloaded.util.ParticleUtil;
import com.mangregory.asgardshieldreloaded.util.ShieldUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.mangregory.asgardshieldreloaded.AsgardShieldReloaded;
import com.mangregory.asgardshieldreloaded.init.ModItems;
import com.mangregory.asgardshieldreloaded.items.ItemAsgardShield;
import com.mangregory.asgardshieldreloaded.util.RandomUtil;

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
                String name = Objects.requireNonNull(item.getRegistryName()).toString();
                float damage = event.getAmount();
                float knockback = 0.0F;
                Entity projectile = source.getImmediateSource();
                Entity enemy = source.getTrueSource();
                boolean cancel = true;

                if (item instanceof ItemAsgardShield)
                {
                    switch (name)
                    {
                        case AsgardShieldReloaded.NAMESPACE + "wooden_shield":
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
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
                                if (RandomUtil.chance(0.25D)) ShieldUtil.dropArrowAtPlayer(arrow, player);
                            }
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_wooden_shield":
                            knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
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
                                if (RandomUtil.chance(0.5D)) ShieldUtil.dropArrowAtPlayer(arrow, player);
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
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_HAT, SoundCategory.PLAYERS, 1.2F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_stone_shield":
                            if (source.isExplosion())
                            {
                                damage *= 1.5F;
                                cancel = false;
                                break;
                            }
                            else if (source.isFireDamage()) damage = 0.0F;
                            knockback = 2.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_HAT, SoundCategory.PLAYERS, 1.2F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "iron_shield":
                            if (player.isWet()) damage *= 1.5F;
                            if (source.isExplosion() && RandomUtil.chance(0.5D)) damage *= 0.5F;
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_iron_shield":
                            if (player.isWet()) damage *= 1.5F;
                            if (source.isExplosion()) damage *= 0.5F;
                            knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "golden_shield":
                            knockback = 0.5F;
                            if (RandomUtil.chance(0.25D) && player.shouldHeal() && enemy instanceof EntityLivingBase)
                            {
                                // TODO: Spawn particles when player is healed
                                // One heart and a half + 10% of the attacker's max health
                                player.heal(3 + ((EntityLivingBase) enemy).getMaxHealth() * 0.1F);
                                player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.PLAYERS, 2.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                                if (((EntityLivingBase) enemy).getCreatureAttribute().equals(EnumCreatureAttribute.UNDEAD)) knockback = 1.0F;
                            }
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "blessed_golden_shield":
                            knockback = 0.75F;
                            if (RandomUtil.chance(0.5D) && player.shouldHeal() && enemy instanceof EntityLivingBase)
                            {
                                // TODO: Spawn particles when player is healed
                                // Three hearts + 20% of the attacker's max health
                                player.heal(6 + ((EntityLivingBase) enemy).getMaxHealth() * 0.2F);
                                player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.PLAYERS, 2.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                                if (((EntityLivingBase) enemy).getCreatureAttribute().equals(EnumCreatureAttribute.UNDEAD)) knockback = 1.5F;
                            }
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.3D))
                            {
                                ShieldUtil.reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            }
                            else knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 1.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.6D))
                            {
                                ShieldUtil.reflectProjectile(player, projectile);
                                damage *= 2.0F;
                            }
                            else knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 1.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "netherquartz_shield":
                            player.addExhaustion(damage);
                            if (projectile instanceof EntitySmallFireball && RandomUtil.chance(0.25D))
                            {
                                player.getEntityWorld().playSound(null, projectile.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                                projectile.setDead();
                                player.inventory.addItemStackToInventory(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.PLAYERS, 1.2F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_netherquartz_shield":
                            player.addExhaustion(damage);
                            if (projectile instanceof EntitySmallFireball && RandomUtil.chance(0.5D))
                            {
                                player.getEntityWorld().playSound(null, projectile.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                                projectile.setDead();
                                player.inventory.addItemStackToInventory(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.PLAYERS, 1.2F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "patchwork_shield":
                            if (RandomUtil.chance(0.05D)) player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
                            if (RandomUtil.chance(0.1D))
                            {
                                if (enemy instanceof EntityPlayer) ((EntityPlayer) enemy).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
                                else if (enemy instanceof EntityLivingBase) ((EntityLivingBase) enemy).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 0));
                            }
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_STEP, SoundCategory.PLAYERS, 2.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_patchwork_shield":
                            if (RandomUtil.chance(0.05D)) player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
                            if (RandomUtil.chance(0.2D))
                            {
                                if (enemy instanceof EntityPlayer) ((EntityPlayer) enemy).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
                                else if (enemy instanceof EntityLivingBase) ((EntityLivingBase) enemy).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 0));
                            }
                            knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_STEP, SoundCategory.PLAYERS, 2.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "skull_shield":
                            if (RandomUtil.chance(0.1D)) damage *= 3.0F;
                            if (enemy instanceof EntityLiving && enemy.isNonBoss() && RandomUtil.chance(0.15D) && ((EntityLiving) enemy).getHealth() <= ((EntityLiving) enemy).getMaxHealth() * 0.2F)
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
                        case AsgardShieldReloaded.NAMESPACE + "gilded_skull_shield":
                            if (RandomUtil.chance(0.1D)) damage *= 3.0F;
                            if (enemy instanceof EntityLiving && enemy.isNonBoss() && RandomUtil.chance(0.3D) && ((EntityLiving) enemy).getHealth() <= ((EntityLiving) enemy).getMaxHealth() * 0.3F)
                            {
                                List<EntityMob> entities = enemy.getEntityWorld().getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(enemy.posX - 4, enemy.posY - 4, enemy.posZ - 4, enemy.posX + 4, enemy.posY + 4, enemy.posZ + 4));
                                if (entities.size() > 1)
                                {
                                    ((EntityLiving) enemy).getNavigator().tryMoveToEntityLiving(entities.get(1), 1.0F);
                                    ((EntityLiving) enemy).attackEntityAsMob(entities.get(1));
                                }
                                else enemy.attackEntityFrom(DamageSource.MAGIC, 10000.0F);
                            }
                            knockback = 2.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_SKELETON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "ender_shield":
                            if (enemy instanceof EntityEnderman || enemy instanceof EntityEndermite || enemy instanceof EntityDragon)
                            {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof EntityLivingBase && RandomUtil.chance(0.2D)) knockback = ShieldUtil.teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_ender_shield":
                            if (enemy instanceof EntityEnderman || enemy instanceof EntityEndermite || enemy instanceof EntityDragon)
                            {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof EntityLivingBase && RandomUtil.chance(0.4D)) knockback = ShieldUtil.teleportEnemy(enemy, knockback);
                            else knockback = 2.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_HURT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "biomass_shield":
                            if (RandomUtil.chance(0.1D) && (ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_keeper_blood") || ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_vessel_blood")))
                            {
                                if (!player.isPotionActive(MobEffects.ABSORPTION)) player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 200, 4));
                            }
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_STEP, SoundCategory.PLAYERS, 2.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_biomass_shield":
                            if (RandomUtil.chance(0.2D) && (ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_keeper_blood") || ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_vessel_blood")))
                            {
                                if (!player.isPotionActive(MobEffects.ABSORPTION)) player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 300, 4));
                            }
                            knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_STEP, SoundCategory.PLAYERS, 2.0F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "livingmetal_shield":
                            if (RandomUtil.chance(0.1D) && (ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_keeper_soul") || ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_vessel_soul")))
                            {
                                if (!player.isPotionActive(MobEffects.ABSORPTION)) player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 200, 4));
                            }
                            knockback = 1.0F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_livingmetal_shield":
                            if (RandomUtil.chance(0.2D) && (ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_keeper_soul") || ShieldUtil.handleEssenceContainer(player, "harkenscythe:essence_vessel_soul")))
                            {
                                if (!player.isPotionActive(MobEffects.ABSORPTION)) player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 300, 4));
                            }
                            knockback = 1.5F;
                            player.getEntityWorld().playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 0.6F, 0.8F + player.getEntityWorld().rand.nextFloat() * 0.4F);
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
            }
        }
    }
}