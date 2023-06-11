package me.mangregory.asr.util.handlers;


import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.item.AsgardShieldItem;
import me.mangregory.asr.item.GiantSwordItem;
import me.mangregory.asr.util.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static me.mangregory.asr.util.handlers.EventHandler.*;


// Courtesy of Fuzs
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class EventHandlerClient
{
    @SubscribeEvent
    public void onRightClickItem(final PlayerInteractEvent.RightClickItem evt) {
        Player player = evt.getEntity();
        if (canItemStackBlock(evt.getItemStack())) {
            if (evt.getHand() != InteractionHand.MAIN_HAND || player.getOffhandItem().getUseAnimation() != UseAnim.BLOCK) {
                player.startUsingItem(evt.getHand());
                // cause reequip animation, but don't swing hand, not to be confused with ActionResultType#SUCCESS
                // partial version seems to not affect game stats which is probably better since you can just spam sword blocking haha
                evt.setCancellationResult(InteractionResult.CONSUME_PARTIAL);
                evt.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void onItemUseStart(final LivingEntityUseItemEvent.Start evt) {
        if (evt.getEntity() instanceof Player && canItemStackBlock(evt.getItem())) {
            // default use duration for items
            evt.setDuration(72000);
        }
    }
    @SubscribeEvent
    public static void onLivingHurt(final LivingHurtEvent evt) {
        if (evt.getEntity() instanceof Player player) {
            if (isDamageSourceBlockable(evt.getSource(), player) && evt.getAmount() > 0.0F) {
                //evt.setAmount((1.0F + evt.getAmount()) * 0.5F);
                player.level().playSound(null, BlockPos.containing(player.getPosition(0)),
                        SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 0.1F,
                        0.8F + player.level().random.nextFloat() * 0.4F);
                evt.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void onLivingKnockBack(final LivingKnockBackEvent evt) {
        if (evt.getEntity() instanceof Player player && isActiveItemStackBlocking(player)) {
            evt.setCanceled(true);
        }
    }
    private static boolean isDamageSourceBlockable(DamageSource source, Player player) {
        Entity entity = source.getDirectEntity();
        if (entity instanceof AbstractArrow arrow) {
            if (arrow.getPierceLevel() > 0) {
                return false;
            }
        }
        if (!source.is(DamageTypes.GENERIC) && isActiveItemStackBlocking(player)) {
            Vec3 vec32 = source.getSourcePosition();
            if (vec32 != null) {
                return true;
                //Vec3 vec3 = player.getViewVector(1.0F);
                //Vec3 vec31 = vec32.vectorTo(player.position()).normalize();
                //vec31 = new Vec3(vec31.x, 0.0, vec31.z);
                //return vec31.dot(vec3) < -Math.cos(1 * Math.PI * 0.5 / 180.0);
            }
        }
        return false;
    }
    public static boolean isActiveItemStackBlocking(Player player) {
        return player.isUsingItem() && canItemStackBlock(player.getUseItem());
    }
    public static boolean canItemStackBlock(ItemStack stack) {
        if (stack.getItem() instanceof GiantSwordItem) {
            if (((GiantSwordItem) stack.getItem()).isBlocking)
                return true;
            else return false;
        } else {
            return false;
        }
    }

    @SubscribeEvent
    public static void onBlockActive(LivingAttackEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            DamageSource source = event.getSource();
            Player player = (Player) event.getEntity();

            if (player.isBlocking() && (source.is(DamageTypes.MOB_ATTACK) || source.is(DamageTypes.MOB_PROJECTILE)
                                            || source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.ARROW))
                                            || source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.PLAYER_EXPLOSION))
            {
                ItemStack stack = player.getItemInHand(player.swingingArm);
                Item item = stack.getItem();
                String name = item.getName(stack).toString();
                float damage = event.getAmount();
                float knockback = 0.0F;
                Entity projectile = source.getDirectEntity();
                Entity enemy = source.getEntity();
                boolean cancel = true;

                if (item instanceof AsgardShieldItem)
                {
                    switch (item.toString())
                    {
                        case "wooden_shield":
                            knockback = 1.0F;
                            if (source.is(DamageTypes.ON_FIRE))
                            {
                                cancel = false;
                                break;
                            }
                            if (projectile instanceof Arrow)
                            {
                                Arrow arrow = (Arrow) projectile;
                                if (arrow.isOnFire())
                                {
                                    cancel = false;
                                    break;
                                }
                                if (RandomUtil.chance(0.5D)) dropArrowAtPlayer(arrow, player);
                            }
                            break;
                        case "gilded_wooden_shield":
                            knockback = 1.5F;
                            if (source.is(DamageTypes.ON_FIRE))
                            {
                                cancel = false;
                                break;
                            }
                            if (projectile instanceof Arrow)
                            {
                                Arrow arrow = (Arrow) projectile;
                                if (arrow.isOnFire())
                                {
                                    cancel = false;
                                    break;
                                }
                                dropArrowAtPlayer(arrow, player);
                            }
                            break;
                        case "stone_shield":
                            if (source.is(DamageTypes.EXPLOSION))
                            {
                                damage *= 1.5F;
                                cancel = false;
                                break;
                            }
                            else if (source.is(DamageTypes.ON_FIRE) && RandomUtil.chance(0.5D)) damage = 0.0F;
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_stone_shield":
                            if (source.is(DamageTypes.EXPLOSION))
                            {
                                damage *= 1.5F;
                                cancel = false;
                                break;
                            }
                            else if (source.is(DamageTypes.ON_FIRE)) damage = 0.0F;
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "iron_shield":
                            if (player.isInWaterOrRain()) damage *= 1.5F;
                            if (source.is(DamageTypes.EXPLOSION) && RandomUtil.chance(0.5F)) damage *= 0.5F;
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_iron_shield":
                            if (player.isInWaterOrRain()) damage *= 1.5F;
                            if (source.is(DamageTypes.EXPLOSION)) damage *= 0.5F;
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.3D))
                            {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            }
                            else knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.6D))
                            {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            }
                            else knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "netherquartz_shield":
                            player.causeFoodExhaustion(damage);
                            if (projectile instanceof SmallFireball && RandomUtil.chance(0.5D))
                            {
                                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                                projectile.discard();
                                player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_netherquartz_shield":
                            player.causeFoodExhaustion(damage);
                            if (projectile instanceof SmallFireball)
                            {
                                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                                projectile.discard();
                                player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "skull_shield":
                            if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.15D))
                            {
                                List<Entity> entities = enemy.level().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
                                if (entities.size() > 1)
                                {
                                    //((LivingEntity) enemy).getNavigator.tryMoveToEntityLiving(entities.get(1), 1.0F);
                                    //((LivingEntity) enemy).attackEntityAsMob(entities.get(1));
                                }
                                else enemy.hurt(new DamageSource((Holder<DamageType>) DamageTypes.MAGIC), 10000.0F);
                            }
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_skull_shield":
                            if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.3D))
                            {
                                List<Entity> entities = enemy.level().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
                                if (entities.size() > 1)
                                {
                                    //((EntityLiving) enemy).getNavigator().tryMoveToEntityLiving(entities.get(1), 1.0F);
                                    //((EntityLiving) enemy).attackEntityAsMob(entities.get(1));
                                }
                                else enemy.hurt(new DamageSource((Holder<DamageType>) DamageTypes.MAGIC), 10000.0F);
                            }
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "ender_shield":
                            if (enemy instanceof EnderMan || enemy instanceof Endermite || enemy instanceof EnderDragon)
                            {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.2D)) knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_ender_shield":
                            if (enemy instanceof EnderMan || enemy instanceof Endermite || enemy instanceof EnderDragon)
                            {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.4D)) knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                    }
                    if (cancel)
                    {
                        stack.getItem().damageItem(stack, (int) damage, player, player1 -> {});
                        if (enemy instanceof LivingEntity && projectile == enemy) ((LivingEntity) enemy).knockback(knockback * 0.5F, player.getX() - enemy.getY(), player.getZ() - enemy.getZ());
                        //player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                    }
                    //event.setCanceled(cancel);
                }
                else if (item instanceof GiantSwordItem)
                {
                    switch (item.toString())
                    {
                        case "wooden_giant_sword":
                            knockback = 1.0F;
                            break;
                        case "stone_giant_sword":
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "iron_giant_sword":
                        case "golden_giant_sword":
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "diamond_giant_sword":
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "netherquartz_giant_sword":
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "skull_giant_sword":
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "ender_giant_sword":
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                    }
                    stack.getItem().damageItem(stack, (int) damage, player, player1 -> {});
                    if (enemy instanceof LivingEntity && projectile == enemy) ((LivingEntity) enemy).knockback(knockback * 0.5F, player.getX() - enemy.getX(), player.getZ() - enemy.getZ());
                    //player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 0.1F, 0.8F + player.level().random.nextFloat() * 0.4F);
                    //event.setCanceled(true);
                }
            }
        }
    }

}