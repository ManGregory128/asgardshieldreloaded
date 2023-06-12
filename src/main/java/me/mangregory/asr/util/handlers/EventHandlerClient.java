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
    public void onLivingHurt(final LivingHurtEvent evt) {
        if (evt.getEntity() instanceof Player player) {
            if (isDamageSourceBlockable(evt.getSource(), player) && evt.getAmount() > 0.0F) {
                //evt.setAmount((1.0F + evt.getAmount()) * 0.5F);
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

}