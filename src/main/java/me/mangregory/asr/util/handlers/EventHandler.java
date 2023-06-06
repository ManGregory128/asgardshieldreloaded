package me.mangregory.asr.util.handlers;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import me.mangregory.asr.AsgardShieldReloaded;
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
        Item itemMainHand = player.getItemInHand(player.getUsedItemHand()).getItem();
        Item itemOffHand = player.getItemInHand(player.getUsedItemHand()).getItem();

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

        if (level instanceof ServerLevel _level && player.getItemInHand(player.getUsedItemHand()).getItem().toString().equals("ender_giant_sword")) {
            enderFx(_level, pos);
        }
    }

    @SubscribeEvent
    public static void onBlockActive(LivingAttackEvent event)
    {
        if (event.getEntity() instanceof Player && event.getEntity().getLevel().isClientSide)
        {
            DamageSource source = event.getSource();
            Player player = (Player) event.getEntity();
            if (!source.isCreativePlayer() && player.isBlocking())
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
                    switch (name)
                    {
                        case AsgardShieldReloaded.NAMESPACE + "wooden_shield":
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
                        case AsgardShieldReloaded.NAMESPACE + "gilded_wooden_shield":
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
                        case AsgardShieldReloaded.NAMESPACE + "stone_shield":
                            if (source.is(DamageTypes.EXPLOSION))
                            {
                                damage *= 1.5F;
                                cancel = false;
                                break;
                            }
                            else if (source.is(DamageTypes.ON_FIRE) && RandomUtil.chance(0.5D)) damage = 0.0F;
                            knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_stone_shield":
                            if (source.is(DamageTypes.EXPLOSION))
                            {
                                damage *= 1.5F;
                                cancel = false;
                                break;
                            }
                            else if (source.is(DamageTypes.ON_FIRE)) damage = 0.0F;
                            knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "iron_shield":
                            if (player.isInWaterOrRain()) damage *= 1.5F;
                            if (source.is(DamageTypes.EXPLOSION) && RandomUtil.chance(0.5F)) damage *= 0.5F;
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_iron_shield":
                            if (player.isInWaterOrRain()) damage *= 1.5F;
                            if (source.is(DamageTypes.EXPLOSION)) damage *= 0.5F;
                            knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.3D))
                            {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            }
                            else knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.6D))
                            {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            }
                            else knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "netherquartz_shield":
                            player.causeFoodExhaustion(damage);
                            if (projectile instanceof SmallFireball && RandomUtil.chance(0.5D))
                            {
                                player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.6F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                                projectile.discard();
                                player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_netherquartz_shield":
                            player.causeFoodExhaustion(damage);
                            if (projectile instanceof SmallFireball)
                            {
                                player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                                projectile.discard();
                                player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "skull_shield":
                            if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.15D))
                            {
                                List<Entity> entities = enemy.getLevel().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
                                if (entities.size() > 1)
                                {
                                    //((LivingEntity) enemy).getNavigator.tryMoveToEntityLiving(entities.get(1), 1.0F);
                                    //((LivingEntity) enemy).attackEntityAsMob(entities.get(1));
                                }
                                else enemy.hurt(new DamageSource((Holder<DamageType>) DamageTypes.MAGIC), 10000.0F);
                            }
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_skull_shield":
                            if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.3D))
                            {
                                List<Entity> entities = enemy.getLevel().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
                                if (entities.size() > 1)
                                {
                                    //((EntityLiving) enemy).getNavigator().tryMoveToEntityLiving(entities.get(1), 1.0F);
                                    //((EntityLiving) enemy).attackEntityAsMob(entities.get(1));
                                }
                                else enemy.hurt(new DamageSource((Holder<DamageType>) DamageTypes.MAGIC), 10000.0F);
                            }
                            knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "ender_shield":
                            if (enemy instanceof EnderMan || enemy instanceof Endermite || enemy instanceof EnderDragon)
                            {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.2D)) knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "gilded_ender_shield":
                            if (enemy instanceof EnderMan || enemy instanceof Endermite || enemy instanceof EnderDragon)
                            {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.4D)) knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                    }
                    if (cancel)
                    {
                        stack.getItem().damageItem(stack, (int) damage, player, player1 -> {});
                        if (enemy instanceof LivingEntity && projectile == enemy) ((LivingEntity) enemy).knockback(knockback * 0.5F, player.getX() - enemy.getY(), player.getZ() - enemy.getZ());
                        player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                    }
                    event.setCanceled(cancel);
                }
                else if (item instanceof GiantSwordItem)
                {
                    switch (name)
                    {
                        case AsgardShieldReloaded.NAMESPACE + "wooden_giant_sword":
                            knockback = 1.0F;
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "stone_giant_sword":
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "iron_giant_sword":
                        case AsgardShieldReloaded.NAMESPACE + "golden_giant_sword":
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.6F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "diamond_giant_sword":
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "netherquartz_giant_sword":
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "skull_giant_sword":
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                        case AsgardShieldReloaded.NAMESPACE + "ender_giant_sword":
                            knockback = 1.0F;
                            player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                            break;
                    }
                    stack.getItem().damageItem(stack, (int) damage, player, player1 -> {});
                    if (enemy instanceof LivingEntity && projectile == enemy) ((LivingEntity) enemy).knockback(knockback * 0.5F, player.getX() - enemy.getX(), player.getZ() - enemy.getZ());
                    player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 0.1F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void dropArrowAtPlayer(Arrow arrow, Player player)
    {
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        arrow.xo = 0;
        arrow.yo = 0;
        arrow.zo = 0;
        arrow.setPos(player.getX(), player.getY(), player.getZ());
        player.getLevel().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ARROW_HIT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getLevel().random.nextFloat() * 0.4F);
    }

    public static void reflectProjectile(Player player, Entity projectile)
    {
        Vec3 look = player.getLookAngle();
        double speed = projectile.getX() * projectile.xo + projectile.yo * projectile.yo + projectile.zo * projectile.zo;
        speed = Math.sqrt(speed);
        speed += 0.2F;
        projectile.xo = look.x * speed;
        projectile.yo = look.y * speed;
        projectile.zo = look.z * speed;
        projectile.rotate(Rotation.CLOCKWISE_180);
        /*
        if (player instanceof EntityPlayerMP) ((EntityPlayerMP) player).connection.sendPacket(new SPacketEntityVelocity(projectile));
        if (projectile instanceof Arrow)
        {
            ((Arrow) projectile).shootingEntity = player;
            projectile.xo /= -0.10000000149011612D;
            projectile.yo /= -0.10000000149011612D;
            projectile.zo /= -0.10000000149011612D;
        }
         */
    }

    public static float teleportEnemy(Entity enemy, float knockback)
    {
        Vec3 prevPos = enemy.getPosition(0);
        double d0 = enemy.getPosition(0).x + (enemy.getLevel().random.nextDouble() - 0.5D) * 64.0D;
        double d1 = enemy.getPosition(0).y + (double) (enemy.getLevel().random.nextInt(64) - 32);
        double d2 = enemy.getPosition(0).z + (enemy.getLevel().random.nextDouble() - 0.5D) * 64.0D;
        if (enemy.getLevel() instanceof ServerLevel)
        {
            enemy.teleportTo(d0, d1, d2);
            enemy.getLevel().playSound(null, prevPos.x, prevPos.y, prevPos.z, SoundEvents.ENDERMAN_TELEPORT, enemy.getSoundSource(), 1.0F, 1.0F);
            enemy.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            return 0.0F;
        }
        return knockback;
    }

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