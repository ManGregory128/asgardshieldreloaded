package me.mangregory.asr.util.handlers;

import me.mangregory.asr.AsgardShieldReloaded;
import me.mangregory.asr.item.AsgardShieldItem;
import me.mangregory.asr.item.GiantSwordItem;
import me.mangregory.asr.util.RandomUtil;
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

import java.util.List;

@Mod.EventBusSubscriber
public class EventHandler
{
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
    public static void onBlockStart(PlayerInteractEvent.RightClickItem event)
    {
        Player player = event.getEntity();
        Level level = player.level();
        Vec3 pos = player.getPosition(0);

        Item itemMainHand = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        Item itemOffHand = player.getItemInHand(InteractionHand.OFF_HAND).getItem();;

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

    public static void dropArrowAtPlayer(Arrow arrow, Player player)
    {
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        arrow.xo = 0;
        arrow.yo = 0;
        arrow.zo = 0;
        arrow.setPos(player.getX(), player.getY(), player.getZ());
        player.level().playSound(null, BlockPos.containing(player.getPosition(0)), SoundEvents.ARROW_HIT, SoundSource.PLAYERS, 0.8F, 0.8F + player.level().random.nextFloat() * 0.4F);
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
        double d0 = enemy.getPosition(0).x + (enemy.level().random.nextDouble() - 0.5D) * 64.0D;
        double d1 = enemy.getPosition(0).y + (double) (enemy.level().random.nextInt(64) - 32);
        double d2 = enemy.getPosition(0).z + (enemy.level().random.nextDouble() - 0.5D) * 64.0D;
        if (enemy.level() instanceof ServerLevel)
        {
            enemy.teleportTo(d0, d1, d2);
            enemy.level().playSound(null, prevPos.x, prevPos.y, prevPos.z, SoundEvents.ENDERMAN_TELEPORT, enemy.getSoundSource(), 1.0F, 1.0F);
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