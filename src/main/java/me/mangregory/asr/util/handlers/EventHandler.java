package me.mangregory.asr.util.handlers;

import me.mangregory.asr.item.AsgardShieldItem;
import me.mangregory.asr.item.GiantSwordItem;
import me.mangregory.asr.util.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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

import java.util.List;

@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        Vec3 pos = player.getPosition(0);

        if (level instanceof ServerLevel _level && player.getItemInHand(player.swingingArm).getItem().toString().equals("ender_giant_sword")) {
            enderFx(_level, pos);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockStart(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = player.level();
        Vec3 pos = player.getPosition(0);

        Item itemMainHand = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        Item itemOffHand = player.getItemInHand(InteractionHand.OFF_HAND).getItem();

        if (itemMainHand instanceof GiantSwordItem || itemOffHand instanceof GiantSwordItem
                || itemMainHand instanceof AsgardShieldItem || itemOffHand instanceof AsgardShieldItem)
            level.playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.IRON_GOLEM_ATTACK,
                    SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);

        if (itemMainHand instanceof GiantSwordItem) {
            if (itemOffHand instanceof AsgardShieldItem) {
                if (!player.getCooldowns().isOnCooldown(itemOffHand)) {
                    ((GiantSwordItem) itemMainHand).isBlocking = false;
                    ((AsgardShieldItem) itemOffHand).isBlocking = true;
                    if (event.getHand() == InteractionHand.MAIN_HAND) {
                        player.stopUsingItem();
                        event.setCancellationResult(InteractionResult.PASS);
                        event.setCanceled(true);
                    }
                } else {
                    ((GiantSwordItem) itemMainHand).isBlocking = true;
                    ((AsgardShieldItem) itemOffHand).isBlocking = false;
                }
            } else if (itemOffHand instanceof ShieldItem) {
                ((GiantSwordItem) itemMainHand).isBlocking = false;
                if (event.getHand() == InteractionHand.MAIN_HAND) {
                    player.stopUsingItem();
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
    public static void onBlockActive(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player && !event.getEntity().level().isClientSide) {
            DamageSource source = event.getSource();

            if (player.isBlocking() && (source.is(DamageTypes.MOB_ATTACK) || source.is(DamageTypes.MOB_PROJECTILE)
                    || source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.ARROW))
                    || source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.PLAYER_EXPLOSION)) {
                ItemStack stack = player.getItemInHand(player.swingingArm);
                Item item = stack.getItem();
                String name = item.getName(stack).toString();
                float damage = event.getAmount();
                float knockback = 0.0F;
                Entity projectile = source.getDirectEntity();
                Entity enemy = source.getEntity();
                boolean cancel = true;

                if (item instanceof AsgardShieldItem) {
                    switch (item.toString()) {
                        case "wooden_shield":
                            knockback = 1.0F;
                            if (source.is(DamageTypes.ON_FIRE)) {
                                cancel = false;
                                break;
                            }
                            if (projectile instanceof Arrow) {
                                Arrow arrow = (Arrow) projectile;
                                if (arrow.isOnFire()) {
                                    cancel = false;
                                    break;
                                }
                                if (RandomUtil.chance(0.5D)) dropArrowAtPlayer(arrow, player);
                            }
                            break;
                        case "gilded_wooden_shield":
                            knockback = 1.5F;
                            if (source.is(DamageTypes.ON_FIRE)) {
                                cancel = false;
                                break;
                            }
                            if (projectile instanceof Arrow) {
                                Arrow arrow = (Arrow) projectile;
                                if (arrow.isOnFire()) {
                                    cancel = false;
                                    break;
                                }
                                dropArrowAtPlayer(arrow, player);
                            }
                            break;
                        case "stone_shield":
                            if (source.is(DamageTypes.EXPLOSION)) {
                                damage *= 1.5F;
                                cancel = false;
                                break;
                            } else if (source.is(DamageTypes.ON_FIRE) && RandomUtil.chance(0.5D)) damage = 0.0F;
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_stone_shield":
                            if (source.is(DamageTypes.EXPLOSION)) {
                                damage *= 1.5F;
                                cancel = false;
                                break;
                            } else if (source.is(DamageTypes.ON_FIRE)) damage = 0.0F;
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
                            if (projectile != null && RandomUtil.chance(0.3D)) {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            } else knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_diamond_shield":
                            if (projectile != null && RandomUtil.chance(0.6D)) {
                                reflectProjectile(player, projectile);
                                damage *= 1.5F;
                            } else knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "netherquartz_shield":
                            player.causeFoodExhaustion(damage);
                            if (projectile instanceof SmallFireball && RandomUtil.chance(0.5D)) {
                                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                                projectile.discard();
                                player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.6F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_netherquartz_shield":
                            player.causeFoodExhaustion(damage);
                            if (projectile instanceof SmallFireball) {
                                player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                                projectile.discard();
                                player.getInventory().add(new ItemStack(Items.FIRE_CHARGE));
                            }
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.NOTE_BLOCK_XYLOPHONE.get(), SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "skull_shield":
                            if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.15D)) {
                                List<Entity> entities = enemy.level().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
                                if (entities.size() > 1) {
                                    ((LivingEntity) enemy).travel(entities.get(1).getPosition(0));
                                    ((LivingEntity) enemy).doHurtTarget(entities.get(1));
                                } else enemy.hurt(new DamageSource((Holder<DamageType>) DamageTypes.MAGIC), 10000.0F);
                            }
                            knockback = 1.0F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_skull_shield":
                            if (RandomUtil.chance(0.1F)) damage *= 3.0F;
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.3D)) {
                                List<Entity> entities = enemy.level().getEntities(null, new AABB(enemy.getX() - 4, enemy.getY() - 4, enemy.getZ() - 4, enemy.getX() + 4, enemy.getY() + 4, enemy.getZ() + 4));
                                if (entities.size() > 1) {
                                    ((LivingEntity) enemy).travel(entities.get(1).getPosition(0));
                                    ((LivingEntity) enemy).doHurtTarget(entities.get(1));
                                } else enemy.hurt(new DamageSource((Holder<DamageType>) DamageTypes.MAGIC), 10000.0F);
                            }
                            knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "ender_shield":
                            if (enemy instanceof EnderMan || enemy instanceof Endermite || enemy instanceof EnderDragon) {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.2D))
                                knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                        case "gilded_ender_shield":
                            if (enemy instanceof EnderMan || enemy instanceof Endermite || enemy instanceof EnderDragon) {
                                cancel = false;
                                break;
                            }
                            if (enemy instanceof LivingEntity && RandomUtil.chance(0.4D))
                                knockback = teleportEnemy(enemy, knockback);
                            else knockback = 1.5F;
                            player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ENDER_DRAGON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                            break;
                    }
                    if (cancel) {
                        stack.getItem().damageItem(stack, (int) damage, player, player1 -> {
                        });
                        if (enemy instanceof LivingEntity && projectile == enemy)
                            ((LivingEntity) enemy).knockback(knockback * 0.5F, player.getX() - enemy.getY(), player.getZ() - enemy.getZ());
                        //player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SKELETON_HURT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
                    }
                    //event.setCanceled(cancel);
                } else if (item instanceof GiantSwordItem) {
                    switch (item.toString()) {
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
                    stack.getItem().damageItem(stack, (int) damage, player, player1 -> {
                    });
                    if (enemy instanceof LivingEntity && projectile == enemy)
                        ((LivingEntity) enemy).knockback(knockback * 0.5F, player.getX() - enemy.getX(), player.getZ() - enemy.getZ());
                    //player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 0.1F, 0.8F + player.level().random.nextFloat() * 0.4F);
                    //event.setCanceled(true);
                }
            }
        }
    }

    public static void dropArrowAtPlayer(Arrow arrow, Player player) {
        //arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        arrow.setDeltaMovement(0, 0, 0);
        arrow.discard();
        ItemStack arrowStack = new ItemStack(Items.ARROW);
        player.getInventory().add(arrowStack);
        //arrow.setPos(player.getX(), player.getY(), player.getZ());
        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ARROW_HIT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
    }

    public static void reflectProjectile(Player player, Entity projectile) {
        Vec3 look = player.getLookAngle();
        double speed = projectile.getX() * projectile.xo + projectile.yo * projectile.yo + projectile.zo * projectile.zo;
        speed = Math.sqrt(speed);
        speed += 0.2F;
        projectile.xo = look.x * speed;
        projectile.yo = look.y * speed;
        projectile.zo = look.z * speed;
        projectile.rotate(Rotation.CLOCKWISE_180);

        if (player instanceof ServerPlayer)
            ((ServerPlayer) player).connection.connection.send(new ClientboundAddEntityPacket(projectile));
        if (projectile instanceof Arrow) {
            ((Arrow) projectile).setOwner(player);
            projectile.xo /= -0.10000000149011612D;
            projectile.yo /= -0.10000000149011612D;
            projectile.zo /= -0.10000000149011612D;
        }

    }

    public static float teleportEnemy(Entity enemy, float knockback) {
        Vec3 prevPos = enemy.getPosition(0);
        double d0 = enemy.getPosition(0).x + (enemy.level().random.nextDouble() - 0.5D) * 64.0D;
        double d1 = enemy.getPosition(0).y + (double) (enemy.level().random.nextInt(64) - 32);
        double d2 = enemy.getPosition(0).z + (enemy.level().random.nextDouble() - 0.5D) * 64.0D;
        if (enemy.level() instanceof ServerLevel) {
            enemy.teleportTo(d0, d1, d2);
            enemy.level().playSound(null, prevPos.x, prevPos.y, prevPos.z, SoundEvents.ENDERMAN_TELEPORT, enemy.getSoundSource(), 1.0F, 1.0F);
            enemy.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            return 0.0F;
        }
        return knockback;
    }

    public static void enderFx(ServerLevel level, Vec3 playerPos) {
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
